package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfOSBootWatchdogTimer")
public class Biosvfosbootwatchdogtimer implements java.io.Serializable {

	private static final long serialVersionUID = 6613367826299350695L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute
	private String childAction;
	@XmlAttribute
	private String rn;
	@XmlAttribute(name="vpOSBootWatchdogTimer")
	private String vpOsbootWatchdogTimer;

	public Biosvfosbootwatchdogtimer() {
	}

	public Biosvfosbootwatchdogtimer(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvfosbootwatchdogtimer(Biosvprofile biosvprofile,
			String childAction, String rn, String vpOsbootWatchdogTimer) {
		this.biosvprofile = biosvprofile;
		this.childAction = childAction;
		this.rn = rn;
		this.vpOsbootWatchdogTimer = vpOsbootWatchdogTimer;
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

	public String getVpOsbootWatchdogTimer() {
		return this.vpOsbootWatchdogTimer;
	}

	public void setVpOsbootWatchdogTimer(String vpOsbootWatchdogTimer) {
		this.vpOsbootWatchdogTimer = vpOsbootWatchdogTimer;
	}

}
