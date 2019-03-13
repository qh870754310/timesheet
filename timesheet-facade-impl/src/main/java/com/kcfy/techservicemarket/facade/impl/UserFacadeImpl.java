package com.kcfy.techservicemarket.facade.impl;

import com.kcfy.techservicemarket.application.UserApplication;
import com.kcfy.techservicemarket.facade.UserFacade;
import com.kcfy.techservicemarket.facade.dto.UserImport;
import com.kcfy.techservicemarket.facade.dto.weixin.StatusReturn;
import com.kcfy.techservicemarket.facade.dto.weixin.UserDetails;
import com.kcfy.techservicemarket.facade.dto.weixin.UserRole;
import com.kcfy.techservicemarket.facade.dto.weixin.UserUpdateInfoDTO;
import com.kcfy.techservicemarket.infra.util.DateTool;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openkoala.koala.commons.InvokeResult;
import org.openkoala.organisation.application.BaseApplication;
import org.openkoala.organisation.application.EmployeeApplication;
import org.openkoala.organisation.application.OrganizationApplication;
import org.openkoala.organisation.core.domain.*;
import org.openkoala.organisation.facade.dto.EmployeeDTO;
import org.openkoala.organisation.facade.impl.assembler.EmployeeAssembler;
import org.openkoala.security.core.domain.Actor;
import org.openkoala.security.core.domain.Role;
import org.openkoala.security.core.domain.User;
import org.openkoala.security.facade.SecurityConfigFacade;
import org.openkoala.security.facade.command.CreateRoleCommand;
import org.openkoala.security.facade.dto.UserDTO;
import org.openkoala.security.facade.impl.assembler.UserAssembler;
import org.openkoala.security.org.facade.SecurityOrgConfigFacade;
import org.openkoala.security.org.facade.command.CreateEmpolyeeUserCommand;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;


@Named
public class UserFacadeImpl implements UserFacade {
    private static final Log LOG = LogFactory.getLog(UserFacade.class);
    @Inject
    private UserApplication application;
    @Inject
    private SecurityConfigFacade securityConfigFacade;
    @Inject
    private SecurityOrgConfigFacade securityOrgConfigFacade;
    @Inject
    private OrganizationApplication organizationApplication;
    @Inject
    private BaseApplication baseApplication;
    @Inject
    private EmployeeApplication employeeApplication;

    @Override
    public UserDTO getUser(Long id) {
        return UserAssembler.toUserDTO(application.getUser(id));
    }

    @Override
    public void importUsers(List<UserImport> userList) {
        // 部门
        List<String> deptList = new ArrayList<>();
        // 职务， 部门
        Map<String, String> postMap = new HashMap<>();
        // 角色
        List<String> roleList = new ArrayList<>();
        // 员工信息,邮箱，岗位
        Map<String, EmployeeDTO> employeeMap = new HashMap<>();
        // 用户信息,角色
        Map<String, Object[]> actorMap = new HashMap<>();
        for (UserImport userImport : userList) {
            String dept = userImport.getDepartment().trim();
            String job = userImport.getPost().trim();
            if (!deptList.contains(dept) && StringUtils.isNotEmpty(dept)) {
                deptList.add(dept);
            }
            if (!postMap.containsKey(job + "," + dept) && StringUtils.isNotEmpty(job) && StringUtils.isNotEmpty(dept)) {
                postMap.put(job + "," + dept, dept);
            }
            String[] roles = userImport.getRole().split("/");
            for (int i = 0; i < roles.length; i++) {
                if (!roleList.contains(roles[i])) {
                    roleList.add(roles[i]);
                }
            }
            String employee = userImport.getName();
            String email = userImport.getEmail();
            EmployeeDTO employeeDto = new EmployeeDTO();
            employeeDto.setName(employee);
            employeeDto.setSn(StringUtils.isEmpty(userImport.getNo()) ? getSn("PER-") : userImport.getNo());
            employeeDto.setEmail(email);
            employeeDto.setMobilePhone(userImport.getCellphone());
            employeeDto.setFamilyPhone(userImport.getTelephone());
            employeeDto.setGender("男".equals(userImport.getSex()) ? "MALE" : ("女".equals(userImport.getSex()) ? "FEMALE" : ""));
            employeeDto.setIdNumber(userImport.getIdNo());
            employeeDto.setOrganizationName(userImport.getDepartment());
            employeeDto.setPostName(userImport.getPost());
            employeeDto.setEntryDate(DateTool.convertStringToDate("yyyy-MM-dd", userImport.getInDate()));
            if (!employeeMap.containsKey(employee + "," + email) && StringUtils.isNotEmpty(employee) && StringUtils.isNotEmpty(email)) {
                employeeMap.put(employee + "," + email, employeeDto);
            }

            String account = StringUtils.isEmpty(userImport.getAccount()) ? userImport.getEmail().split("@")[0] : userImport.getAccount();
            Object[] info = new Object[3];
            info[0] = employeeDto;
            info[1] = userImport.getMemo();
            info[2] = roles;
            if (StringUtils.isNotEmpty(userImport.getEmail()) && StringUtils.isNotEmpty(userImport.getName())) {
                actorMap.put(account, info);
            }
        }

        saveDepts(deptList);
        saveJobAndPosition(postMap);
        saveRole(roleList);
        savePerson(employeeMap);
        saveActor(actorMap);
    }

    @Override
    public InvokeResult getBindStatus(String openId, String weixinUserId) {
        String userId = application.getBindStatus(openId,weixinUserId);
        StatusReturn statusReturn = new StatusReturn();
        statusReturn.setStatus(StringUtils.isNotEmpty(userId) ? "1" : "0");
        statusReturn.setUserId(userId);
        return InvokeResult.success(statusReturn);
    }

