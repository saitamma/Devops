package com.cisco.ca.cstg.pdi.pojos;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "admin_tacacs_general")
public class AdminTacacsGeneral implements java.io.Serializable {

	private static final long serialVersionUID = -5459509661085692410L;
	private Integer id;
	private ProjectDetails projectDetails;
	private String timeout;

	public AdminTacacsGeneral() {
	}

	public AdminTacacsGeneral(Integer id) {
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

	@Column(name = "timeout", length = 45)
	public String getTimeout() {
		return this.timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

}
