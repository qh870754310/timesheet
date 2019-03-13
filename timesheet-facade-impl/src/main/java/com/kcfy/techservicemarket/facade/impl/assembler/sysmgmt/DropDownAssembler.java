/**
 * 
 */
package com.kcfy.techservicemarket.facade.impl.assembler.sysmgmt;

import com.kcfy.techservicemarket.core.domain.sysmgmt.Dictionary;
import com.kcfy.techservicemarket.core.domain.sysmgmt.DictionaryData;
import com.kcfy.techservicemarket.facade.dto.sysmgmt.DropDownDTO;
import org.openkoala.organisation.core.domain.Employee;
import org.openkoala.organisation.core.domain.Organization;

/**
 * @author xiongp
 *
 */
public class DropDownAssembler {
	public static DropDownDTO toDTO(DictionaryData dictionaryData) {
		DropDownDTO dropDownDTO = new DropDownDTO();
		dropDownDTO.setId(dictionaryData.getId());
		dropDownDTO.setVal(dictionaryData.getDictdataValue());
		return dropDownDTO;
	}

	public static DropDownDTO toDTO(Dictionary dictionary) {
		DropDownDTO dropDownDTO = new DropDownDTO();
		dropDownDTO.setId(dictionary.getId());
		dropDownDTO.setVal(dictionary.getDictName());
		return dropDownDTO;
	}
	public static DropDownDTO toDTO(Organization organization) {
		DropDownDTO dropDownDTO = new DropDownDTO();
		dropDownDTO.setId(organization.getId());
		dropDownDTO.setVal(organization.getName());
		return dropDownDTO;
	}
	public static DropDownDTO toDTO(Employee employee) {
		DropDownDTO dropDownDTO = new DropDownDTO();
		dropDownDTO.setId(employee.getId());
		dropDownDTO.setVal(employee.getName());
		return dropDownDTO;
	}
}
