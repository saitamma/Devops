package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "adaptorFcPortProfile")
public class Adaptorfcportprofile implements java.io.Serializable {

	private static final long serialVersionUID = 2352371144154781968L;
	private int primaryKey;
	private Adaptorhostfcifprofile adaptorhostfcifprofile;
	
	@XmlAttribute
	private String rn;
	
	@XmlAttribute
	private String lunsPerTarget;
	
	@XmlAttribute
	private String ioThrottleCount;

	public Adaptorfcportprofile() {
	}

	public Adaptorfcportprofile(int primaryKey,
			Adaptorhostfcifprofile adaptorhostfcifprofile) {
		this.primaryKey = primaryKey;
		this.adaptorhostfcifprofile = adaptorhostfcifprofile;
	}

	public Adaptorfcportprofile(int primaryKey,
			Adaptorhostfcifprofile adaptorhostfcifprofile, String rn,
			String lunsPerTarget, String ioThrottleCount) {
		this.primaryKey = primaryKey;
		this.adaptorhostfcifprofile = adaptorhostfcifprofile;
		this.rn = rn;
		this.lunsPerTarget = lunsPerTarget;
		this.ioThrottleCount = ioThrottleCount;
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

	public String getLunsPerTarget() {
		return this.lunsPerTarget;
	}

	public void setLunsPerTarget(String lunsPerTarget) {
		this.lunsPerTarget = lunsPerTarget;
	}

	public String getIoThrottleCount() {
		return this.ioThrottleCount;
	}

	public void setIoThrottleCount(String ioThrottleCount) {
		this.ioThrottleCount = ioThrottleCount;
	}

}
