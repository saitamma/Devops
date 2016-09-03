package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "adaptorFcInterruptProfile")
public class Adaptorfcinterruptprofile implements java.io.Serializable {

	private static final long serialVersionUID = 2954500725484419678L;
	private int primaryKey;
	private Adaptorhostfcifprofile adaptorhostfcifprofile;
	
	@XmlAttribute
	private String rn;
	
	@XmlAttribute
	private String mode;

	public Adaptorfcinterruptprofile() {
	}

	public Adaptorfcinterruptprofile(int primaryKey,
			Adaptorhostfcifprofile adaptorhostfcifprofile) {
		this.primaryKey = primaryKey;
		this.adaptorhostfcifprofile = adaptorhostfcifprofile;
	}

	public Adaptorfcinterruptprofile(int primaryKey,
			Adaptorhostfcifprofile adaptorhostfcifprofile, String rn,
			String mode) {
		this.primaryKey = primaryKey;
		this.adaptorhostfcifprofile = adaptorhostfcifprofile;
		this.rn = rn;
		this.mode = mode;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Adaptorhostfcifprofile getAdaptorhostfcifprofile() {
		return this.adaptorhostfcifprofile;
	}

	public void setAdaptorhostfcifprofile(
			Adaptorhostfcifprofile adaptorhostfcifprofile) {
		this.adaptorhostfcifprofile = adaptorhostfcifprofile;
	}

	public String getRn() {
		return this.rn;
	}

	public void setRn(String rn) {
		this.rn = rn;
	}

	public String getMode() {
		return this.mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

}
