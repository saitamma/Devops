package com.cisco.ca.cstg.pdi.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "organization_view")
public class OrganizationsView implements java.io.Serializable {

	private static final long serialVersionUID = -5469397738801105668L;
	private Integer id;
	private Integer count;

	public OrganizationsView() {
	}

	public OrganizationsView(Integer id) {
		this.id = id;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "count")	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
