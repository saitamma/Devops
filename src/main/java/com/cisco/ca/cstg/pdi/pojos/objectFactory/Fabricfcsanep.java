package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
// Generated Nov 11, 2014 10:41:23 AM by Hibernate Tools 4.0.0
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "fabricFcSanEp")
@XmlAccessorType(XmlAccessType.FIELD)
public class Fabricfcsanep implements java.io.Serializable {

	private static final long serialVersionUID = -3026516954675271249L;
	private int primaryKey;
	private Fabricfcsan fabricfcsan;
	
	@XmlAttribute(name="adminState")
	private String adminState;
	
	@XmlAttribute(name="usrLbl")
	private String usrLbl;
	
	@XmlAttribute(name="slotId")
	private String slotId;
	
	@XmlAttribute(name="portId")
	private String portId;
	
	@XmlAttribute(name="fillPattern")
	private String fillPattern;

	public Fabricfcsanep() {
	}

	public Fabricfcsanep(int primaryKey, Fabricfcsan fabricfcsan) {
		this.primaryKey = primaryKey;
		this.fabricfcsan = fabricfcsan;
	}

	public Fabricfcsanep(int primaryKey, Fabricfcsan fabricfcsan,
			String adminState, String usrLbl, String slotId, String portId,
			String fillPattern) {
		this.primaryKey = primaryKey;
		this.fabricfcsan = fabricfcsan;
		this.adminState = adminState;
		this.usrLbl = usrLbl;
		this.slotId = slotId;
		this.portId = portId;
		this.fillPattern = fillPattern;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Fabricfcsan getFabricfcsan() {
		return this.fabricfcsan;
	}

	public void setFabricfcsan(Fabricfcsan fabricfcsan) {
		this.fabricfcsan = fabricfcsan;
	}

	public String getAdminState() {
		return this.adminState;
	}

	public void setAdminState(String adminState) {
		this.adminState = adminState;
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

	public String getFillPattern() {
		return this.fillPattern;
	}

	public void setFillPattern(String fillPattern) {
		this.fillPattern = fillPattern;
	}

}
