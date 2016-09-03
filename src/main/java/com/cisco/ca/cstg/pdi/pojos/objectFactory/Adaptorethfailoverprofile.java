package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "adaptorEthFailoverProfile")
public class Adaptorethfailoverprofile implements java.io.Serializable {

	private static final long serialVersionUID = -2475621326110886282L;
	private int primaryKey;
	private Adaptorhostethifprofile adaptorhostethifprofile;
	@XmlAttribute
	private String timeout;

	public Adaptorethfailoverprofile() {
	}

	public Adaptorethfailoverprofile(
			Adaptorhostethifprofile adaptorhostethifprofile) {
		this.adaptorhostethifprofile = adaptorhostethifprofile;
	}

	public Adaptorethfailoverprofile(
			Adaptorhostethifprofile adaptorhostethifprofile, String timeout) {
		this.adaptorhostethifprofile = adaptorhostethifprofile;
		this.timeout = timeout;
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

	public String getTimeout() {
		return this.timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

}
