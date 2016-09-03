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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * LanPortchannel generated by hbm2java
 */
@Entity
@Table(name = "lan_portchannel")
public class LanPortchannel implements java.io.Serializable {

	private static final long serialVersionUID = -2869465056832327695L;	
	private Integer id;
	private ProjectDetails projectDetails;
	private EquipmentUplink equipmentUplink;
	private String fiId;
	private String fiName;
	private String description;

	public LanPortchannel() {
	}
	
	public LanPortchannel(Integer id) {
		this.id = id;
	}

	public LanPortchannel(ProjectDetails projectDetails,
			EquipmentUplink equipmentUplink, String fiId, String fiName,
			String description) {
		this.projectDetails = projectDetails;
		this.equipmentUplink = equipmentUplink;
		this.fiId = fiId;
		this.fiName = fiName;
		this.description = description;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "uplink_id")
	@Fetch(FetchMode.SELECT)
	public EquipmentUplink getEquipmentUplink() {
		return this.equipmentUplink;
	}

	public void setEquipmentUplink(EquipmentUplink equipmentUplink) {
		this.equipmentUplink = equipmentUplink;
	}

	@Column(name = "fi_id", length = 45)
	public String getFiId() {
		return this.fiId;
	}

	public void setFiId(String fiId) {
		this.fiId = fiId;
	}

	@Column(name = "fi_name", length = 45)
	public String getFiName() {
		return this.fiName;
	}

	public void setFiName(String fiName) {
		this.fiName = fiName;
	}

	@Column(name = "description", length = 45)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
