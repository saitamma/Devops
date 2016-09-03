package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfUSBBootConfig")
public class Biosvfusbbootconfig implements java.io.Serializable {

	private static final long serialVersionUID = 743370911230352507L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute
	private String childAction;
	@XmlAttribute
	private String rn;
	@XmlAttribute(name="vpLegacyUSBSupport")
	private String vpLegacyUsbsupport;
	@XmlAttribute(name="vpMakeDeviceNonBootable")
	private String vpMakeDeviceNonBootable;

	public Biosvfusbbootconfig() {
	}

	public Biosvfusbbootconfig(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvfusbbootconfig(Biosvprofile biosvprofile, String childAction,
			String rn, String vpLegacyUsbsupport, String vpMakeDeviceNonBootable) {
		this.biosvprofile = biosvprofile;
		this.childAction = childAction;
		this.rn = rn;
		this.vpLegacyUsbsupport = vpLegacyUsbsupport;
		this.vpMakeDeviceNonBootable = vpMakeDeviceNonBootable;
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

	public String getVpLegacyUsbsupport() {
		return this.vpLegacyUsbsupport;
	}

	public void setVpLegacyUsbsupport(String vpLegacyUsbsupport) {
		this.vpLegacyUsbsupport = vpLegacyUsbsupport;
	}

	public String getVpMakeDeviceNonBootable() {
		return this.vpMakeDeviceNonBootable;
	}

	public void setVpMakeDeviceNonBootable(String vpMakeDeviceNonBootable) {
		this.vpMakeDeviceNonBootable = vpMakeDeviceNonBootable;
	}

}
