package com.kcfy.techservicemarket.facade.impl.assembler.sysmgmt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.kcfy.techservicemarket.core.domain.sysmgmt.Dictionary;
import com.kcfy.techservicemarket.core.domain.sysmgmt.DictionaryData;
import com.kcfy.techservicemarket.facade.dto.sysmgmt.DictionaryDataDTO;

public class DictionaryDataAssembler {

	public static DictionaryDataDTO toDTO(DictionaryData dictionaryData) {
		if (dictionaryData == null) {
			return null;
		}
		DictionaryDataDTO result = new DictionaryDataDTO();
		result.setId(dictionaryData.getId());
		result.setVersion(dictionaryData.getVersion());
		result.setDictId(dictionaryData.getDictionary().getId());
		result.setDictName(dictionaryData.getDictionary().getDictName());
		result.setDictdataName(dictionaryData.getDictdataName());
		result.setDictdataValue(dictionaryData.getDictdataValue());
		return result;
	}

	public static List<DictionaryDataDTO> toDTOs(Collection<DictionaryData> dictionaryDatas) {
		if (dictionaryDatas == null) {
			return null;
		}
		List<DictionaryDataDTO> results = new ArrayList<DictionaryDataDTO>();
		for (DictionaryData each : dictionaryDatas) {
			results.add(toDTO(each));
		}
		return results;
	}

	public static DictionaryData toEntity(DictionaryDataDTO dictionaryDataDTO) {
		if (dictionaryDataDTO == null) {
			return null;
		}
		DictionaryData result = new DictionaryData();
		result.setId(dictionaryDataDTO.getId());
		result.setVersion(dictionaryDataDTO.getVersion());
		if(dictionaryDataDTO.getDictId()!=null){
			result.setDictionary(new Dictionary(dictionaryDataDTO.getDictId()));
		}
		result.setDictdataName(dictionaryDataDTO.getDictdataName());
		result.setDictdataValue(dictionaryDataDTO.getDictdataValue());
		return result;
	}

	public static List<DictionaryData> toEntities(Collection<DictionaryDataDTO> dictionaryDataDTOs) {
		if (dictionaryDataDTOs == null) {
			return null;
		}

		List<DictionaryData> results = new ArrayList<DictionaryData>();
		for (DictionaryDataDTO each : dictionaryDataDTOs) {
			results.add(toEntity(each));
		}
		return results;
	}
}
