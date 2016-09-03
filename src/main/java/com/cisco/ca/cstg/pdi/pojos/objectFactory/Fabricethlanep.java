package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
// Generated Nov 11, 2014 10:41:23 AM by Hibernate Tools 4.0.0

@XmlRootElement(name = "fabricEthLanEp")
@XmlAccessorType(XmlAccessType.FIELD)
public class Fabricethlanep implements java.io.Serializable {

	private static final long serialVersionUID = 8570059850821000548L;
	private int primaryKey;
	private Fabricethlan fabricethlan;
	
	@XmlAttribute(name="usrLbl")
	private String usrLbl;
	
	@XmlAttribute(name="slotId")
	private String slotId;
	
	@XmlAttribute(name="portId")
	private String portId;

	public Fabricethlanep() {
	}

	public Fabricethlanep(int primaryKey, Fabricethlan fabricethlan) {
		this.primaryKey = primaryKey;
		this.fabricethlan = fabricethlan;
	}

	public Fabricethlanep(int primaryKey, Fabricethlan fabricethlan,
			String usrLbl, String slotId, String portId) {
		this.primaryKey = primaryKey;
		this.fabricethlan = fabricethlan;
		this.usrLbl = usrLbl;
		this.slotId = slotId;
		this.portId = portId;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Fabricethlan getFabricethlan() {
		return this.fabricethlan;
	}

	public void setFabricethlan(Fabricethlan fabricethlan) {
		this.fabricethlan = fabricethlan;
	}

	public String getUsrLbl() {
		return this.usrLbl;
	}

	public void setUsrLbl(String usrLbl) {
		this.usrLbl = usrLbl;
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
