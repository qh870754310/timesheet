package com.kcfy.techservicemarket.web.controller;


import com.kcfy.techservicemarket.facade.ProjectTypeFacade;
import com.kcfy.techservicemarket.facade.dto.ProjectTypeDTO;
import com.kcfy.techservicemarket.facade.dto.sysmgmt.DropDownDTO;
import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/ProjectType")
public class ProjectTypeController {

    @Inject
    private ProjectTypeFacade projectTypeFacade;

    @ResponseBody
    @RequestMapping("/add")
    public InvokeResult add(ProjectTypeDTO projectTypeDTO) throws ParseException {
        return projectTypeFacade.creatProjectType(projectTypeDTO);
    }

    @ResponseBody
    @RequestMapping("/update")
    public InvokeResult update(ProjectTypeDTO projectTypeDTO) throws ParseException {
        return projectTypeFacade.updateProjectType(projectTypeDTO);
    }

    @ResponseBody
    @RequestMapping("/pageJson")
    public Page pageJson(ProjectTypeDTO projectTypeDTO, @RequestParam int page, @RequestParam int pagesize) {
        Page<ProjectTypeDTO> all = projectTypeFacade.pageQueryProjectType(projectTypeDTO, page, pagesize);
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
        return projectTypeFacade.removeProjectTypes(idArrs);
    }

    @ResponseBody
    @RequestMapping("/get/{id}")
    public InvokeResult get(@PathVariable Long id) {
        return projectTypeFacade.getProjectType(id);
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
    public List<DropDownDTO> list() {
        List<ProjectTypeDTO> list = projectTypeFacade.findAllProjectType();
        List<DropDownDTO> list1 = new ArrayList<DropDownDTO>();
        for(ProjectTypeDTO projectTypeDTO : list) {
            DropDownDTO dto = new DropDownDTO();
            dto.setId(projectTypeDTO.getId());
            dto.setVal(projectTypeDTO.getName());
            list1.add(dto);
        }
        return list1;
    }
}
