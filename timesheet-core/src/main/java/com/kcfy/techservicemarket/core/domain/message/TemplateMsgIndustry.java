package com.kcfy.techservicemarket.core.domain.message;

import org.openkoala.common.core.domain.CustomKoalaAbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "t_message_template_industry")
public class TemplateMsgIndustry extends CustomKoalaAbstractEntity {

	@Column(name = "name_one_first")
	private String nameOneFirst;
	@Column(name = "name_one_second")
	private String nameOneSecond;
	@Column(name = "code_one")
	private Integer codeOne;
	@Column(name = "name_two_first")
	private String nameTwoFirst;
	@Column(name = "name_two_second")
	private String nameTwoSecond;
	@Column(name = "code_two")
	private Integer codeTwo;

	public String getNameOneFirst() {
		return nameOneFirst;
	}
	public void setNameOneFirst(String nameOneFirst) {
		this.nameOneFirst = nameOneFirst;
	}
	public String getNameOneSecond() {
		return nameOneSecond;
	}
	public void setNameOneSecond(String nameOneSecond) {
		this.nameOneSecond = nameOneSecond;
	}
	public Integer getCodeOne() {
		return codeOne;
	}
	public void setCodeOne(Integer codeOne) {
		this.codeOne = codeOne;
	}
	public String getNameTwoFirst() {
		return nameTwoFirst;
	}
	public void setNameTwoFirst(String nameTwoFirst) {
		this.nameTwoFirst = nameTwoFirst;
	}
	public String getNameTwoSecond() {
		return nameTwoSecond;
	}
	public void setNameTwoSecond(String nameTwoSecond) {
		this.nameTwoSecond = nameTwoSecond;
	}
	public Integer getCodeTwo() {
		return codeTwo;
	}
	public void setCodeTwo(Integer codeTwo) {
		this.codeTwo = codeTwo;
	}


	@Override
	public String[] businessKeys() {
		return new String[0];
	}
}
