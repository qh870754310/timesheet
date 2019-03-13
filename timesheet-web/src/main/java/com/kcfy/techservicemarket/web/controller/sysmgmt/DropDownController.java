/**
 * 
 */
package com.kcfy.techservicemarket.web.controller.sysmgmt;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.kcfy.techservicemarket.infra.util.IndustryCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kcfy.techservicemarket.facade.sysmgmt.DropDownFacade;
import com.kcfy.techservicemarket.facade.dto.sysmgmt.DropDownDTO;

/**
 * 所有的DropDown数据请求方法都应该写在该类中
 * 
 * @author xiongp
 *
 */
@Controller
@RequestMapping("/DropDown")
public class DropDownController {

	@Inject
	private DropDownFacade downFacade;

	@ResponseBody
	@RequestMapping("dicData")
	public List<DropDownDTO> dicData(@RequestParam String name) {
		return downFacade.getByDicName(name);
	}

	@ResponseBody
	@RequestMapping("dic")
	public List<DropDownDTO> dic() {
		return downFacade.getByDic();
	}
	@ResponseBody
	@RequestMapping("/department")
	public List<DropDownDTO> department() {
		return downFacade.getDepartments();
	}
	@ResponseBody
	@RequestMapping("/employee")
	public List<DropDownDTO> employee() {
		return downFacade.getEmployees();
	}
	@ResponseBody
	@RequestMapping("/messageTemplate")
	public List<DropDownDTO> messageTemplate() {
		return downFacade.getMessageTemplates();
	}
	@ResponseBody
	@RequestMapping("/industry")
	public List<DropDownDTO> industry() {
		return downFacade.getIndustry();
	}
}
