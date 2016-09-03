package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "adaptorEthArfsProfile")
public class Adaptoretharfsprofile implements java.io.Serializable {

	private static final long serialVersionUID = -113462775851226371L;
	private int primaryKey;
	private Adaptorhostethifprofile adaptorhostethifprofile;
	@XmlAttribute(name="accelaratedRFS")
	private String accelaratedRfs;

	public Adaptoretharfsprofile() {
	}

	public Adaptoretharfsprofile(Adaptorhostethifprofile adaptorhostethifprofile) {
		this.adaptorhostethifprofile = adaptorhostethifprofile;
	}

	public Adaptoretharfsprofile(
			Adaptorhostethifprofile adaptorhostethifprofile,
			String accelaratedRfs) {
		this.adaptorhostethifprofile = adaptorhostethifprofile;
		this.accelaratedRfs = accelaratedRfs;
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

	public String getAccelaratedRfs() {
		return this.accelaratedRfs;
	}

	public void setAccelaratedRfs(String accelaratedRfs) {
		this.accelaratedRfs = accelaratedRfs;
	}

}
