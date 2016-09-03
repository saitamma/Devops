package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
// Generated Nov 11, 2014 10:41:23 AM by Hibernate Tools 4.0.0
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "fabricEthLanPcEp")
@XmlAccessorType(XmlAccessType.FIELD)
public class Fabricethlanpcep implements java.io.Serializable {

	private static final long serialVersionUID = 1020593655882330844L;
	private int primaryKey;
	private Fabricethlanpc fabricethlanpc;
	
	@XmlAttribute(name="name")
	private String name;
	
	@XmlAttribute(name="adminState")
	private String adminState;
	
	@XmlAttribute(name="slotId")
	private String slotId;
	
	@XmlAttribute(name="portId")
	private String portId;
	
	@XmlAttribute(name="ethLinkProfileName")
	private String ethLinkProfileName;

	public Fabricethlanpcep() {
	}

	public Fabricethlanpcep(int primaryKey, Fabricethlanpc fabricethlanpc) {
		this.primaryKey = primaryKey;
		this.fabricethlanpc = fabricethlanpc;
	}

	public Fabricethlanpcep(int primaryKey, Fabricethlanpc fabricethlanpc,
			String name, String adminState, String slotId, String portId,
			String ethLinkProfileName) {
		this.primaryKey = primaryKey;
		this.fabricethlanpc = fabricethlanpc;
		this.name = name;
		this.adminState = adminState;
		this.slotId = slotId;
		this.portId = portId;
		this.ethLinkProfileName = ethLinkProfileName;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Fabricethlanpc getFabricethlanpc() {
		return this.fabricethlanpc;
	}

	public void setFabricethlanpc(Fabricethlanpc fabricethlanpc) {
		this.fabricethlanpc = fabricethlanpc;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdminState() {
		return this.adminState;
	}

	public void setAdminState(String adminState) {
		this.adminState = adminState;
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

	public String getEthLinkProfileName() {
		return this.ethLinkProfileName;
	}

	public void setEthLinkProfileName(String ethLinkProfileName) {
		this.ethLinkProfileName = ethLinkProfileName;
	}

}
