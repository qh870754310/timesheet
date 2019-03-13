/**
 * 
 */
package com.kcfy.techservicemarket.facade.impl.sysmgmt;

import com.kcfy.techservicemarket.application.MessageTemplateApplication;
import com.kcfy.techservicemarket.application.sysmgmt.DictionaryApplication;
import com.kcfy.techservicemarket.application.sysmgmt.DictionaryDataApplication;
import com.kcfy.techservicemarket.core.domain.message.MessageTemplate;
import com.kcfy.techservicemarket.core.domain.sysmgmt.Dictionary;
import com.kcfy.techservicemarket.core.domain.sysmgmt.DictionaryData;
import com.kcfy.techservicemarket.facade.dto.sysmgmt.DropDownDTO;
import com.kcfy.techservicemarket.facade.impl.assembler.sysmgmt.DropDownAssembler;
import com.kcfy.techservicemarket.facade.sysmgmt.DropDownFacade;
import com.kcfy.techservicemarket.infra.util.IndustryCode;
import org.openkoala.organisation.application.EmployeeApplication;
import org.openkoala.organisation.application.OrganizationApplication;
import org.openkoala.organisation.core.domain.Employee;
import org.openkoala.organisation.core.domain.Organization;
import org.openkoala.organisation.facade.dto.EmployeeDTO;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author xiongp
 *
 */
@Named
public class DropDownFacadeImpl implements DropDownFacade {

	/* (non-Javadoc)
	 * @see com.koala.demo.facade.DropDownFacade#get(com.koala.demo.facade.dto.DropDownDTO)
	 */
	@Inject
	private DictionaryDataApplication dictionaryDataApplication;
	@Inject
	private DictionaryApplication dictionaryApplication;
	@Inject
	private OrganizationApplication organizationApplication;
	@Inject
	private EmployeeApplication employeeApplication;
	@Inject
	private MessageTemplateApplication messageTemplateApplication;

	@Override
	public List<DropDownDTO> getByDicName(String name) {
		List<DropDownDTO> dropDownDTOs = new ArrayList<>();
		List<DictionaryData> list = dictionaryDataApplication.findByDicName(name);
		for (DictionaryData dictionaryData : list) {
			dropDownDTOs.add(DropDownAssembler.toDTO(dictionaryData));
		}
		return dropDownDTOs;
	}

	/* (non-Javadoc)
	 * @see com.koala.demo.facade.DropDownFacade#getByDic()
	 */
	@Override
	public List<DropDownDTO> getByDic() {
		List<DropDownDTO> dropDownDTOs = new ArrayList<>();
		List<Dictionary> list = dictionaryApplication.findAllDictionary();
		for (Dictionary dictionary : list) {
			dropDownDTOs.add(DropDownAssembler.toDTO(dictionary));
		}
		return dropDownDTOs;
	}

	@Override
	public List<DropDownDTO> getDepartments() {
		List<DropDownDTO> dropDownDTOs = new ArrayList<>();
		List<Organization> list = organizationApplication.findAllDepartment();
		for (Organization organization : list) {
			dropDownDTOs.add(DropDownAssembler.toDTO(organization));
		}
		return dropDownDTOs;
	}
	@Override
	public List<DropDownDTO> getEmployees() {
		List<DropDownDTO> dropDownDTOs = new ArrayList<>();
		List<Employee> list = employeeApplication.findAllEmployee();
		for (Employee employee : list) {
			dropDownDTOs.add(DropDownAssembler.toDTO(employee));
		}
		return dropDownDTOs;
	}

	@Override
	public List<DropDownDTO> getMessageTemplates() {
		List<MessageTemplate> list = messageTemplateApplication.findAllMessageTemplate();
		List<DropDownDTO> list1 = new ArrayList<DropDownDTO>();
		for (MessageTemplate messageTemplate : list) {
			DropDownDTO dto = new DropDownDTO();
			dto.setId(messageTemplate.getId());
			dto.setVal(messageTemplate.getName());
			list1.add(dto);
		}
		return list1;
	}

	@Override
	public List<DropDownDTO> getIndustry() {
		Map<String, Integer> map = IndustryCode.getAll();
		List<DropDownDTO> list = new ArrayList<>();
		for (String key : map.keySet()) {
			DropDownDTO dto = new DropDownDTO();
			dto.setId(Long.valueOf(map.get(key)));
			dto.setVal(key);
			list.add(dto);
		}
		return list;
	}
}
