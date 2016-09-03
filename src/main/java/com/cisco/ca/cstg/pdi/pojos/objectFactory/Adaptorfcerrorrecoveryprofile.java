package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "adaptorFcErrorRecoveryProfile")
public class Adaptorfcerrorrecoveryprofile implements java.io.Serializable {

	private static final long serialVersionUID = 5931441955890465783L;
	private int primaryKey;
	private Adaptorhostfcifprofile adaptorhostfcifprofile;
	
	@XmlAttribute
	private String rn;
	
	@XmlAttribute
	private String portDownTimeout;
	
	@XmlAttribute
	private String portDownIoRetryCount;
	
	@XmlAttribute
	private String linkDownTimeout;
	
	@XmlAttribute
	private String fcpErrorRecovery;

	public Adaptorfcerrorrecoveryprofile() {
	}

	public Adaptorfcerrorrecoveryprofile(int primaryKey,
			Adaptorhostfcifprofile adaptorhostfcifprofile) {
		this.primaryKey = primaryKey;
		this.adaptorhostfcifprofile = adaptorhostfcifprofile;
	}

	public Adaptorfcerrorrecoveryprofile(int primaryKey,
			Adaptorhostfcifprofile adaptorhostfcifprofile, String rn,
			String portDownTimeout, String portDownIoRetryCount,
			String linkDownTimeout, String fcpErrorRecovery) {
		this.primaryKey = primaryKey;
		this.adaptorhostfcifprofile = adaptorhostfcifprofile;
		this.rn = rn;
		this.portDownTimeout = portDownTimeout;
		this.portDownIoRetryCount = portDownIoRetryCount;
		this.linkDownTimeout = linkDownTimeout;
		this.fcpErrorRecovery = fcpErrorRecovery;
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

	public String getPortDownTimeout() {
		return this.portDownTimeout;
	}

	public void setPortDownTimeout(String portDownTimeout) {
		this.portDownTimeout = portDownTimeout;
	}

	public String getPortDownIoRetryCount() {
		return this.portDownIoRetryCount;
	}

	public void setPortDownIoRetryCount(String portDownIoRetryCount) {
		this.portDownIoRetryCount = portDownIoRetryCount;
	}

	public String getLinkDownTimeout() {
		return this.linkDownTimeout;
	}

	public void setLinkDownTimeout(String linkDownTimeout) {
		this.linkDownTimeout = linkDownTimeout;
	}

	public String getFcpErrorRecovery() {
		return this.fcpErrorRecovery;
	}

	public void setFcpErrorRecovery(String fcpErrorRecovery) {
		this.fcpErrorRecovery = fcpErrorRecovery;
	}

}
