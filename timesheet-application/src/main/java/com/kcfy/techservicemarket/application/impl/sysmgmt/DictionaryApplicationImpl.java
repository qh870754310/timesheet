package com.kcfy.techservicemarket.application.impl.sysmgmt;

import java.util.List;
import java.util.Set;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.kcfy.techservicemarket.application.sysmgmt.DictionaryApplication;
import com.kcfy.techservicemarket.core.domain.sysmgmt.Dictionary;

@Named
@Transactional
public class DictionaryApplicationImpl implements DictionaryApplication {

	public Dictionary getDictionary(Long id) {
		return Dictionary.get(Dictionary.class, id);
	}
	
	public void creatDictionary(Dictionary dictionary) {
		dictionary.save();
	}
	
	public void updateDictionary(Dictionary dictionary) {
		dictionary .save();
	}
	
	public void removeDictionary(Dictionary dictionary) {
		if(dictionary != null){
			dictionary.remove();
		}
	}
	
	public void removeDictionarys(Set<Dictionary> dictionarys) {
		for (Dictionary dictionary : dictionarys) {
			dictionary.remove();
		}
	}
	
	public List<Dictionary> findAllDictionary() {
		return Dictionary.findAll(Dictionary.class);
	}
	
}
