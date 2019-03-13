package org.openkoala.security.controller;

import com.kcfy.techservicemarket.facade.UserFacade;
import com.kcfy.techservicemarket.facade.dto.UserImport;
import com.kcfy.techservicemarket.infra.util.ExcelReader;
import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;
import org.openkoala.security.facade.SecurityAccessFacade;
import org.openkoala.security.facade.SecurityConfigFacade;
import org.openkoala.security.facade.command.ChangeUserPropsCommand;
import org.openkoala.security.facade.command.CreateUserCommand;
import org.openkoala.security.facade.dto.PermissionDTO;
import org.openkoala.security.facade.dto.RoleDTO;
import org.openkoala.security.facade.dto.UserDTO;
import org.openkoala.security.shiro.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户控制器。
 * 分页都将采用POST请求方式，因GET请求搜索时携带中文会导致乱码。
 *
 * @author lucas
 */
@Controller
@RequestMapping("/auth/user")
public class UserController {

    @Inject
    private SecurityAccessFacade securityAccessFacade;

    @Inject
    private SecurityConfigFacade securityConfigFacade;

    @Inject
    private UserFacade userFacade;

    /**
     * 添加用户。
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public InvokeResult add(CreateUserCommand command) {
        String createOwner = CurrentUser.getUserAccount();
        command.setCreateOwner(createOwner);
        return securityConfigFacade.createUser(command);
    }

    /**
     * 更改用户。
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public InvokeResult changeUserProps(ChangeUserPropsCommand command) {
        return securityConfigFacade.changeUserProps(command);
    }

    /**
     * 撤销用户。
     */
    @ResponseBody
    @RequestMapping(value = "/terminate", method = RequestMethod.POST)
    public InvokeResult terminate(Long[] userIds) {
        String currentUserAccount = CurrentUser.getUserAccount();
        return securityConfigFacade.terminateUsers(userIds, currentUserAccount);
    }

    /**
     * 重置用户密码。
     */
    @ResponseBody
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public InvokeResult resetPassword(Long userId) {
        return securityConfigFacade.resetPassword(userId);
    }

    /**
     * 激活用户。
     */
    @ResponseBody
    @RequestMapping(value = "/activate", method = RequestMethod.POST)
    public InvokeResult activate(Long userId) {
        return securityConfigFacade.activate(userId);
    }

    /**
     * 挂起用户。
     */
    @ResponseBody
    @RequestMapping(value = "/suspend", method = RequestMethod.POST)
    public InvokeResult suspend(Long userId) {
        String currentUserAccount = CurrentUser.getUserAccount();
        return securityConfigFacade.suspend(userId, currentUserAccount);
    }

    /**
     * 批量激活用户。
     */
    @ResponseBody
    @RequestMapping(value = "/activates", method = RequestMethod.POST)
    public InvokeResult activates(Long[] userIds) {
        return securityConfigFacade.activate(userIds);
    }

    /**
     * 批量挂起用户。
     */
    @ResponseBody
    @RequestMapping(value = "/suspends", method = RequestMethod.POST)
    public InvokeResult suspends(Long[] userIds) {
        String currentUserAccount = CurrentUser.getUserAccount();
        return securityConfigFacade.suspend(userIds, currentUserAccount);
    }

    /**
     * 为用户授权一个角色。
     */
    @ResponseBody
    @RequestMapping(value = "/grantRoleToUser", method = RequestMethod.POST)
    public InvokeResult grantRoleToUser(Long userId, Long roleId) {
        return securityConfigFacade.grantRoleToUser(userId, roleId);
    }

    /**
     * 为用户授权多个角色。
     */
    @ResponseBody
    @RequestMapping(value = "/grantRolesToUser", method = RequestMethod.POST)
    public InvokeResult grantRolesToUser(Long userId, Long[] roleIds) {
        return securityConfigFacade.grantRolesToUser(userId, roleIds);
    }

    /**
     * 为用户授权一个权限。
     */
    @ResponseBody
    @RequestMapping(value = "/grantPermissionToUser", method = RequestMethod.POST)
    public InvokeResult grantPermissionToUser(Long userId, Long permissionId) {
        return securityConfigFacade.grantPermissionToUser(userId, permissionId);
    }

    /**
     * 为用户授权多个权限。
     */
    @ResponseBody
    @RequestMapping(value = "/grantPermissionsToUser", method = RequestMethod.POST)
    public InvokeResult grantPermissionsToUser(Long userId, Long[] permissionIds) {
        return securityConfigFacade.grantPermissionsToUser(userId, permissionIds);
    }

    /**
     * 通过角色下的用户撤销一个授权中心{@link org.openkoala.security.core.domain.Authorization}。
     */
    @ResponseBody
    @RequestMapping(value = "/terminateAuthorizationByUserInRole", method = RequestMethod.POST)
    public InvokeResult terminateAuthorizationByUserInRole(Long userId, Long roleId) {
        return securityConfigFacade.terminateUserFromRole(userId, roleId);
    }

