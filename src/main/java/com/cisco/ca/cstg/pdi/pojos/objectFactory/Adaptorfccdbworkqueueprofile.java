package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "adaptorFcCdbWorkQueueProfile")
public class Adaptorfccdbworkqueueprofile implements java.io.Serializable {

	private static final long serialVersionUID = -5835669266744442349L;
	private int primaryKey;
	private Adaptorhostfcifprofile adaptorhostfcifprofile;
	
	@XmlAttribute
	private String rn;
	
	@XmlAttribute
	private String ringSize;
	
	@XmlAttribute
	private String count;

	public Adaptorfccdbworkqueueprofile() {
	}

	public Adaptorfccdbworkqueueprofile(int primaryKey,
			Adaptorhostfcifprofile adaptorhostfcifprofile) {
		this.primaryKey = primaryKey;
		this.adaptorhostfcifprofile = adaptorhostfcifprofile;
	}

	public Adaptorfccdbworkqueueprofile(int primaryKey,
			Adaptorhostfcifprofile adaptorhostfcifprofile, String rn,
			String ringSize, String count) {
		this.primaryKey = primaryKey;
		this.adaptorhostfcifprofile = adaptorhostfcifprofile;
		this.rn = rn;
		this.ringSize = ringSize;
		this.count = count;
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

	public String getCount() {
		return this.count;
	}

	public void setCount(String count) {
		this.count = count;
	}

}
