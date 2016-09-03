package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "adaptorEthCompQueueProfile")
public class Adaptorethcompqueueprofile implements java.io.Serializable {

	private static final long serialVersionUID = -6471129224290488935L;
	private int primaryKey;
	private Adaptorhostethifprofile adaptorhostethifprofile;
	@XmlAttribute
	private String count;

	public Adaptorethcompqueueprofile() {
	}

	public Adaptorethcompqueueprofile(
			Adaptorhostethifprofile adaptorhostethifprofile) {
		this.adaptorhostethifprofile = adaptorhostethifprofile;
	}

	public Adaptorethcompqueueprofile(
			Adaptorhostethifprofile adaptorhostethifprofile, String count) {
		this.adaptorhostethifprofile = adaptorhostethifprofile;
		this.count = count;
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

}
