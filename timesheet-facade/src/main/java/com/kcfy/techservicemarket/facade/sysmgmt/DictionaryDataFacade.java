package com.kcfy.techservicemarket.facade.sysmgmt;

import com.kcfy.techservicemarket.facade.dto.sysmgmt.DictionaryDataDTO;
import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;

import java.util.List;

public interface DictionaryDataFacade {

	public InvokeResult getDictionaryData(Long id);
	
	public InvokeResult creatDictionaryData(DictionaryDataDTO dictionaryData);
	
	public InvokeResult updateDictionaryData(DictionaryDataDTO dictionaryData);
	
	public InvokeResult removeDictionaryData(Long id);
	
	public InvokeResult removeDictionaryDatas(Long[] ids);
	
	public List<DictionaryDataDTO> findAllDictionaryData();
	
	public Page<DictionaryDataDTO> pageQueryDictionaryData(DictionaryDataDTO dictionaryData, int currentPage, int pageSize);
	

}

