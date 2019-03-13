package com.kcfy.techservicemarket.web.controller;

import com.kcfy.techservicemarket.facade.ProjectFacade;
import com.kcfy.techservicemarket.facade.dto.ProjectDTO;
import com.kcfy.techservicemarket.facade.dto.ProjectImport;
import com.kcfy.techservicemarket.facade.dto.UserFitDTO;
import com.kcfy.techservicemarket.infra.util.ExcelReader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.inject.Inject;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private Log log = LogFactory.getLog(ProjectController.class);

    @Inject
    private ProjectFacade projectFacade;

    @ResponseBody
    @RequestMapping("/add")
    public InvokeResult add(ProjectDTO projectDTO) {
        return projectFacade.creatProject(projectDTO);
    }

    @ResponseBody
    @RequestMapping("/update")
    public InvokeResult update(ProjectDTO projectDTO) {
        return projectFacade.updateProject(projectDTO);
    }

    @ResponseBody
    @RequestMapping("/pageJson")
    public Page pageJson(ProjectDTO projectDTO, @RequestParam int page, @RequestParam int pagesize) {
        Page<ProjectDTO> all = projectFacade.pageQueryProject(projectDTO, page, pagesize);
        return all;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public InvokeResult remove(@RequestParam String ids) {
        String[] value = ids.split(",");
        Long[] idArrs = new Long[value.length];
        for (int i = 0; i < value.length; i++) {
            idArrs[i] = Long.parseLong(value[i]);
        }
        return projectFacade.removeProjects(idArrs);
    }

    @ResponseBody
    @RequestMapping("/get/{id}")
    public InvokeResult get(@PathVariable Long id) {
        return projectFacade.getProject(id);
    }

    @ResponseBody
    @RequestMapping("/close/{id}")
    public InvokeResult close(@PathVariable Long id) {
        return projectFacade.closeProject(id);
    }
    @ResponseBody
    @RequestMapping("/open/{id}")
    public InvokeResult open(@PathVariable Long id) {
        return projectFacade.openProject(id);
    }
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public String importUsers(ModelMap map, MultipartHttpServletRequest request) throws IOException {
        try {
            map.put("message", "导入成功。");
            List<ProjectImport> projectList = new ArrayList<>();
            for (String[] content : new ExcelReader().readExcelContent(request.getFile("file").getInputStream())) {
                ProjectImport projectImport = new ProjectImport();
                projectImport.setName(content[0]);
                projectImport.setDepartment(content[1]);
                projectImport.setPm(content[2]);
                projectImport.setType(content[3]);
                projectImport.setStage(content[4]);
                projectList.add(projectImport);
            }
            projectFacade.importProjects(projectList);
        } catch (Exception e) {
            map.put("message", "导入失败，原因：" + e);
        }
        return "project/project-importin";
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        //CustomDateEditor 可以换成自己定义的编辑器。  
        //注册一个Date 类型的绑定器 。
        binder.setAutoGrowCollectionLimit(Integer.MAX_VALUE);
    }

    @ResponseBody
    @RequestMapping("/list")
    public InvokeResult getListAndUserProject(Long userId) {
        return projectFacade.findAllProjectAndUserProject(userId);
    }

    @ResponseBody
    @RequestMapping("/setTeam")
    public InvokeResult setTeam(@RequestParam Long projectId, @RequestParam String userIds) {
        log.info(projectId.toString());
        log.info(userIds);
        String[] value = userIds.split(",");
        Long[] idArrs = new Long[value.length];
        for (int i = 0; i < value.length; i++) {
            idArrs[i] = Long.parseLong(value[i]);
        }
        InvokeResult invokeResult = this.projectFacade.updateProjectAddTeam(projectId, idArrs);
        return invokeResult;
    }
    @ResponseBody
    @RequestMapping("/unSetTeam")
    public InvokeResult unSetTeam(@RequestParam Long projectId, @RequestParam String userIds) {
        log.info(projectId.toString());
        log.info(userIds);
        String[] value = userIds.split(",");
        Long[] idArrs = new Long[value.length];
        for (int i = 0; i < value.length; i++) {
            idArrs[i] = Long.parseLong(value[i]);
        }
        InvokeResult invokeResult = this.projectFacade.updateProjectRemoveTeam(projectId, idArrs);
        return invokeResult;
    }

    @ResponseBody
    @RequestMapping(value = "/pagingQueryNotGrantUsers", method = RequestMethod.POST)
    public Page<UserFitDTO> pagingQueryNotGrantUsers(int page, int pagesize, Long projectId) {
        Page<UserFitDTO> pageList = projectFacade.pagingQueryNotGrantUsers(page, pagesize, projectId);
        return pageList;
    }
    @ResponseBody
    @RequestMapping(value = "/pagingQueryGrantUserByProjectId", method = RequestMethod.POST)
    public Page<UserFitDTO> pagingQueryGrantUserByProjectId(int page, int pagesize, Long projectId) {
        Page<UserFitDTO> pageList = projectFacade.pagingQueryGrantUserByProjectId(page, pagesize, projectId);
        return pageList;
    }

    @ResponseBody
    @RequestMapping(value = "/stageTime", method = RequestMethod.GET)
    public InvokeResult stageTime(Long projectId) {
        return projectFacade.getStagesByProjectId(projectId);
    }

    @ResponseBody
    @RequestMapping(value = "/time", method = RequestMethod.GET)
    public InvokeResult time(@RequestParam String projectIds, String startTime, String endTime) {
        String[] value = projectIds.split(",");
        List<Long> projectIdArrs = new ArrayList<Long>();
        for(int i = 0; i < value.length; i++){
            projectIdArrs.add(Long.valueOf(value[i]));
        }
        return projectFacade.getProjectTimes(projectIdArrs, startTime,endTime);
    }

    @ResponseBody
    @RequestMapping(value = "/getByDept", method = RequestMethod.GET)
    public InvokeResult getProjectListByDept(Long deptId) {
        return projectFacade.getProjectListByDept(deptId);
    }
}
