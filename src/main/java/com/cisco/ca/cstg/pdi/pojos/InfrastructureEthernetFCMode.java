package com.cisco.ca.cstg.pdi.pojos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "infrastructure_ethernet_fc_mode")
public class InfrastructureEthernetFCMode implements Serializable {

	private static final long serialVersionUID = -3506619514249315338L;

	private Integer id;
	private ProjectDetails projectDetails;
	private String ethernetMode;
	private String fcMode;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	public ProjectDetails getProjectDetails() {
		return projectDetails;
	}

	public void setProjectDetails(ProjectDetails projectDetails) {
		this.projectDetails = projectDetails;
	}

	@Column(name = "ethernet_mode", length = 45)
	public String getEthernetMode() {
		return ethernetMode;
	}

	public void setEthernetMode(String ethernetMode) {
		this.ethernetMode = ethernetMode;
	}

	@Column(name = "fc_mode", length = 45)
	public String getFcMode() {
		return fcMode;
	}

	public void setFcMode(String fcMode) {
		this.fcMode = fcMode;
	}

}
