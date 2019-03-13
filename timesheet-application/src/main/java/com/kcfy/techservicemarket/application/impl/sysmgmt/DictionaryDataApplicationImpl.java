package com.kcfy.techservicemarket.application.impl.sysmgmt;

import java.util.List;
import java.util.Set;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.kcfy.techservicemarket.application.sysmgmt.DictionaryDataApplication;
import com.kcfy.techservicemarket.core.domain.sysmgmt.DictionaryData;

@Named
@Transactional
public class DictionaryDataApplicationImpl implements DictionaryDataApplication {

	public DictionaryData getDictionaryData(Long id) {
		return DictionaryData.get(DictionaryData.class, id);
	}
	
	public void creatDictionaryData(DictionaryData dictionaryData) {
		dictionaryData.save();
	}
	
	public void updateDictionaryData(DictionaryData dictionaryData) {
		dictionaryData.save();
	}
	
	public void removeDictionaryData(DictionaryData dictionaryData) {
		if(dictionaryData != null){
			dictionaryData.remove();
		}
	}
	
	public void removeDictionaryDatas(Set<DictionaryData> dictionaryDatas) {
		for (DictionaryData dictionaryData : dictionaryDatas) {
			dictionaryData.remove();
		}
	}
	
	public List<DictionaryData> findAllDictionaryData() {
		return DictionaryData.findAll(DictionaryData.class);
	}

	/* (non-Javadoc)
	 * @see com.koala.demo.application.DictionaryDataApplication#findByDicName(java.lang.String)
	 */
	@Override
	public List<DictionaryData> findByDicName(String name) {
		return DictionaryData.findByProperty(DictionaryData.class, "dictionary.dictName", name);
	}
	
}
