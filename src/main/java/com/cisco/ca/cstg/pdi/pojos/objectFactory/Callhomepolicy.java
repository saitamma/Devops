package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "callhomePolicy")
public class Callhomepolicy implements java.io.Serializable {

	private static final long serialVersionUID = 9104796418476496753L;
	private int primaryKey;
	private Callhomeep callhomeEp;
	@XmlAttribute
	private String adminState;
	@XmlAttribute
	private String cause;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String descr;

	public Callhomepolicy() {
	}

	public Callhomepolicy(Callhomeep callhomeEp) {
		this.callhomeEp = callhomeEp;
	}

	public Callhomepolicy(Callhomeep callhomeEp, String adminState,
			String cause, String name, String descr) {
		this.callhomeEp = callhomeEp;
		this.adminState = adminState;
		this.cause = cause;
		this.name = name;
		this.descr = descr;
	}

	public int getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Callhomeep getCallhomeEp() {
		return callhomeEp;
	}

	public void setCallhomeEp(Callhomeep callhomeEp) {
		this.callhomeEp = callhomeEp;
	}

	public String getAdminState() {
		return this.adminState;
	}

	public void setAdminState(String adminState) {
		this.adminState = adminState;
	}

	public String getCause() {
		return this.cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

}
