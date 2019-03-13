package com.kcfy.techservicemarket.core.domain.sysmgmt;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.openkoala.common.core.domain.CustomKoalaAbstractEntity;

/**
 * Auto Generated Entity
 * 
 * @author Koala
 * 
 */
@Entity
@Table(name = "t_dictionary")
public class Dictionary extends CustomKoalaAbstractEntity{

	private static final long serialVersionUID = 2212898891880398523L;
	@Column(name = "dict_name")
	private String dictName;
	
	@OneToMany(cascade={}, fetch = FetchType.EAGER, mappedBy="dictionary")
	private List<DictionaryData> dictionaryDatas = new ArrayList<DictionaryData>();
	
	public Dictionary() {
	}

	public Dictionary(Long id) {
		super.setId(id);
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	/**
	 * @return the dictionaryDatas
	 */
	public List<DictionaryData> getDictionaryDatas() {
		return dictionaryDatas;
	}

	/**
	 * @param dictionaryDatas the dictionaryDatas to set
	 */
	public void setDictionaryDatas(List<DictionaryData> dictionaryDatas) {
		this.dictionaryDatas = dictionaryDatas;
	}

	@Override
	public String[] businessKeys() {
		// TODO Auto-generated method stub
		return null;
	}

}