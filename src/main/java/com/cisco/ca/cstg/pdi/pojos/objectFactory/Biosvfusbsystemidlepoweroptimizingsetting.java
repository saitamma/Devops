package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfUSBSystemIdlePowerOptimizingSetting")
public class Biosvfusbsystemidlepoweroptimizingsetting implements
		java.io.Serializable {

	private static final long serialVersionUID = 5407229013159112006L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute
	private String childAction;
	@XmlAttribute
	private String rn;
	@XmlAttribute(name="vpUSBIdlePowerOptimizing")
	private String vpUsbidlePowerOptimizing;

	public Biosvfusbsystemidlepoweroptimizingsetting() {
	}

	public Biosvfusbsystemidlepoweroptimizingsetting(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvfusbsystemidlepoweroptimizingsetting(Biosvprofile biosvprofile,
			String childAction, String rn, String vpUsbidlePowerOptimizing) {
		this.biosvprofile = biosvprofile;
		this.childAction = childAction;
		this.rn = rn;
		this.vpUsbidlePowerOptimizing = vpUsbidlePowerOptimizing;
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

	public String getVpUsbidlePowerOptimizing() {
		return this.vpUsbidlePowerOptimizing;
	}

	public void setVpUsbidlePowerOptimizing(String vpUsbidlePowerOptimizing) {
		this.vpUsbidlePowerOptimizing = vpUsbidlePowerOptimizing;
	}

}
