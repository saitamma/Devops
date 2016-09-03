package com.cisco.ca.cstg.pdi.pojos;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "admin_ldap_general")
public class AdminLdapGeneral implements java.io.Serializable {

	private static final long serialVersionUID = 6266762303669591025L;
	private Integer id;
	private ProjectDetails projectDetails;
	private String timeout;
	private String attribute;
	private String baseDn;
	private String filter;

	public AdminLdapGeneral() {
	}

	public AdminLdapGeneral(Integer id) {
		this.id = id;
	}

	public AdminLdapGeneral(ProjectDetails projectDetails) {
		this.projectDetails = projectDetails;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id", nullable = false)
	public ProjectDetails getProjectDetails() {
		return this.projectDetails;
	}

	public void setProjectDetails(ProjectDetails projectDetails) {
		this.projectDetails = projectDetails;
	}

	@Column(name = "timeout", length = 45)
	public String getTimeout() {
		return this.timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	@Column(name = "attribute", length = 45)
	public String getAttribute() {
		return this.attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	@Column(name = "baseDN", length = 45)
	public String getBaseDn() {
		return this.baseDn;
	}

	public void setBaseDn(String baseDn) {
		this.baseDn = baseDn;
	}

	@Column(name = "filter", length = 45)
	public String getFilter() {
		return this.filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

}
