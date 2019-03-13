package com.kcfy.techservicemarket.facade;

import com.kcfy.techservicemarket.facade.dto.ProjectTypeDTO;
import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;

import java.text.ParseException;
import java.util.List;

public interface ProjectTypeFacade {

	public InvokeResult getProjectType(Long id);
	
	public InvokeResult creatProjectType(ProjectTypeDTO projectType) throws ParseException;
	
	public InvokeResult updateProjectType(ProjectTypeDTO projectType) throws ParseException;
	
	public InvokeResult removeProjectType(Long id);
	
	public InvokeResult removeProjectTypes(Long[] ids);
	
	public List<ProjectTypeDTO> findAllProjectType();
	
	public Page<ProjectTypeDTO> pageQueryProjectType(ProjectTypeDTO projectType, int currentPage, int pageSize);
	

}

