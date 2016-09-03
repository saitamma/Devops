package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "storageLocalDiskConfigPolicy")
public class Storagelocaldiskconfigpolicy implements java.io.Serializable {

	private static final long serialVersionUID = -2834547807873182156L;
	private int primaryKey;
	private Orgorg orgorg;
	
	@XmlAttribute
	private String policyOwner;
	
	@XmlAttribute
	private String descr;
	
	@XmlAttribute
	private String protectConfig;
	
	@XmlAttribute
	private String mode;
	
	@XmlAttribute
	private String flexFlashState;
	
	@XmlAttribute
	private String flexFlashRAIDReportingState;

	@XmlAttribute
	private String name;

	public Storagelocaldiskconfigpolicy() {
	}

	public Storagelocaldiskconfigpolicy(int primaryKey, Orgorg orgorg) {
		this.primaryKey = primaryKey;
		this.orgorg = orgorg;
	}

	public Storagelocaldiskconfigpolicy(int primaryKey, Orgorg orgorg,
			String policyOwner, String descr, String protectConfig,
			String mode, String flexFlashState,
			String flexFlashRaidreportingState, String name) {
		this.primaryKey = primaryKey;
		this.orgorg = orgorg;
		this.policyOwner = policyOwner;
		this.descr = descr;
		this.protectConfig = protectConfig;
		this.mode = mode;
		this.flexFlashState = flexFlashState;
		this.flexFlashRAIDReportingState = flexFlashRaidreportingState;
		this.name = name;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Orgorg getOrgorg() {
		return this.orgorg;
	}

	public void setOrgorg(Orgorg orgorg) {
		this.orgorg = orgorg;
	}

	public String getPolicyOwner() {
		return this.policyOwner;
	}

	public void setPolicyOwner(String policyOwner) {
		this.policyOwner = policyOwner;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getProtectConfig() {
		return this.protectConfig;
	}

	public void setProtectConfig(String protectConfig) {
		this.protectConfig = protectConfig;
	}

	public String getMode() {
		return this.mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getFlexFlashState() {
		return this.flexFlashState;
	}

	public void setFlexFlashState(String flexFlashState) {
		this.flexFlashState = flexFlashState;
	}

	public String getFlexFlashRAIDReportingState() {
		return flexFlashRAIDReportingState;
	}

	public void setFlexFlashRAIDReportingState(String flexFlashRAIDReportingState) {
		this.flexFlashRAIDReportingState = flexFlashRAIDReportingState;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
