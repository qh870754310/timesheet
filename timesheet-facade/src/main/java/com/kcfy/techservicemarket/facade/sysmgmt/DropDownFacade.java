/**
 * 
 */
package com.kcfy.techservicemarket.facade.sysmgmt;

import com.kcfy.techservicemarket.facade.dto.sysmgmt.DropDownDTO;
import org.openkoala.organisation.facade.dto.EmployeeDTO;

import java.util.List;

/**
 * @author xiongp
 *
 */
public interface DropDownFacade {
	/**
	 * 获取下拉框数据
	 * @param dropDownDto
	 * @return
	 */
	public List<DropDownDTO> getByDicName(String name);
	/**
	 * 获取下拉框总类
	 * @return
	 */
	public List<DropDownDTO> getByDic();

	public List<DropDownDTO> getDepartments();

	public List<DropDownDTO> getEmployees();

	public List<DropDownDTO> getMessageTemplates();

	public List<DropDownDTO> getIndustry();
}
