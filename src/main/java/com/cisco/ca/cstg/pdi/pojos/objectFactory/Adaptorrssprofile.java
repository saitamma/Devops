package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "adaptorRssProfile")
public class Adaptorrssprofile implements java.io.Serializable {

	private static final long serialVersionUID = 3315337328471212540L;
	private int primaryKey;
	private Adaptorhostethifprofile adaptorhostethifprofile;
	@XmlAttribute
	private String receiveSideScaling;

	public Adaptorrssprofile() {
	}

	public Adaptorrssprofile(Adaptorhostethifprofile adaptorhostethifprofile) {
		this.adaptorhostethifprofile = adaptorhostethifprofile;
	}

	public Adaptorrssprofile(Adaptorhostethifprofile adaptorhostethifprofile,
			String receiveSideScaling) {
		this.adaptorhostethifprofile = adaptorhostethifprofile;
		this.receiveSideScaling = receiveSideScaling;
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

	public String getReceiveSideScaling() {
		return this.receiveSideScaling;
	}

	public void setReceiveSideScaling(String receiveSideScaling) {
		this.receiveSideScaling = receiveSideScaling;
	}

}
