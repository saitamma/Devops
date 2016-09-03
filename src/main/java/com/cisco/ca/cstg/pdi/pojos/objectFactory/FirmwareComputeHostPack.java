package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="firmwareComputeHostPack")
public class FirmwareComputeHostPack implements java.io.Serializable {

	private static final long serialVersionUID = -3346516521287684158L;
	private Integer primaryKey;
	private Orgorg orgorg;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String descr;
	@XmlAttribute
	private String bladeBundleVersion;
	@XmlAttribute
	private String ignoreCompCheck;
	@XmlAttribute(name="mode")
	private String firmwareMode;
	@XmlAttribute
	private String policyOwner;
	@XmlAttribute
	private String rackBundleVersion;
	@XmlAttribute
	private String stageSize;
	@XmlAttribute
	private String updateTrigger;

	public FirmwareComputeHostPack() {
	}

	public FirmwareComputeHostPack(Orgorg orgorg) {
		this.orgorg = orgorg;
	}

	public FirmwareComputeHostPack(Orgorg orgorg, String name, String descr,
			String bladeBundleVersion, String ignoreCompCheck,
			String firmwareMode, String policyOwner, String rackBundleVersion,
			String stageSize, String updateTrigger) {
		this.orgorg = orgorg;
		this.name = name;
		this.descr = descr;
		this.bladeBundleVersion = bladeBundleVersion;
		this.ignoreCompCheck = ignoreCompCheck;
		this.firmwareMode = firmwareMode;
		this.policyOwner = policyOwner;
		this.rackBundleVersion = rackBundleVersion;
		this.stageSize = stageSize;
		this.updateTrigger = updateTrigger;
	}

	public Integer getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(Integer primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Orgorg getOrgorg() {
		return this.orgorg;
	}

	public void setOrgorg(Orgorg orgorg) {
		this.orgorg = orgorg;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getBladeBundleVersion() {
		return this.bladeBundleVersion;
	}

	public void setBladeBundleVersion(String bladeBundleVersion) {
		this.bladeBundleVersion = bladeBundleVersion;
	}

	public String getIgnoreCompCheck() {
		return this.ignoreCompCheck;
	}

	public void setIgnoreCompCheck(String ignoreCompCheck) {
		this.ignoreCompCheck = ignoreCompCheck;
	}

	public String getFirmwareMode() {
		return this.firmwareMode;
	}

	public void setFirmwareMode(String firmwareMode) {
		this.firmwareMode = firmwareMode;
	}

	public String getPolicyOwner() {
		return this.policyOwner;
	}

	public void setPolicyOwner(String policyOwner) {
		this.policyOwner = policyOwner;
	}

	public String getRackBundleVersion() {
		return this.rackBundleVersion;
	}

	public void setRackBundleVersion(String rackBundleVersion) {
		this.rackBundleVersion = rackBundleVersion;
	}

	public String getStageSize() {
		return this.stageSize;
	}

	public void setStageSize(String stageSize) {
		this.stageSize = stageSize;
	}

	public String getUpdateTrigger() {
		return this.updateTrigger;
	}

	public void setUpdateTrigger(String updateTrigger) {
		this.updateTrigger = updateTrigger;
	}

}
