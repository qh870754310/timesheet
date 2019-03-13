package com.kcfy.techservicemarket.web.controller;

import com.kcfy.techservicemarket.facade.ProjectStageFacade;
import com.kcfy.techservicemarket.facade.dto.ProjectDTO;
import com.kcfy.techservicemarket.facade.dto.ProjectStageDTO;
import com.kcfy.techservicemarket.facade.dto.sysmgmt.DropDownDTO;
import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/projectStage")
public class ProjectStageController {

	@Inject
	private ProjectStageFacade projectStageFacade;

	@ResponseBody
	@RequestMapping("/add")
	public InvokeResult add(ProjectStageDTO projectStageDTO) {
		return projectStageFacade.creatProjectStage(projectStageDTO);
	}

	@ResponseBody
	@RequestMapping("/update")
	public InvokeResult update(ProjectStageDTO projectStageDTO) {
		return projectStageFacade.updateProjectStage(projectStageDTO);
	}

	@ResponseBody
	@RequestMapping("/pageJson")
	public Page pageJson(ProjectStageDTO projectStageDTO, @RequestParam int page, @RequestParam int pagesize) {
		Page<ProjectStageDTO> all = projectStageFacade.pageQueryProjectStage(projectStageDTO, page, pagesize);
		return all;
	}

	@ResponseBody
	@RequestMapping("/delete")
	public InvokeResult remove(@RequestParam String ids) {
		String[] value = ids.split(",");
        Long[] idArrs = new Long[value.length];
        for (int i = 0; i < value.length; i ++) {
        	        					idArrs[i] = Long.parseLong(value[i]);
						        }
        return projectStageFacade.removeProjectStages(idArrs);
	}

	@ResponseBody
	@RequestMapping("/get/{id}")
	public InvokeResult get(@PathVariable Long id) {
		return projectStageFacade.getProjectStage(id);
	}

	@ResponseBody
	@RequestMapping("/findProjectTypeByProjectStage/{id}")
	public Map<String, Object> findProjectTypeByProjectStage(@PathVariable Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", projectStageFacade.findProjectTypeByProjectStage(id));
		return result;
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
	@RequestMapping("/listByProjectId/{id}")
	public List<DropDownDTO> listByProjectId(@PathVariable Long id) {
		List<ProjectStageDTO> list = projectStageFacade.findProjectStageListByTypeId(id);
		List<DropDownDTO> list1 = new ArrayList<DropDownDTO>();
		for(ProjectStageDTO projectStageDTO : list) {
			DropDownDTO dto = new DropDownDTO();
			dto.setId(projectStageDTO.getId());
			dto.setVal(projectStageDTO.getName());
			list1.add(dto);
		}
		return list1;
	}

	@ResponseBody
	@RequestMapping("/getByProjectId")
	public InvokeResult getByProjectId(Long projectId, HttpServletRequest request) {
		return  projectStageFacade.getByProjectId(projectId);
	}

}
