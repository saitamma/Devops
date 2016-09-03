package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfAllUSBDevices")
public class Biosvfallusbdevices implements java.io.Serializable {

	private static final long serialVersionUID = -7237697110889133433L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute(name="vpAllUSBDevices")
	private String vpAllUsbdevices;

	public Biosvfallusbdevices() {
	}

	public Biosvfallusbdevices(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvfallusbdevices(Biosvprofile biosvprofile, String childAction,
			String rn, String vpAllUsbdevices) {
		this.biosvprofile = biosvprofile;
		this.vpAllUsbdevices = vpAllUsbdevices;
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

	public String getVpAllUsbdevices() {
		return this.vpAllUsbdevices;
	}

	public void setVpAllUsbdevices(String vpAllUsbdevices) {
		this.vpAllUsbdevices = vpAllUsbdevices;
	}

}
