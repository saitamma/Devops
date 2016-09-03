package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
// Generated Nov 11, 2014 10:41:23 AM by Hibernate Tools 4.0.0

@XmlType(name = "fabricDceSwSrvEp")
@XmlAccessorType(XmlAccessType.FIELD)
public class Fabricdceswsrvep implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3339668179011907829L;
	private int primaryKey;
	private Fabricdceswsrv fabricdceswsrv;
	private Fabricsubgroup fabricsubgroup;
	
	@XmlAttribute(name="usrLbl")
	private String usrLbl;
	
	@XmlAttribute(name="slotId")
	private String slotId;
	
	@XmlAttribute(name="portId")
	private String portId;

	public Fabricdceswsrvep() {
	}

	public Fabricdceswsrvep(int primaryKey, Fabricdceswsrv fabricdceswsrv, Fabricsubgroup fabricsubgroup) {
		this.primaryKey = primaryKey;
		this.fabricdceswsrv = fabricdceswsrv;
		this.fabricsubgroup = fabricsubgroup;
	}

	public Fabricdceswsrvep(int primaryKey, Fabricdceswsrv fabricdceswsrv,
			String usrLbl, String slotId, String portId) {
		this.primaryKey = primaryKey;
		this.fabricdceswsrv = fabricdceswsrv;
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

	public Fabricdceswsrv getFabricdceswsrv() {
		return this.fabricdceswsrv;
	}

	public void setFabricdceswsrv(Fabricdceswsrv fabricdceswsrv) {
		this.fabricdceswsrv = fabricdceswsrv;
	}

	public Fabricsubgroup getFabricsubgroup() {
		return fabricsubgroup;
	}

	public void setFabricsubgroup(Fabricsubgroup fabricsubgroup) {
		this.fabricsubgroup = fabricsubgroup;
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
