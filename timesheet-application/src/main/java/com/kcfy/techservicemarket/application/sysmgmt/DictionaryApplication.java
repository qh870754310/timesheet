package com.kcfy.techservicemarket.application.sysmgmt;


import java.util.List;
import java.util.Set;

import com.kcfy.techservicemarket.core.domain.sysmgmt.Dictionary;

public interface DictionaryApplication {

	public Dictionary getDictionary(Long id);
	
	public void creatDictionary(Dictionary dictionary);
	
	public void updateDictionary(Dictionary dictionary);
	
	public void removeDictionary(Dictionary dictionary);
	
	public void removeDictionarys(Set<Dictionary> dictionarys);
	
	public List<Dictionary> findAllDictionary();
	
}

