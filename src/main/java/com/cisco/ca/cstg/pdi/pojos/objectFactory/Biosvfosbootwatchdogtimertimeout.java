package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfOSBootWatchdogTimerTimeout")
public class Biosvfosbootwatchdogtimertimeout implements java.io.Serializable {

	private static final long serialVersionUID = 5287709761007581355L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute
	private String childAction;
	@XmlAttribute
	private String rn;
	@XmlAttribute(name="vpOSBootWatchdogTimerTimeout")
	private String vpOsbootWatchdogTimerTimeout;

	public Biosvfosbootwatchdogtimertimeout() {
	}

	public Biosvfosbootwatchdogtimertimeout(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvfosbootwatchdogtimertimeout(Biosvprofile biosvprofile,
			String childAction, String rn, String vpOsbootWatchdogTimerTimeout) {
		this.biosvprofile = biosvprofile;
		this.childAction = childAction;
		this.rn = rn;
		this.vpOsbootWatchdogTimerTimeout = vpOsbootWatchdogTimerTimeout;
	}

	public Integer getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(Integer primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Biosvprofile getBiosvprofile() {
		return this.biosvprofile;
	}

	public void setBiosvprofile(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public String getChildAction() {
		return this.childAction;
	}

	public void setChildAction(String childAction) {
		this.childAction = childAction;
	}

	public String getRn() {
		return this.rn;
	}

	public void setRn(String rn) {
		this.rn = rn;
	}

	public String getVpOsbootWatchdogTimerTimeout() {
		return this.vpOsbootWatchdogTimerTimeout;
	}

	public void setVpOsbootWatchdogTimerTimeout(
			String vpOsbootWatchdogTimerTimeout) {
		this.vpOsbootWatchdogTimerTimeout = vpOsbootWatchdogTimerTimeout;
	}

}
