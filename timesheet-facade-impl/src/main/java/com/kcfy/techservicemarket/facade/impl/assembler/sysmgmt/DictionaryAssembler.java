package com.kcfy.techservicemarket.facade.impl.assembler.sysmgmt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.kcfy.techservicemarket.core.domain.sysmgmt.Dictionary;
import com.kcfy.techservicemarket.facade.dto.sysmgmt.DictionaryDTO;

public class DictionaryAssembler {

	public static DictionaryDTO toDTO(Dictionary dictionary) {
		if (dictionary == null) {
			return null;
		}
		DictionaryDTO result = new DictionaryDTO();
		result.setId(dictionary.getId());
		result.setVersion(dictionary.getVersion());
		result.setDictName(dictionary.getDictName());
		return result;
	}

	public static List<DictionaryDTO> toDTOs(Collection<Dictionary> dictionarys) {
		if (dictionarys == null) {
			return null;
		}
		List<DictionaryDTO> results = new ArrayList<DictionaryDTO>();
		for (Dictionary each : dictionarys) {
			results.add(toDTO(each));
		}
		return results;
	}

	public static Dictionary toEntity(DictionaryDTO dictionaryDTO) {
		if (dictionaryDTO == null) {
			return null;
		}
		Dictionary result = new Dictionary();
		result.setId(dictionaryDTO.getId());
		result.setVersion(dictionaryDTO.getVersion());
		result.setDictName(dictionaryDTO.getDictName());
		return result;
	}

	public static List<Dictionary> toEntities(Collection<DictionaryDTO> dictionaryDTOs) {
		if (dictionaryDTOs == null) {
			return null;
		}

		List<Dictionary> results = new ArrayList<Dictionary>();
		for (DictionaryDTO each : dictionaryDTOs) {
			results.add(toEntity(each));
		}
		return results;
	}
}
