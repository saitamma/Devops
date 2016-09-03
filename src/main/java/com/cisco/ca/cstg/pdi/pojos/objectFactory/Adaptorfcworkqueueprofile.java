package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "adaptorFcWorkQueueProfile")
public class Adaptorfcworkqueueprofile implements java.io.Serializable {

	private static final long serialVersionUID = 4575634782198859134L;
	private int primaryKey;
	private Adaptorhostfcifprofile adaptorhostfcifprofile;
	
	@XmlAttribute
	private String rn;
	
	@XmlAttribute
	private String ringSize;

	public Adaptorfcworkqueueprofile() {
	}

	public Adaptorfcworkqueueprofile(int primaryKey,
			Adaptorhostfcifprofile adaptorhostfcifprofile) {
		this.primaryKey = primaryKey;
		this.adaptorhostfcifprofile = adaptorhostfcifprofile;
	}

	public Adaptorfcworkqueueprofile(int primaryKey,
			Adaptorhostfcifprofile adaptorhostfcifprofile, String rn,
			String ringSize) {
		this.primaryKey = primaryKey;
		this.adaptorhostfcifprofile = adaptorhostfcifprofile;
		this.rn = rn;
		this.ringSize = ringSize;
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

	public String getRingSize() {
		return this.ringSize;
	}

	public void setRingSize(String ringSize) {
		this.ringSize = ringSize;
	}

}
