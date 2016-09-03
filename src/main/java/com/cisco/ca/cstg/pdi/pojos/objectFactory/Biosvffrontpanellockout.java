package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfFrontPanelLockout")
public class Biosvffrontpanellockout implements java.io.Serializable {

	private static final long serialVersionUID = 513648528423300015L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute
	private String vpFrontPanelLockout;

	public Biosvffrontpanellockout() {
	}

	public Biosvffrontpanellockout(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvffrontpanellockout(Biosvprofile biosvprofile,
			String childAction, String rn, String vpFrontPanelLockout) {
		this.biosvprofile = biosvprofile;
		this.vpFrontPanelLockout = vpFrontPanelLockout;
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

	public String getVpFrontPanelLockout() {
		return this.vpFrontPanelLockout;
	}

	public void setVpFrontPanelLockout(String vpFrontPanelLockout) {
		this.vpFrontPanelLockout = vpFrontPanelLockout;
	}

}
