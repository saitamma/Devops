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
@Table(name = "admin_callhome_policy")
public class AdminCallhomePolicy implements java.io.Serializable {

	private static final long serialVersionUID = 8259488985732287438L;
	private Integer id;
	private ProjectDetails projectDetails;
	private String state;
	private String cause;

	public AdminCallhomePolicy() {
	}

	public AdminCallhomePolicy(Integer id) {
		this.id = id;
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
	@JoinColumn(name = "project_id")
	public ProjectDetails getProjectDetails() {
		return this.projectDetails;
	}

	public void setProjectDetails(ProjectDetails projectDetails) {
		this.projectDetails = projectDetails;
	}

	@Column(name = "state", length = 25)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "cause")
	public String getCause() {
		return this.cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

}
