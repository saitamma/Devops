package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfOSBootWatchdogTimerPolicy")
public class Biosvfosbootwatchdogtimerpolicy implements java.io.Serializable {

	private static final long serialVersionUID = -5528061470230551474L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute
	private String childAction;
	@XmlAttribute
	private String rn;
	@XmlAttribute(name="vpOSBootWatchdogTimerPolicy")
	private String vpOsbootWatchdogTimerPolicy;

	public Biosvfosbootwatchdogtimerpolicy() {
	}

	public Biosvfosbootwatchdogtimerpolicy(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvfosbootwatchdogtimerpolicy(Biosvprofile biosvprofile,
			String childAction, String rn, String vpOsbootWatchdogTimerPolicy) {
		this.biosvprofile = biosvprofile;
		this.childAction = childAction;
		this.rn = rn;
		this.vpOsbootWatchdogTimerPolicy = vpOsbootWatchdogTimerPolicy;
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

	public String getVpOsbootWatchdogTimerPolicy() {
		return this.vpOsbootWatchdogTimerPolicy;
	}

	public void setVpOsbootWatchdogTimerPolicy(
			String vpOsbootWatchdogTimerPolicy) {
		this.vpOsbootWatchdogTimerPolicy = vpOsbootWatchdogTimerPolicy;
	}

}
