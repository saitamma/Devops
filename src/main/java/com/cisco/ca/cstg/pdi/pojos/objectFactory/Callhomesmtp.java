package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "callhomeSmtp")
public class Callhomesmtp implements java.io.Serializable {

	private static final long serialVersionUID = -5544182717184893034L;
	private int primaryKey;
	private Callhomeep callhomeEp;
	@XmlAttribute
	private String host;
	@XmlAttribute
	private String port;

	public Callhomesmtp() {
	}

	public Callhomesmtp(Callhomeep callhomeep) {
		this.callhomeEp = callhomeep;
	}

	public Callhomesmtp(Callhomeep callhomeEp, String host, String port) {
		this.callhomeEp = callhomeEp;
		this.host = host;
		this.port = port;
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

	public String getHost() {
		return this.host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return this.port;
	}

	public void setPort(String port) {
		this.port = port;
	}

}
