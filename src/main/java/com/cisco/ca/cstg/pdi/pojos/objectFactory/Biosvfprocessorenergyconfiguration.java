package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfProcessorEnergyConfiguration")
public class Biosvfprocessorenergyconfiguration implements java.io.Serializable {

	private static final long serialVersionUID = -4535657253789836176L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute
	private String childAction;
	@XmlAttribute
	private String rn;
	@XmlAttribute
	private String vpEnergyPerformance;
	@XmlAttribute
	private String vpPowerTechnology;

	public Biosvfprocessorenergyconfiguration() {
	}

	public Biosvfprocessorenergyconfiguration(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvfprocessorenergyconfiguration(Biosvprofile biosvprofile,
			String childAction, String rn, String vpEnergyPerformance,
			String vpPowerTechnology) {
		this.biosvprofile = biosvprofile;
		this.childAction = childAction;
		this.rn = rn;
		this.vpEnergyPerformance = vpEnergyPerformance;
		this.vpPowerTechnology = vpPowerTechnology;
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

	public String getVpEnergyPerformance() {
		return this.vpEnergyPerformance;
	}

	public void setVpEnergyPerformance(String vpEnergyPerformance) {
		this.vpEnergyPerformance = vpEnergyPerformance;
	}

	public String getVpPowerTechnology() {
		return this.vpPowerTechnology;
	}

	public void setVpPowerTechnology(String vpPowerTechnology) {
		this.vpPowerTechnology = vpPowerTechnology;
	}

}
