package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "adaptorEthWorkQueueProfile")
public class Adaptorethworkqueueprofile implements java.io.Serializable {

	private static final long serialVersionUID = -3201236213489534200L;
	private int primaryKey;
	private Adaptorhostethifprofile adaptorhostethifprofile;
	@XmlAttribute
	private String count;
	@XmlAttribute
	private String ringSize;

	public Adaptorethworkqueueprofile() {
	}

	public Adaptorethworkqueueprofile(
			Adaptorhostethifprofile adaptorhostethifprofile) {
		this.adaptorhostethifprofile = adaptorhostethifprofile;
	}

	public Adaptorethworkqueueprofile(
			Adaptorhostethifprofile adaptorhostethifprofile, String count,
			String ringSize) {
		this.adaptorhostethifprofile = adaptorhostethifprofile;
		this.count = count;
		this.ringSize = ringSize;
	}

	public int getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Adaptorhostethifprofile getAdaptorhostethifprofile() {
		return this.adaptorhostethifprofile;
	}

	public void setAdaptorhostethifprofile(
			Adaptorhostethifprofile adaptorhostethifprofile) {
		this.adaptorhostethifprofile = adaptorhostethifprofile;
	}

	public String getCount() {
		return this.count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getRingSize() {
		return this.ringSize;
	}

	public void setRingSize(String ringSize) {
		this.ringSize = ringSize;
	}

}