    @Override
    public Long bindWeixinUser(String openid, String weixinUserId, String email) {
        return application.bindWeixinUser(openid, weixinUserId, email);
    }

    @Override
    public InvokeResult unBindUser(Long userId) {
        int num =  application.unBindUser(userId);
        return num  == 1 ? InvokeResult.success() : InvokeResult.failure("无此用户信息");
    }

    @Override
    public UserRole getRoleNameByUserId(Long userId) {
        return application.getRoleNameByUserId(userId);
    }
    public UserDetails getUserDeatilsByUserId(Long userId){

        return application.getUserDeatilsByUserId(userId);


    }


    private void saveActor(Map<String, Object[]> actorMap) {
        for (String userAccount : actorMap.keySet()) {
            List<User> userList = Actor.getRepository().findByProperty(User.class, "userAccount", userAccount);
            if (userList.size() == 0) {
                Object[] info = actorMap.get(userAccount);
                EmployeeDTO employeeDto = (EmployeeDTO) info[0];
                CreateEmpolyeeUserCommand createEmpolyeeUserCommand = new CreateEmpolyeeUserCommand();
                createEmpolyeeUserCommand.setDescription(String.valueOf(info[1]));
                createEmpolyeeUserCommand.setName(employeeDto.getName());
                createEmpolyeeUserCommand.setUserAccount(userAccount);
                createEmpolyeeUserCommand.setCreateOwner("system");
                Party employee = Employee.getByNameAndEmail(Party.class, employeeDto.getName(), employeeDto.getEmail());
                createEmpolyeeUserCommand.setEmployeeId(employee.getId());
                InvokeResult invokeResult = securityOrgConfigFacade.createEmployeeUser(createEmpolyeeUserCommand);

                String[] roles = (String[]) info[2];
                Long[] roleIds = new Long[roles.length];
                for (int i = 0; i < roles.length; i++) {
                    Role role = Role.getRoleBy(roles[i]);
                    roleIds[i] = role.getId();
                }
                securityConfigFacade.grantRolesToUser(((Actor) invokeResult.getData()).getId(), roleIds);
            }
        }
    }

    private String getSn(String start) {
        return start + String.valueOf(new Date().getTime()).substring(10, 13) + String.valueOf(Math.random()).substring(2, 8);
    }

    private void savePerson(Map<String, EmployeeDTO> employeeMap) {
        for (String employeeInfo : employeeMap.keySet()) {
            String name = employeeInfo.split(",")[0];
            String email = employeeInfo.split(",")[1];
            boolean exist = Employee.isExistName(Party.class, name, email);
            if (!exist) {
                EmployeeDTO employeeDto = employeeMap.get(employeeInfo);
                if (StringUtils.isEmpty(employeeDto.getPostName()) || StringUtils.isEmpty(employeeDto.getOrganizationName())) {
                    baseApplication.saveParty(EmployeeAssembler.toEntity(employeeDto));
                } else {
                    Post post = (Post) Post.getByName(Party.class, employeeDto.getPostName());
                    employeeApplication.createEmployeeWithPost(EmployeeAssembler.toEntity(employeeDto), post);
                }
            }
        }
    }

    private void saveRole(List<String> roleList) {
        for (int i = 0; i < roleList.size(); i++) {
            boolean exist = Role.checkName(roleList.get(i));
            if (!exist) {
                CreateRoleCommand command = new CreateRoleCommand();
                command.setName(roleList.get(i));
                command.setDescription(roleList.get(i));
                securityConfigFacade.createRole(command);
            }
            LOG.info(roleList.get(i) + exist);
        }
    }

    private void saveDepts(List<String> deptList) {
        for (int i = 0; i < deptList.size(); i++) {
            boolean exist = Department.isExistName(Party.class, deptList.get(i), new Date());
            boolean existCompany = Company.isExistName(Party.class, deptList.get(i), new Date());
            if (!exist && !existCompany) {
                Company company = (Company) Department.getTopOrganization();
                Department department = new Department(deptList.get(i));
                department.setSn(getSn("DEPT-"));
                organizationApplication.createDepartment(company, department);
            }
            LOG.info(deptList.get(i) + exist);
        }
    }

    private void saveJobAndPosition(Map<String, String> postMap) {
        for (String jobStr : postMap.keySet()) {
            boolean exist = Job.isExistName(Party.class, jobStr.split(",")[0], new Date());
            Job job = null;
            if (!exist) {
                job = new Job(jobStr.split(",")[0], getSn("JOB-"));
                baseApplication.saveParty(job);
            } else {
                job = Job.getByName(Party.class, jobStr.split(",")[0]);
            }
            LOG.info(jobStr.split(",")[0] + exist);
            if (StringUtils.isNotEmpty(postMap.get(jobStr))) {
                LOG.info("department is null, do not add post");
                Party department = Party.getByName(postMap.get(jobStr));
                boolean existPost = Post.isExistName(Party.class, jobStr.split(",")[0], department.getId(), new Date());
                if (!existPost) {
                    Post post = new Post(jobStr.split(",")[0], getSn("POST-"), job, (Organization) department);
                    baseApplication.saveParty(post);
                }
            }
        }
    }

    @Override
    public InvokeResult updateUserInfoByUserId(Long userId, String phone) {
        int count = application.updateUserInfoByUserId(userId, phone);
        if(count > 0) {
            UserUpdateInfoDTO userUpdateInfoDTO = new UserUpdateInfoDTO();
            userUpdateInfoDTO.setUserId(String.valueOf(userId));
            return InvokeResult.success(userUpdateInfoDTO);
        }else{
            return InvokeResult.failure("无此用户");
        }
    }
}
