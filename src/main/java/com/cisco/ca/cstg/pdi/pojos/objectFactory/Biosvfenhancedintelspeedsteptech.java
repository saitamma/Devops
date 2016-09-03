package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfEnhancedIntelSpeedStepTech")
public class Biosvfenhancedintelspeedsteptech implements java.io.Serializable {

	private static final long serialVersionUID = -2660716125792463524L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute
	private String childAction;
	@XmlAttribute
	private String rn;
	@XmlAttribute
	private String vpEnhancedIntelSpeedStepTech;

	public Biosvfenhancedintelspeedsteptech() {
	}

	public Biosvfenhancedintelspeedsteptech(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvfenhancedintelspeedsteptech(Biosvprofile biosvprofile,
			String childAction, String rn, String vpEnhancedIntelSpeedStepTech) {
		this.biosvprofile = biosvprofile;
		this.childAction = childAction;
		this.rn = rn;
		this.vpEnhancedIntelSpeedStepTech = vpEnhancedIntelSpeedStepTech;
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

	public String getVpEnhancedIntelSpeedStepTech() {
		return this.vpEnhancedIntelSpeedStepTech;
	}

	public void setVpEnhancedIntelSpeedStepTech(
			String vpEnhancedIntelSpeedStepTech) {
		this.vpEnhancedIntelSpeedStepTech = vpEnhancedIntelSpeedStepTech;
	}

}
