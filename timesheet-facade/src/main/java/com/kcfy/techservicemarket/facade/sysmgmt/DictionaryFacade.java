package com.kcfy.techservicemarket.facade.sysmgmt;

import java.util.List;

import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;

import com.kcfy.techservicemarket.facade.dto.sysmgmt.DictionaryDTO;

public interface DictionaryFacade {

	public InvokeResult getDictionary(Long id);
	
	public InvokeResult creatDictionary(DictionaryDTO dictionary);
	
	public InvokeResult updateDictionary(DictionaryDTO dictionary);
	
	public InvokeResult removeDictionary(Long id);
	
	public InvokeResult removeDictionarys(Long[] ids);
	
	public List<DictionaryDTO> findAllDictionary();
	
	public Page<DictionaryDTO> pageQueryDictionary(DictionaryDTO dictionary, int currentPage, int pageSize);
	

}

