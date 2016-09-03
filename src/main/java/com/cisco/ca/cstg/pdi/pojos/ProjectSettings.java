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
@Table(name = "project_settings")
public class ProjectSettings implements java.io.Serializable {

	private static final long serialVersionUID = -3058629916366397864L;
	
	private Integer id;
	private ProjectDetails projectDetails;
	private String projectKey;
	private String projectValue;

	public ProjectSettings() {
	}

	public ProjectSettings(Integer id) {
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
	@JoinColumn(name = "project_id", nullable = false)
	public ProjectDetails getProjectDetails() {
		return this.projectDetails;
	}

	public void setProjectDetails(ProjectDetails projectDetails) {
		this.projectDetails = projectDetails;
	}

	@Column(name = "project_key", nullable = false, length = 45)
	public String getProjectKey() {
		return this.projectKey;
	}

	public void setProjectKey(String projectKey) {
		this.projectKey = projectKey;
	}

	@Column(name = "project_value", nullable = false, length = 45)
	public String getProjectValue() {
		return this.projectValue;
	}

	public void setProjectValue(String projectValue) {
		this.projectValue = projectValue;
	}

}