    /**
     * 通过权限下的用户撤销一个授权中心{@link org.openkoala.security.core.domain.Authorization}。
     */
    @ResponseBody
    @RequestMapping(value = "/terminateAuthorizationByUserInPermission", method = RequestMethod.POST)
    public InvokeResult terminateAuthorizationByUserInPermission(Long userId, Long permissionId) {
        return securityConfigFacade.terminateUserFromPermission(userId, permissionId);
    }

    /**
     * 通过角色下的用户撤销多个授权中心{@link org.openkoala.security.core.domain.Authorization}。
     */
    @ResponseBody
    @RequestMapping(value = "/terminateAuthorizationByUserInRoles", method = RequestMethod.POST)
    public InvokeResult terminateAuthorizationByUserInRoles(Long userId, Long[] roleIds) {
        return securityConfigFacade.terminateUserFromRoles(userId, roleIds);
    }

    /**
     * 通过权限下的用户撤销多个授权中心{@link org.openkoala.security.core.domain.Authorization}。。
     */
    @ResponseBody
    @RequestMapping(value = "/terminatePermissionsByUser", method = RequestMethod.POST)
    public InvokeResult terminateAuthorizationsByPermissions(Long userId, Long[] permissionIds) {
        return securityConfigFacade.terminateUserFromPermissions(userId, permissionIds);
    }

    /**
     * 根据条件分页查询用户。
     */
    @ResponseBody
    @RequestMapping(value = "/pagingQuery", method = RequestMethod.POST)
    public Page<UserDTO> pagingQuery(int page, int pagesize, UserDTO queryUserCondition) {
        return securityAccessFacade.pagingQueryUsers(page, pagesize, queryUserCondition);
    }

    @ResponseBody
    @RequestMapping(value = "/findInfoOfUser", method = RequestMethod.GET)
    public InvokeResult findInfoOfUser(Long userId) {
        return securityAccessFacade.findInfoOfUser(userId);
    }

    /**
     * 根据用户ID分页查找已经授权的角色。
     */
    @ResponseBody
    @RequestMapping(value = "/pagingQueryGrantRoleByUserId", method = RequestMethod.POST)
    public Page<RoleDTO> pagingQueryRolesByUserId(int page, int pagesize, Long userId) {
        return securityAccessFacade.pagingQueryGrantRolesByUserId(page, pagesize, userId);
    }

    /**
     * 根据用户ID分页查询已经授权的权限
     */
    @ResponseBody
    @RequestMapping(value = "/pagingQueryGrantPermissionByUserId", method = RequestMethod.POST)
    public Page<PermissionDTO> pagingQueryGrantPermissionByUserId(int page, int pagesize, Long userId) {
        return securityAccessFacade.pagingQueryGrantPermissionByUserId(page, pagesize, userId);
    }

    /**
     * 根据条件分页查询还未授权的角色
     */
    @ResponseBody
    @RequestMapping(value = "/pagingQueryNotGrantRoles", method = RequestMethod.POST)
    public Page<RoleDTO> pagingQueryNotGrantRoles(int page, int pagesize, Long userId, RoleDTO queryRoleCondition) {
        return securityAccessFacade.pagingQueryNotGrantRoles(page, pagesize, queryRoleCondition, userId);
    }

    /**
     * 根据用户ID分页查找还未授权的权限。
     */
    @ResponseBody
    @RequestMapping(value = "/pagingQueryNotGrantPermissions", method = RequestMethod.POST)
    public Page<PermissionDTO> pagingQueryNotGrantPermissions(int page, int pagesize, PermissionDTO queryPermissionCondition, Long userId) {
        return securityAccessFacade.pagingQueryNotGrantPermissionsByUserId(page, pagesize, queryPermissionCondition, userId);
    }
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public String importUsers(ModelMap map, MultipartHttpServletRequest request) throws IOException {
        try {
            map.put("message", "导入成功。");
            List<UserImport> userList = new ArrayList<>();
            for (String[] content : new ExcelReader().readExcelContent(request.getFile("file").getInputStream())) {
                UserImport userImport = new UserImport();
                userImport.setNo(content[0]);
                userImport.setName(content[1]);
                userImport.setEmail(content[2]);
                userImport.setAccount(content[3]);
                userImport.setCellphone(content[4]);
                userImport.setTelephone(content[5]);
                userImport.setPost(content[6]);
                userImport.setDepartment(content[7]);
                userImport.setRole(content[8]);
                userImport.setSex(content[9]);
                userImport.setInDate(content[10]);
                userImport.setIdNo(content[11]);
                userImport.setMemo(content[12]);
                userList.add(userImport);
            }
            userFacade.importUsers(userList);
        } catch (Exception e) {
            map.put("message", "导入失败，原因：" + e);
        }
        return "auth/user-importin";
    }

}