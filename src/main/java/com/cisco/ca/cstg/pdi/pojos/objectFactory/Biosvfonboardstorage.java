package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfOnboardStorage")
public class Biosvfonboardstorage implements java.io.Serializable {

	private static final long serialVersionUID = -6219553391261655043L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute
	private String childAction;
	@XmlAttribute
	private String rn;
	@XmlAttribute(name="vpOnboardSCUStorageSupport")
	private String vpOnboardScustorageSupport;

	public Biosvfonboardstorage() {
	}

	public Biosvfonboardstorage(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvfonboardstorage(Biosvprofile biosvprofile, String childAction,
			String rn, String vpOnboardScustorageSupport) {
		this.biosvprofile = biosvprofile;
		this.childAction = childAction;
		this.rn = rn;
		this.vpOnboardScustorageSupport = vpOnboardScustorageSupport;
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

	public String getVpOnboardScustorageSupport() {
		return this.vpOnboardScustorageSupport;
	}

	public void setVpOnboardScustorageSupport(String vpOnboardScustorageSupport) {
		this.vpOnboardScustorageSupport = vpOnboardScustorageSupport;
	}

}
