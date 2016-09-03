package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
// Generated Nov 11, 2014 10:41:23 AM by Hibernate Tools 4.0.0
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "fabricUdldLinkPolicy")
@XmlAccessorType(XmlAccessType.FIELD)
public class Fabricudldlinkpolicy implements java.io.Serializable {

	private static final long serialVersionUID = -3905193583591032684L;
	private int primaryKey;
	private Fabriclancloud fabriclancloud;
	
	@XmlAttribute(name = "policyOwner")
	private String policyOwner;
	
	@XmlAttribute(name = "descr")
	private String descr;
	
	@XmlAttribute(name = "name")
	private String name;
	
	@XmlAttribute(name = "mode")
	private String mode;
	
	@XmlAttribute(name = "adminState")
	private String adminState;

	public Fabricudldlinkpolicy() {
	}

	public Fabricudldlinkpolicy(int primaryKey, Fabriclancloud fabriclancloud) {
		this.primaryKey = primaryKey;
		this.fabriclancloud = fabriclancloud;
	}

	public Fabricudldlinkpolicy(int primaryKey, Fabriclancloud fabriclancloud,
			String policyOwner, String descr, String name, String mode,
			String adminState) {
		this.primaryKey = primaryKey;
		this.fabriclancloud = fabriclancloud;
		this.policyOwner = policyOwner;
		this.descr = descr;
		this.name = name;
		this.mode = mode;
		this.adminState = adminState;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Fabriclancloud getFabriclancloud() {
		return this.fabriclancloud;
	}

	public void setFabriclancloud(Fabriclancloud fabriclancloud) {
		this.fabriclancloud = fabriclancloud;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMode() {
		return this.mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getAdminState() {
		return this.adminState;
	}

	public void setAdminState(String adminState) {
		this.adminState = adminState;
	}

}
