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
@Table(name = "equipment_chasis")
public class EquipmentChasis implements java.io.Serializable {

	private static final long serialVersionUID = -569203972191549427L;
	private Integer id;
	private ProjectDetails projectDetails;
	private String cdpAction;
	private String cdpLinkAgg;
	private String pspPowerSupply;

	public EquipmentChasis() {
	}

	public EquipmentChasis(Integer id) {
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

	@Column(name = "cdp_action", length = 45)
	public String getCdpAction() {
		return this.cdpAction;
	}

	public void setCdpAction(String cdpAction) {
		this.cdpAction = cdpAction;
	}

	@Column(name = "cdp_link_agg", length = 45)
	public String getCdpLinkAgg() {
		return this.cdpLinkAgg;
	}

	public void setCdpLinkAgg(String cdpLinkAgg) {
		this.cdpLinkAgg = cdpLinkAgg;
	}

	@Column(name = "psp_power_supply", length = 45)
	public String getPspPowerSupply() {
		return this.pspPowerSupply;
	}

	public void setPspPowerSupply(String pspPowerSupply) {
		this.pspPowerSupply = pspPowerSupply;
	}

}
