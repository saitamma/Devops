package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "fabricDceSwSrvPcEp")
@XmlAccessorType(XmlAccessType.FIELD)
public class Fabricdceswsrvpcep implements java.io.Serializable {

	private static final long serialVersionUID = -2057727092702741485L;
	private int primaryKey;
	private Fabricdceswsrvpc fabricdceswsrvpc;
	@XmlAttribute(name="adminState")
	private String adminState;
	@XmlAttribute(name="name")
	private String name;
	@XmlAttribute(name="usrLbl")
	private String usrLbl;
	@XmlAttribute(name="slotId")
	private String slotId;
	@XmlAttribute(name="portId")
	private String portId;

	public Fabricdceswsrvpcep() {
	}

	public Fabricdceswsrvpcep(Fabricdceswsrvpc fabricdceswsrvpc) {
		this.fabricdceswsrvpc = fabricdceswsrvpc;
	}

	public Fabricdceswsrvpcep(Fabricdceswsrvpc fabricdceswsrvpc,
			String adminState, String name, String usrLbl, String slotId,
			String portId) {
		this.fabricdceswsrvpc = fabricdceswsrvpc;
		this.adminState = adminState;
		this.name = name;
		this.usrLbl = usrLbl;
		this.slotId = slotId;
		this.portId = portId;
	}

	
	public int getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Fabricdceswsrvpc getFabricdceswsrvpc() {
		return this.fabricdceswsrvpc;
	}

	public void setFabricdceswsrvpc(Fabricdceswsrvpc fabricdceswsrvpc) {
		this.fabricdceswsrvpc = fabricdceswsrvpc;
	}

	public String getAdminState() {
		return this.adminState;
	}

	public void setAdminState(String adminState) {
		this.adminState = adminState;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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
