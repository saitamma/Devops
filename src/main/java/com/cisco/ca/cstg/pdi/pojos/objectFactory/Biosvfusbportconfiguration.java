package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfUSBPortConfiguration")
public class Biosvfusbportconfiguration implements java.io.Serializable {

	private static final long serialVersionUID = -6598478600098208504L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute
	private String childAction;
	@XmlAttribute
	private String rn;
	@XmlAttribute(name="vpPort6064Emulation")
	private String vpPort6064emulation;
	@XmlAttribute(name="vpUSBPortFront")
	private String vpUsbportFront;
	@XmlAttribute(name="vpUSBPortInternal")
	private String vpUsbportInternal;
	@XmlAttribute(name="vpUSBPortKVM")
	private String vpUsbportKvm;
	@XmlAttribute(name="vpUSBPortRear")
	private String vpUsbportRear;
	@XmlAttribute(name="vpUSBPortSDCard")
	private String vpUsbportSdcard;
	@XmlAttribute(name="vpUSBPortVMedia")
	private String vpUsbportVmedia;

	public Biosvfusbportconfiguration() {
	}

	public Biosvfusbportconfiguration(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvfusbportconfiguration(Biosvprofile biosvprofile,
			String childAction, String rn, String vpPort6064emulation,
			String vpUsbportFront, String vpUsbportInternal,
			String vpUsbportKvm, String vpUsbportRear, String vpUsbportSdcard,
			String vpUsbportVmedia) {
		this.biosvprofile = biosvprofile;
		this.childAction = childAction;
		this.rn = rn;
		this.vpPort6064emulation = vpPort6064emulation;
		this.vpUsbportFront = vpUsbportFront;
		this.vpUsbportInternal = vpUsbportInternal;
		this.vpUsbportKvm = vpUsbportKvm;
		this.vpUsbportRear = vpUsbportRear;
		this.vpUsbportSdcard = vpUsbportSdcard;
		this.vpUsbportVmedia = vpUsbportVmedia;
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

	public String getVpPort6064emulation() {
		return this.vpPort6064emulation;
	}

	public void setVpPort6064emulation(String vpPort6064emulation) {
		this.vpPort6064emulation = vpPort6064emulation;
	}

	public String getVpUsbportFront() {
		return this.vpUsbportFront;
	}

	public void setVpUsbportFront(String vpUsbportFront) {
		this.vpUsbportFront = vpUsbportFront;
	}

	public String getVpUsbportInternal() {
		return this.vpUsbportInternal;
	}

	public void setVpUsbportInternal(String vpUsbportInternal) {
		this.vpUsbportInternal = vpUsbportInternal;
	}

	public String getVpUsbportKvm() {
		return this.vpUsbportKvm;
	}

	public void setVpUsbportKvm(String vpUsbportKvm) {
		this.vpUsbportKvm = vpUsbportKvm;
	}

	public String getVpUsbportRear() {
		return this.vpUsbportRear;
	}

	public void setVpUsbportRear(String vpUsbportRear) {
		this.vpUsbportRear = vpUsbportRear;
	}

	public String getVpUsbportSdcard() {
		return this.vpUsbportSdcard;
	}

	public void setVpUsbportSdcard(String vpUsbportSdcard) {
		this.vpUsbportSdcard = vpUsbportSdcard;
	}

	public String getVpUsbportVmedia() {
		return this.vpUsbportVmedia;
	}

	public void setVpUsbportVmedia(String vpUsbportVmedia) {
		this.vpUsbportVmedia = vpUsbportVmedia;
	}

}
