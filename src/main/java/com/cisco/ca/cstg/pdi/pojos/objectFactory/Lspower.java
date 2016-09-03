package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lsPower")
public class Lspower implements java.io.Serializable {

	private static final long serialVersionUID = 5473003087319435233L;
	private int primaryKey;
	private Lsserver lsserver;
	
	@XmlAttribute
	private String state;

	public Lspower() {
	}

	public Lspower(int primaryKey, Lsserver lsserver) {
		this.primaryKey = primaryKey;
		this.lsserver = lsserver;
	}

	public Lspower(int primaryKey, Lsserver lsserver, String state) {
		this.primaryKey = primaryKey;
		this.lsserver = lsserver;
		this.state = state;
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

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
