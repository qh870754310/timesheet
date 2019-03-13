package com.kcfy.techservicemarket.facade.dto.sysmgmt;

import java.io.Serializable;

public class DictionaryDTO implements Serializable {

	private static final long serialVersionUID = -7723721833535246231L;

	private Long id;

	private int version;

	private String dictName;

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getDictName() {
		return this.dictName;
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
		DictionaryDTO other = (DictionaryDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}