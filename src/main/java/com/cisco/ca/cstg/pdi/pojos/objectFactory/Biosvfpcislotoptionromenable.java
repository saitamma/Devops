package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfPCISlotOptionROMEnable")
public class Biosvfpcislotoptionromenable implements java.io.Serializable {

	private static final long serialVersionUID = -2928144565868531443L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute
	private String childAction;
	@XmlAttribute
	private String rn;
	@XmlAttribute(name="vpPCIeSlotSASOptionROM")
	private String vpPcieSlotSasoptionRom;
	@XmlAttribute
	private String vpSlot10state;
	@XmlAttribute
	private String vpSlot1state;
	@XmlAttribute
	private String vpSlot2state;
	@XmlAttribute
	private String vpSlot3state;
	@XmlAttribute
	private String vpSlot4state;
	@XmlAttribute
	private String vpSlot5state;
	@XmlAttribute
	private String vpSlot6state;
	@XmlAttribute
	private String vpSlot7state;
	@XmlAttribute
	private String vpSlot8state;
	@XmlAttribute
	private String vpSlot9state;
	@XmlAttribute
	private String vpSlotMezzState;

	public Biosvfpcislotoptionromenable() {
	}

	public Biosvfpcislotoptionromenable(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvfpcislotoptionromenable(Biosvprofile biosvprofile,
			String childAction, String rn, String vpPcieSlotSasoptionRom,
			String vpSlot10state, String vpSlot1state, String vpSlot2state,
			String vpSlot3state, String vpSlot4state, String vpSlot5state,
			String vpSlot6state, String vpSlot7state, String vpSlot8state,
			String vpSlot9state, String vpSlotMezzState) {
		this.biosvprofile = biosvprofile;
		this.childAction = childAction;
		this.rn = rn;
		this.vpPcieSlotSasoptionRom = vpPcieSlotSasoptionRom;
		this.vpSlot10state = vpSlot10state;
		this.vpSlot1state = vpSlot1state;
		this.vpSlot2state = vpSlot2state;
		this.vpSlot3state = vpSlot3state;
		this.vpSlot4state = vpSlot4state;
		this.vpSlot5state = vpSlot5state;
		this.vpSlot6state = vpSlot6state;
		this.vpSlot7state = vpSlot7state;
		this.vpSlot8state = vpSlot8state;
		this.vpSlot9state = vpSlot9state;
		this.vpSlotMezzState = vpSlotMezzState;
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

	public String getVpPcieSlotSasoptionRom() {
		return this.vpPcieSlotSasoptionRom;
	}

	public void setVpPcieSlotSasoptionRom(String vpPcieSlotSasoptionRom) {
		this.vpPcieSlotSasoptionRom = vpPcieSlotSasoptionRom;
	}

	public String getVpSlot10state() {
		return this.vpSlot10state;
	}

	public void setVpSlot10state(String vpSlot10state) {
		this.vpSlot10state = vpSlot10state;
	}

	public String getVpSlot1state() {
		return this.vpSlot1state;
	}

	public void setVpSlot1state(String vpSlot1state) {
		this.vpSlot1state = vpSlot1state;
	}

	public String getVpSlot2state() {
		return this.vpSlot2state;
	}

	public void setVpSlot2state(String vpSlot2state) {
		this.vpSlot2state = vpSlot2state;
	}

	public String getVpSlot3state() {
		return this.vpSlot3state;
	}

	public void setVpSlot3state(String vpSlot3state) {
		this.vpSlot3state = vpSlot3state;
	}

	public String getVpSlot4state() {
		return this.vpSlot4state;
	}

	public void setVpSlot4state(String vpSlot4state) {
		this.vpSlot4state = vpSlot4state;
	}

	public String getVpSlot5state() {
		return this.vpSlot5state;
	}

	public void setVpSlot5state(String vpSlot5state) {
		this.vpSlot5state = vpSlot5state;
	}

	public String getVpSlot6state() {
		return this.vpSlot6state;
	}

	public void setVpSlot6state(String vpSlot6state) {
		this.vpSlot6state = vpSlot6state;
	}

	public String getVpSlot7state() {
		return this.vpSlot7state;
	}

	public void setVpSlot7state(String vpSlot7state) {
		this.vpSlot7state = vpSlot7state;
	}

	public String getVpSlot8state() {
		return this.vpSlot8state;
	}

	public void setVpSlot8state(String vpSlot8state) {
		this.vpSlot8state = vpSlot8state;
	}

	public String getVpSlot9state() {
		return this.vpSlot9state;
	}

	public void setVpSlot9state(String vpSlot9state) {
		this.vpSlot9state = vpSlot9state;
	}

	public String getVpSlotMezzState() {
		return this.vpSlotMezzState;
	}

	public void setVpSlotMezzState(String vpSlotMezzState) {
		this.vpSlotMezzState = vpSlotMezzState;
	}

}
