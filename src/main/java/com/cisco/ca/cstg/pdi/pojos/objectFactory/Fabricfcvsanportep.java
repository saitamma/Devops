package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
// Generated Nov 11, 2014 10:41:23 AM by Hibernate Tools 4.0.0

/**
 * Fabricfcvsanportep generated by hbm2java
 */
@XmlRootElement(name = "fabricFcVsanPortEp")
@XmlAccessorType(XmlAccessType.FIELD)
public class Fabricfcvsanportep implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -984577443473151693L;
	private int primaryKey;
	private Fabricvsan fabricvsan;
	
	@XmlAttribute(name="adminState")
	private String adminState;
	
	@XmlAttribute(name="switchId")
	private String switchId;
	
	@XmlAttribute(name="slotId")
	private String slotId;
	
	@XmlAttribute(name="portId")
	private String portId;

	public Fabricfcvsanportep() {
	}

	public Fabricfcvsanportep(int primaryKey, Fabricvsan fabricvsan) {
		this.primaryKey = primaryKey;
		this.fabricvsan = fabricvsan;
	}

	public Fabricfcvsanportep(int primaryKey, Fabricvsan fabricvsan,
			String adminState, String switchId, String slotId, String portId) {
		this.primaryKey = primaryKey;
		this.fabricvsan = fabricvsan;
		this.adminState = adminState;
		this.switchId = switchId;
		this.slotId = slotId;
		this.portId = portId;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Fabricvsan getFabricvsan() {
		return this.fabricvsan;
	}

	public void setFabricvsan(Fabricvsan fabricvsan) {
		this.fabricvsan = fabricvsan;
	}

	public String getAdminState() {
		return this.adminState;
	}

	public void setAdminState(String adminState) {
		this.adminState = adminState;
	}

	public String getSwitchId() {
		return this.switchId;
	}

	public void setSwitchId(String switchId) {
		this.switchId = switchId;
	}

	public String getSlotId() {
		return this.slotId;
	}

	public void setSlotId(String slotId) {
		this.slotId = slotId;
	}

	public String getPortId() {
		return this.portId;
	}

	public void setPortId(String portId) {
		this.portId = portId;
	}

}
