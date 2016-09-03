package com.cisco.ca.cstg.pdi.pojos;

public class EquipmentMiniServerUplink implements java.io.Serializable {

	private static final long serialVersionUID = -7880649749325567293L;

	private Integer id;
	private String fiId;
	private Integer portId;
	private Integer slotId;
	private int chassis;
	private int isConfigured;
	private String configureAs;
	private String configureAsOldValue;
	private String userLabel;

	public EquipmentMiniServerUplink() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFiId() {
		return this.fiId;
	}

	public void setFiId(String fiId) {
		this.fiId = fiId;
	}

	public Integer getPortId() {
		return this.portId;
	}

	public void setPortId(Integer portId) {
		this.portId = portId;
	}

	public Integer getSlotId() {
		return this.slotId;
	}

	public void setSlotId(Integer slotId) {
		this.slotId = slotId;
	}

	public int getChassis() {
		return this.chassis;
	}

	public void setChassis(int chassis) {
		this.chassis = chassis;
	}

	public int getIsConfigured() {
		return isConfigured;
	}

	public void setIsConfigured(int isConfigured) {
		this.isConfigured = isConfigured;
	}

	public String getConfigureAs() {
		return configureAs;
	}

	public void setConfigureAs(String configureAs) {
		this.configureAs = configureAs;
	}

	public String getConfigureAsOldValue() {
		return configureAsOldValue;
	}

	public void setConfigureAsOldValue(String configureAsOldValue) {
		this.configureAsOldValue = configureAsOldValue;
	}

	public String getUserLabel() {
		return this.userLabel;
	}

	public void setUserLabel(String userLabel) {
		this.userLabel = userLabel;
	}
}
