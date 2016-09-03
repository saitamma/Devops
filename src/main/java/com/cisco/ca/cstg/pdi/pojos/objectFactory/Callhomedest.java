package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "callhomeDest")
public class Callhomedest implements java.io.Serializable {

	private static final long serialVersionUID = 5158701712109647586L;
	private int primaryKey;
	private Callhomeprofile callhomeprofile;
	@XmlAttribute
	private String email;

	public Callhomedest() {
	}

	public Callhomedest(Callhomeprofile callhomeprofile) {
		this.callhomeprofile = callhomeprofile;
	}

	public Callhomedest(Callhomeprofile callhomeprofile, String email) {
		this.callhomeprofile = callhomeprofile;
		this.email = email;
	}

	public int getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Callhomeprofile getCallhomeprofile() {
		return this.callhomeprofile;
	}

	public void setCallhomeprofile(Callhomeprofile callhomeprofile) {
		this.callhomeprofile = callhomeprofile;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
