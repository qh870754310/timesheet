package com.kcfy.techservicemarket.core.domain.sysmgmt;

import org.openkoala.common.core.domain.CustomKoalaAbstractEntity;

import javax.persistence.*;

/**
 * Auto Generated Entity
 * 
 * @author Koala
 * 
 */
@Entity
@Table(name = "t_dictionary_data")
public class DictionaryData extends CustomKoalaAbstractEntity {

	private static final long serialVersionUID = -6846801378196948925L;

	@Column(name = "dictdata_name")
	private String dictdataName;

	@Column(name = "dictdata_value")
	private String dictdataValue;

	@ManyToOne(cascade={},fetch=FetchType.EAGER,optional=true,targetEntity=Dictionary.class)
	@JoinColumn(name = "dict_id")
	private Dictionary dictionary;
	

	/**
	 * @return the dictdataName
	 */
	public String getDictdataName() {
		return dictdataName;
	}

	/**
	 * @param dictdataName the dictdataName to set
	 */
	public void setDictdataName(String dictdataName) {
		this.dictdataName = dictdataName;
	}

	public String getDictdataValue() {
		return dictdataValue;
	}

	public void setDictdataValue(String dictdataValue) {
		this.dictdataValue = dictdataValue;
	}

	/**
	 * @return the dictionary
	 */
	public Dictionary getDictionary() {
		return dictionary;
	}

	/**
	 * @param dictionary the dictionary to set
	 */
	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}

	@Override
	public String[] businessKeys() {
		// TODO Auto-generated method stub
		return null;
	}

}