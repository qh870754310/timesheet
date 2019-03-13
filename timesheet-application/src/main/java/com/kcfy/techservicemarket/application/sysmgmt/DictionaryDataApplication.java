package com.kcfy.techservicemarket.application.sysmgmt;


import com.kcfy.techservicemarket.core.domain.sysmgmt.DictionaryData;

import java.util.List;
import java.util.Set;


public interface DictionaryDataApplication {

	public DictionaryData getDictionaryData(Long id);
	
	public void creatDictionaryData(DictionaryData dictionaryData);
	
	public void updateDictionaryData(DictionaryData dictionaryData);
	
	public void removeDictionaryData(DictionaryData dictionaryData);
	
	public void removeDictionaryDatas(Set<DictionaryData> dictionaryDatas);
	
	public List<DictionaryData> findAllDictionaryData();
	
	/**
	 * 根据字典类别查询字典下拉数
	 * @return
	 */
	public List<DictionaryData> findByDicName(String name);
	
}

