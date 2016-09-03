package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lsVConAssign")
public class Lsvconassign implements java.io.Serializable {

	private static final long serialVersionUID = -7733769520483040098L;
	private int primaryKey;
	private Lsserver lsserver;
	
	@XmlAttribute
	private String vnicName;
	
	@XmlAttribute
	private String transport;
	
	@XmlAttribute
	private String order;
	
	@XmlAttribute
	private String adminVcon;

	public Lsvconassign() {
	}

	public Lsvconassign(int primaryKey, Lsserver lsserver) {
		this.primaryKey = primaryKey;
		this.lsserver = lsserver;
	}

	public Lsvconassign(int primaryKey, Lsserver lsserver, String vnicName,
			String transport, String order, String adminVcon) {
		this.primaryKey = primaryKey;
		this.lsserver = lsserver;
		this.vnicName = vnicName;
		this.transport = transport;
		this.order = order;
		this.adminVcon = adminVcon;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Lsserver getLsserver() {
		return this.lsserver;
	}

	public void setLsserver(Lsserver lsserver) {
		this.lsserver = lsserver;
	}

	public String getVnicName() {
		return this.vnicName;
	}

	public void setVnicName(String vnicName) {
		this.vnicName = vnicName;
	}

	public String getTransport() {
		return this.transport;
	}

	public void setTransport(String transport) {
		this.transport = transport;
	}

	public String getOrder() {
		return this.order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getAdminVcon() {
		return this.adminVcon;
	}

	public void setAdminVcon(String adminVcon) {
		this.adminVcon = adminVcon;
	}

}
