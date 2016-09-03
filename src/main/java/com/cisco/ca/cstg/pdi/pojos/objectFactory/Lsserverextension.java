package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lsServerExtension")
public class Lsserverextension implements java.io.Serializable {

	private static final long serialVersionUID = -4239352807846440549L;
	private int primaryKey;
	private Lsserver lsserver;
	
	@XmlAttribute
	private String guid;

	public Lsserverextension() {
	}

	public Lsserverextension(int primaryKey, Lsserver lsserver) {
		this.primaryKey = primaryKey;
		this.lsserver = lsserver;
	}

	public Lsserverextension(int primaryKey, Lsserver lsserver, String guid) {
		this.primaryKey = primaryKey;
		this.lsserver = lsserver;
		this.guid = guid;
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

	public String getGuid() {
		return this.guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

}
