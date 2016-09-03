package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfPackageCStateLimit")
public class Biosvfpackagecstatelimit implements java.io.Serializable {

	private static final long serialVersionUID = 4903670470190825657L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute
	private String childAction;
	@XmlAttribute
	private String rn;
	@XmlAttribute(name="vpPackageCStateLimit")
	private String vpPackageCstateLimit;

	public Biosvfpackagecstatelimit() {
	}

	public Biosvfpackagecstatelimit(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvfpackagecstatelimit(Biosvprofile biosvprofile,
			String childAction, String rn, String vpPackageCstateLimit) {
		this.biosvprofile = biosvprofile;
		this.childAction = childAction;
		this.rn = rn;
		this.vpPackageCstateLimit = vpPackageCstateLimit;
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

	public String getVpPackageCstateLimit() {
		return this.vpPackageCstateLimit;
	}

	public void setVpPackageCstateLimit(String vpPackageCstateLimit) {
		this.vpPackageCstateLimit = vpPackageCstateLimit;
	}

}
