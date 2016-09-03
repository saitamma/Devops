package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfLOMPortsConfiguration")
public class Biosvflomportsconfiguration implements java.io.Serializable {

	private static final long serialVersionUID = 7578723550841326154L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute
	private String childAction;
	@XmlAttribute
	private String rn;
	@XmlAttribute(name="vpAllOnboardLOMPorts")
	private String vpAllOnboardLomports;
	@XmlAttribute(name="vpLOMPort0OptionROM")
	private String vpLomport0optionRom;
	@XmlAttribute(name="vpLOMPort1OptionROM")
	private String vpLomport1optionRom;
	@XmlAttribute(name="vpLOMPort2OptionROM")
	private String vpLomport2optionRom;
	@XmlAttribute(name="vpLOMPort3OptionROM")
	private String vpLomport3optionRom;

	public Biosvflomportsconfiguration() {
	}

	public Biosvflomportsconfiguration(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvflomportsconfiguration(Biosvprofile biosvprofile,
			String childAction, String rn, String vpAllOnboardLomports,
			String vpLomport0optionRom, String vpLomport1optionRom,
			String vpLomport2optionRom, String vpLomport3optionRom) {
		this.biosvprofile = biosvprofile;
		this.childAction = childAction;
		this.rn = rn;
		this.vpAllOnboardLomports = vpAllOnboardLomports;
		this.vpLomport0optionRom = vpLomport0optionRom;
		this.vpLomport1optionRom = vpLomport1optionRom;
		this.vpLomport2optionRom = vpLomport2optionRom;
		this.vpLomport3optionRom = vpLomport3optionRom;
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

	public String getVpAllOnboardLomports() {
		return this.vpAllOnboardLomports;
	}

	public void setVpAllOnboardLomports(String vpAllOnboardLomports) {
		this.vpAllOnboardLomports = vpAllOnboardLomports;
	}

	public String getVpLomport0optionRom() {
		return this.vpLomport0optionRom;
	}

	public void setVpLomport0optionRom(String vpLomport0optionRom) {
		this.vpLomport0optionRom = vpLomport0optionRom;
	}

	public String getVpLomport1optionRom() {
		return this.vpLomport1optionRom;
	}

	public void setVpLomport1optionRom(String vpLomport1optionRom) {
		this.vpLomport1optionRom = vpLomport1optionRom;
	}

	public String getVpLomport2optionRom() {
		return this.vpLomport2optionRom;
	}

	public void setVpLomport2optionRom(String vpLomport2optionRom) {
		this.vpLomport2optionRom = vpLomport2optionRom;
	}

	public String getVpLomport3optionRom() {
		return this.vpLomport3optionRom;
	}

	public void setVpLomport3optionRom(String vpLomport3optionRom) {
		this.vpLomport3optionRom = vpLomport3optionRom;
	}

}
