package com.cisco.ca.cstg.pdi.pojos;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "equipment_uplink")
public class EquipmentUplink implements java.io.Serializable {

	private static final long serialVersionUID = 9145032406696202908L;
	private Integer id;
	private ProjectDetails projectDetails;
	private String fiId;
	private Integer portId;
	private Integer slotId;
	private String userLabel;
	private List<LanPortchannel> lanPortchannels = new ArrayList<LanPortchannel>();

	public EquipmentUplink() {
	}

	public EquipmentUplink(Integer id) {
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

	@Column(name = "user_label", length = 45)
	public String getUserLabel() {
		return this.userLabel;
	}

	public void setUserLabel(String userLabel) {
		this.userLabel = userLabel;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "equipmentUplink")
	public List<LanPortchannel> getLanPortchannels() {
		return this.lanPortchannels;
	}

	public void setLanPortchannels(List<LanPortchannel> lanPortchannels) {
		this.lanPortchannels = lanPortchannels;
	}

}
