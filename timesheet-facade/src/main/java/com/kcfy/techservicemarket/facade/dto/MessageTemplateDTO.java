package com.kcfy.techservicemarket.facade.dto;

import java.io.Serializable;

public class MessageTemplateDTO implements Serializable {

	private Long id;

	private int version;

			
		private String content;
		
				
		private String templateId;
		
				
		private String name;
		
			
	
	public void setContent(String content) { 
		this.content = content;
	}

	public String getContent() {
		return this.content;
	}
		
			
	
	public void setTemplateId(String templateId) { 
		this.templateId = templateId;
	}

	public String getTemplateId() {
		return this.templateId;
	}
		
			
	
	public void setName(String name) { 
		this.name = name;
	}

	public String getName() {
		return this.name;
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
		MessageTemplateDTO other = (MessageTemplateDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}