package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "vnicSanConnTempl")
public class Vnicsanconntempl implements java.io.Serializable {

	private static final long serialVersionUID = -3564140263378090523L;
	
	private int primaryKey;
	
	private Orgorg orgOrg;
	
	@XmlAttribute(name="policyOwner")
	private String policyOwner;
	
	@XmlAttribute(name="descr")
	private String descr;
	
	@XmlAttribute(name="switchId")
	private String switchId;
	
	@XmlAttribute(name="statsPolicyName")
	private String statsPolicyName;
	
	@XmlAttribute(name="pinToGroupName")
	private String pinToGroupName;
	
	@XmlAttribute(name="templType")
	private String templType;
	
	@XmlAttribute(name="qosPolicyName")
	private String qosPolicyName;
	
	@XmlAttribute(name="name")
	private String name;
	
	@XmlAttribute(name="maxDataFieldSize")
	private String maxDataFieldSize;
	
	@XmlAttribute(name="identPoolName")
	private String identPoolName;
	
	@XmlElement
	private List<Vnicfcif> vnicFcIf = new ArrayList<Vnicfcif>();

	public Vnicsanconntempl() {
	}

	public Vnicsanconntempl(int primaryKey, Orgorg orgorg) {
		this.primaryKey = primaryKey;
		this.orgOrg = orgorg;
	}

	public Vnicsanconntempl(int primaryKey, Orgorg orgorg, String policyOwner,
			String descr, String switchId, String statsPolicyName,
			String pinToGroupName, String templType, String qosPolicyName,
			String name, String maxDataFieldSize, String identPoolName,
			List<Vnicfcif> vnicFcIf) {
		this.primaryKey = primaryKey;
		this.orgOrg = orgorg;
		this.policyOwner = policyOwner;
		this.descr = descr;
		this.switchId = switchId;
		this.statsPolicyName = statsPolicyName;
		this.pinToGroupName = pinToGroupName;
		this.templType = templType;
		this.qosPolicyName = qosPolicyName;
		this.name = name;
		this.maxDataFieldSize = maxDataFieldSize;
		this.identPoolName = identPoolName;
		this.vnicFcIf = vnicFcIf;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Orgorg getOrgOrg() {
		return this.orgOrg;
	}

	public void setOrgOrg(Orgorg orgorg) {
		this.orgOrg = orgorg;
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

	public String getSwitchId() {
		return this.switchId;
	}

	public void setSwitchId(String switchId) {
		this.switchId = switchId;
	}

	public String getStatsPolicyName() {
		return this.statsPolicyName;
	}

	public void setStatsPolicyName(String statsPolicyName) {
		this.statsPolicyName = statsPolicyName;
	}

	public String getPinToGroupName() {
		return this.pinToGroupName;
	}

	public void setPinToGroupName(String pinToGroupName) {
		this.pinToGroupName = pinToGroupName;
	}

	public String getTemplType() {
		return this.templType;
	}

	public void setTemplType(String templType) {
		this.templType = templType;
	}

	public String getQosPolicyName() {
		return this.qosPolicyName;
	}

	public void setQosPolicyName(String qosPolicyName) {
		this.qosPolicyName = qosPolicyName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMaxDataFieldSize() {
		return this.maxDataFieldSize;
	}

	public void setMaxDataFieldSize(String maxDataFieldSize) {
		this.maxDataFieldSize = maxDataFieldSize;
	}

	public String getIdentPoolName() {
		return this.identPoolName;
	}

	public void setIdentPoolName(String identPoolName) {
		this.identPoolName = identPoolName;
	}

	public List<Vnicfcif> getVnicFcIf() {
		return vnicFcIf;
	}

	public void setVnicFcIf(List<Vnicfcif> vnicFcIf) {
		this.vnicFcIf = vnicFcIf;
	}
}
