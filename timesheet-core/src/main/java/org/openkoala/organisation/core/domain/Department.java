package org.openkoala.organisation.core.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.dayatang.domain.CriteriaQuery;
import org.dayatang.domain.QueryCriterion;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;
import org.openkoala.gqc.core.domain.QueryCondition;

import java.util.Date;
import java.util.List;

/**
 * 部门
 * 
 * @author xmfang
 * 
 */
@Entity
@DiscriminatorValue("DEPARTMENT")
public class Department extends Organization {

	private static final long serialVersionUID = -7339118476080239701L;

	public Department() {
	}

	public Department(String name) {
		super(name);
	}
	public Department(Long id) {
		super.setId(id);
	}

	public Department(String name, String sn) {
		super(name, sn);
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Department)) {
			return false;
		}
		Department that = (Department) other;
		return new EqualsBuilder().append(this.getName(), that.getName()).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getName()).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(getName()).build();
	}

	public static <P extends Party> boolean isExistName(Class<P> clazz, String name, Date date) {
		List<P> parties = getRepository().createCriteriaQuery(clazz).eq("category","DEPARTMENT").eq("name", name).le("createDate", date).gt("terminateDate", date).list();
		return !parties.isEmpty();
	}

	public static Department getByName(String name) {
		List<Department> departmentList = getRepository().createCriteriaQuery(Department.class).eq("category","DEPARTMENT").eq("name", name).list();
		return departmentList.size() > 0 ? departmentList.get(0) : null;
	}
}
