package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "vnicFc")
public class Vnicfc implements java.io.Serializable {

	private static final long serialVersionUID = 7259153950837661845L;
	private int primaryKey;
	
	private Lsserver lsserver;
	private Vnicsanconnpolicy vnicSanConnPolicy;
	
	@XmlAttribute
	private String switchId;
	
	@XmlAttribute
	private String statsPolicyName;
	
	@XmlAttribute
	private String order;
	
	@XmlAttribute
	private String adminVcon;
	
	@XmlAttribute
	private String qosPolicyName;
	
	@XmlAttribute
	private String pinToGroupName;
	
	@XmlAttribute
	private String persBind;
	
	@XmlAttribute
	private String nwTemplName;
	
	@XmlAttribute
	private String maxDataFieldSize;
	
	@XmlAttribute
	private String identPoolName;
	
	@XmlAttribute
	private String adaptorProfileName;
	
	@XmlAttribute
	private String persBindClear;
	
	@XmlAttribute
	private String name;
	
	@XmlAttribute
	private String addr;
	
	@XmlElement
	private List<Vnicfcif> vnicFcIf = new ArrayList<Vnicfcif>();

	public Vnicfc() {
	}

	public Vnicfc(int primaryKey, Lsserver lsserver,
			Vnicsanconnpolicy vnicsanconnpolicy) {
		this.primaryKey = primaryKey;
		this.lsserver = lsserver;
		this.vnicSanConnPolicy = vnicsanconnpolicy;
	}

	public Vnicfc(int primaryKey, Lsserver lsserver,
			Vnicsanconnpolicy vnicsanconnpolicy, String switchId,
			String statsPolicyName, String order, String adminVcon,
			String qosPolicyName, String pinToGroupName, String persBind,
			String nwTemplName, String maxDataFieldSize, String identPoolName,
			String adaptorProfileName, String persBindClear, String name,
			String addr, List<Vnicfcif> vnicfcifs) {
		this.primaryKey = primaryKey;
		this.lsserver = lsserver;
		this.vnicSanConnPolicy = vnicsanconnpolicy;
		this.switchId = switchId;
		this.statsPolicyName = statsPolicyName;
		this.order = order;
		this.adminVcon = adminVcon;
		this.qosPolicyName = qosPolicyName;
		this.pinToGroupName = pinToGroupName;
		this.persBind = persBind;
		this.nwTemplName = nwTemplName;
		this.maxDataFieldSize = maxDataFieldSize;
		this.identPoolName = identPoolName;
		this.adaptorProfileName = adaptorProfileName;
		this.persBindClear = persBindClear;
		this.name = name;
		this.addr = addr;
		this.vnicFcIf = vnicfcifs;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Lsserver getLsserver() {
		return this.lsserver;
	}

	public void setLsserver(Lsserver lsserver) {
		this.lsserver = lsserver;
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

	public String getOrder() {
		return this.order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getAdminVcon() {
		return this.adminVcon;
	}

	public void setAdminVcon(String adminVcon) {
		this.adminVcon = adminVcon;
	}

	public String getQosPolicyName() {
		return this.qosPolicyName;
	}

	public void setQosPolicyName(String qosPolicyName) {
		this.qosPolicyName = qosPolicyName;
	}

	public String getPinToGroupName() {
		return this.pinToGroupName;
	}

	public void setPinToGroupName(String pinToGroupName) {
		this.pinToGroupName = pinToGroupName;
	}

	public String getPersBind() {
		return this.persBind;
	}

	public void setPersBind(String persBind) {
		this.persBind = persBind;
	}

	public String getNwTemplName() {
		return this.nwTemplName;
	}

	public void setNwTemplName(String nwTemplName) {
		this.nwTemplName = nwTemplName;
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

	public String getAdaptorProfileName() {
		return this.adaptorProfileName;
	}

	public void setAdaptorProfileName(String adaptorProfileName) {
		this.adaptorProfileName = adaptorProfileName;
	}

	public String getPersBindClear() {
		return this.persBindClear;
	}

	public void setPersBindClear(String persBindClear) {
		this.persBindClear = persBindClear;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return this.addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Vnicsanconnpolicy getVnicSanConnPolicy() {
		return vnicSanConnPolicy;
	}

	public void setVnicSanConnPolicy(Vnicsanconnpolicy vnicSanConnPolicy) {
		this.vnicSanConnPolicy = vnicSanConnPolicy;
	}

	public List<Vnicfcif> getVnicFcIf() {
		return vnicFcIf;
	}

	public void setVnicFcIf(List<Vnicfcif> vnicFcIf) {
		this.vnicFcIf = vnicFcIf;
	}
}
