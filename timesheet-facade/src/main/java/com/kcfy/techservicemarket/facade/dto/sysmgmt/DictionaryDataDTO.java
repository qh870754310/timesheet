package com.kcfy.techservicemarket.facade.dto.sysmgmt;

import java.io.Serializable;

public class DictionaryDataDTO implements Serializable {

	private static final long serialVersionUID = 6924755686521993437L;

	private Long id;

	private int version;

	private Long dictId;
	
	private String dictName;

	private String dictdataName;

	private String dictdataValue;

	/**
	 * @return the dictId
	 */
	public Long getDictId() {
		return dictId;
	}

	/**
	 * @param dictId the dictId to set
	 */
	public void setDictId(Long dictId) {
		this.dictId = dictId;
	}
	
	

	/**
	 * @return the dictName
	 */
	public String getDictName() {
		return dictName;
	}

	/**
	 * @param dictName the dictName to set
	 */
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public void setDictdataName(String dictdataName) {
		this.dictdataName = dictdataName;
	}

	public String getDictdataName() {
		return this.dictdataName;
	}

	public void setDictdataValue(String dictdataValue) {
		this.dictdataValue = dictdataValue;
	}

	public String getDictdataValue() {
		return this.dictdataValue;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DictionaryDataDTO other = (DictionaryDataDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}