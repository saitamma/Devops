package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfPCISlotLinkSpeed")
public class Biosvfpcislotlinkspeed implements java.io.Serializable {

	private static final long serialVersionUID = 4144890964239660707L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute
	private String childAction;
	@XmlAttribute
	private String rn;
	@XmlAttribute(name="vpPCIeSlot10LinkSpeed")
	private String vpPcieSlot10linkSpeed;
	@XmlAttribute(name="vpPCIeSlot1LinkSpeed")
	private String vpPcieSlot1linkSpeed;
	@XmlAttribute(name="vpPCIeSlot2LinkSpeed")
	private String vpPcieSlot2linkSpeed;
	@XmlAttribute(name="vpPCIeSlot3LinkSpeed")
	private String vpPcieSlot3linkSpeed;
	@XmlAttribute(name="vpPCIeSlot4LinkSpeed")
	private String vpPcieSlot4linkSpeed;
	@XmlAttribute(name="vpPCIeSlot5LinkSpeed")
	private String vpPcieSlot5linkSpeed;
	@XmlAttribute(name="vpPCIeSlot6LinkSpeed")
	private String vpPcieSlot6linkSpeed;
	@XmlAttribute(name="vpPCIeSlot7LinkSpeed")
	private String vpPcieSlot7linkSpeed;
	@XmlAttribute(name="vpPCIeSlot8LinkSpeed")
	private String vpPcieSlot8linkSpeed;
	@XmlAttribute(name="vpPCIeSlot9LinkSpeed")
	private String vpPcieSlot9linkSpeed;

	public Biosvfpcislotlinkspeed() {
	}

	public Biosvfpcislotlinkspeed(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvfpcislotlinkspeed(Biosvprofile biosvprofile,
			String childAction, String rn, String vpPcieSlot10linkSpeed,
			String vpPcieSlot1linkSpeed, String vpPcieSlot2linkSpeed,
			String vpPcieSlot3linkSpeed, String vpPcieSlot4linkSpeed,
			String vpPcieSlot5linkSpeed, String vpPcieSlot6linkSpeed,
			String vpPcieSlot7linkSpeed, String vpPcieSlot8linkSpeed,
			String vpPcieSlot9linkSpeed) {
		this.biosvprofile = biosvprofile;
		this.childAction = childAction;
		this.rn = rn;
		this.vpPcieSlot10linkSpeed = vpPcieSlot10linkSpeed;
		this.vpPcieSlot1linkSpeed = vpPcieSlot1linkSpeed;
		this.vpPcieSlot2linkSpeed = vpPcieSlot2linkSpeed;
		this.vpPcieSlot3linkSpeed = vpPcieSlot3linkSpeed;
		this.vpPcieSlot4linkSpeed = vpPcieSlot4linkSpeed;
		this.vpPcieSlot5linkSpeed = vpPcieSlot5linkSpeed;
		this.vpPcieSlot6linkSpeed = vpPcieSlot6linkSpeed;
		this.vpPcieSlot7linkSpeed = vpPcieSlot7linkSpeed;
		this.vpPcieSlot8linkSpeed = vpPcieSlot8linkSpeed;
		this.vpPcieSlot9linkSpeed = vpPcieSlot9linkSpeed;
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

	public String getVpPcieSlot10linkSpeed() {
		return this.vpPcieSlot10linkSpeed;
	}

	public void setVpPcieSlot10linkSpeed(String vpPcieSlot10linkSpeed) {
		this.vpPcieSlot10linkSpeed = vpPcieSlot10linkSpeed;
	}

	public String getVpPcieSlot1linkSpeed() {
		return this.vpPcieSlot1linkSpeed;
	}

	public void setVpPcieSlot1linkSpeed(String vpPcieSlot1linkSpeed) {
		this.vpPcieSlot1linkSpeed = vpPcieSlot1linkSpeed;
	}

	public String getVpPcieSlot2linkSpeed() {
		return this.vpPcieSlot2linkSpeed;
	}

	public void setVpPcieSlot2linkSpeed(String vpPcieSlot2linkSpeed) {
		this.vpPcieSlot2linkSpeed = vpPcieSlot2linkSpeed;
	}

	public String getVpPcieSlot3linkSpeed() {
		return this.vpPcieSlot3linkSpeed;
	}

	public void setVpPcieSlot3linkSpeed(String vpPcieSlot3linkSpeed) {
		this.vpPcieSlot3linkSpeed = vpPcieSlot3linkSpeed;
	}

	public String getVpPcieSlot4linkSpeed() {
		return this.vpPcieSlot4linkSpeed;
	}

	public void setVpPcieSlot4linkSpeed(String vpPcieSlot4linkSpeed) {
		this.vpPcieSlot4linkSpeed = vpPcieSlot4linkSpeed;
	}

	public String getVpPcieSlot5linkSpeed() {
		return this.vpPcieSlot5linkSpeed;
	}

	public void setVpPcieSlot5linkSpeed(String vpPcieSlot5linkSpeed) {
		this.vpPcieSlot5linkSpeed = vpPcieSlot5linkSpeed;
	}

	public String getVpPcieSlot6linkSpeed() {
		return this.vpPcieSlot6linkSpeed;
	}

	public void setVpPcieSlot6linkSpeed(String vpPcieSlot6linkSpeed) {
		this.vpPcieSlot6linkSpeed = vpPcieSlot6linkSpeed;
	}

	public String getVpPcieSlot7linkSpeed() {
		return this.vpPcieSlot7linkSpeed;
	}

	public void setVpPcieSlot7linkSpeed(String vpPcieSlot7linkSpeed) {
		this.vpPcieSlot7linkSpeed = vpPcieSlot7linkSpeed;
	}

	public String getVpPcieSlot8linkSpeed() {
		return this.vpPcieSlot8linkSpeed;
	}

	public void setVpPcieSlot8linkSpeed(String vpPcieSlot8linkSpeed) {
		this.vpPcieSlot8linkSpeed = vpPcieSlot8linkSpeed;
	}

	public String getVpPcieSlot9linkSpeed() {
		return this.vpPcieSlot9linkSpeed;
	}

	public void setVpPcieSlot9linkSpeed(String vpPcieSlot9linkSpeed) {
		this.vpPcieSlot9linkSpeed = vpPcieSlot9linkSpeed;
	}

}
