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
@Table(name = "equipment_mini_scalability")
public class EquipmentMiniScalability implements java.io.Serializable {

	private static final long serialVersionUID = -7880649749325567293L;

	private Integer id;
	private ProjectDetails projectDetails;
	private String fiId;
	private Integer portId;
	private Integer slotId;
	private int chassis;
	private int isConfigured;
	private String configureAs;

	public EquipmentMiniScalability() {
	}

	public EquipmentMiniScalability(int id) {
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

	@Column(name = "fi_id", length = 45)
	public String getFiId() {
		return this.fiId;
	}

	public void setFiId(String fiId) {
		this.fiId = fiId;
	}

	@Column(name = "port_id")
	public Integer getPortId() {
		return this.portId;
	}

	public void setPortId(Integer portId) {
		this.portId = portId;
	}

	@Column(name = "slot_id")
	public Integer getSlotId() {
		return this.slotId;
	}

	public void setSlotId(Integer slotId) {
		this.slotId = slotId;
	}

	@Column(name = "chassis", nullable = false)
	public int getChassis() {
		return this.chassis;
	}

	public void setChassis(int chassis) {
		this.chassis = chassis;
	}

	@Column(name = "is_configured")
	public int getIsConfigured() {
		return isConfigured;
	}

	public void setIsConfigured(int isConfigured) {
		this.isConfigured = isConfigured;
	}

	@Column(name = "configure_as")
	public String getConfigureAs() {
		return configureAs;
	}

	public void setConfigureAs(String configureAs) {
		this.configureAs = configureAs;
	}
}
