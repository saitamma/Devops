package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
// Generated Nov 11, 2014 10:41:23 AM by Hibernate Tools 4.0.0
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "fabricEthLinkProfile")
@XmlAccessorType(XmlAccessType.FIELD)
public class Fabricethlinkprofile implements java.io.Serializable {

	private static final long serialVersionUID = -3826477780758298229L;
	private int primaryKey;
	private Fabriclancloud fabriclancloud;
	
	@XmlAttribute(name="policyOwner")
	private String policyOwner;
	
	@XmlAttribute(name="descr")
	private String descr;
	
	@XmlAttribute(name="udldLinkPolicyName")
	private String udldLinkPolicyName;
	
	@XmlAttribute(name="name")
	private String name;
	
	@XmlAttribute(name="cdpLinkPolicyName")
	private String cdpLinkPolicyName;

	public Fabricethlinkprofile() {
	}

	public Fabricethlinkprofile(int primaryKey, Fabriclancloud fabriclancloud) {
		this.primaryKey = primaryKey;
		this.fabriclancloud = fabriclancloud;
	}

	public Fabricethlinkprofile(int primaryKey, Fabriclancloud fabriclancloud,
			String policyOwner, String descr, String udldLinkPolicyName,
			String name, String cdpLinkPolicyName) {
		this.primaryKey = primaryKey;
		this.fabriclancloud = fabriclancloud;
		this.policyOwner = policyOwner;
		this.descr = descr;
		this.udldLinkPolicyName = udldLinkPolicyName;
		this.name = name;
		this.cdpLinkPolicyName = cdpLinkPolicyName;
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

	public String getUdldLinkPolicyName() {
		return this.udldLinkPolicyName;
	}

	public void setUdldLinkPolicyName(String udldLinkPolicyName) {
		this.udldLinkPolicyName = udldLinkPolicyName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCdpLinkPolicyName() {
		return this.cdpLinkPolicyName;
	}

	public void setCdpLinkPolicyName(String cdpLinkPolicyName) {
		this.cdpLinkPolicyName = cdpLinkPolicyName;
	}

}
