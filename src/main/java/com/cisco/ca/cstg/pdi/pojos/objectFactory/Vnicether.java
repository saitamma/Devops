package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "vnicEther")
public class Vnicether implements java.io.Serializable {

	private static final long serialVersionUID = -7371828527256905372L;
	private int primaryKey;

	private Lsserver lsserver;
	private Vniclanconnpolicy vnicLanConnPolicy;

	@XmlAttribute
	private String statsPolicyName;

	@XmlAttribute
	private String order;

	@XmlAttribute
	private String adminVcon;

	@XmlAttribute
	private String switchId;

	@XmlAttribute
	private String qosPolicyName;

	@XmlAttribute
	private String pinToGroupName;

	@XmlAttribute
	private String nwTemplName;

	@XmlAttribute
	private String nwCtrlPolicyName;

	@XmlAttribute
	private String mtu;

	@XmlAttribute
	private String identPoolName;

	@XmlAttribute
	private String adaptorProfileName;

	@XmlAttribute
	private String name;

	private List<Vnicetherif> vnicEtherIf = new ArrayList<Vnicetherif>();

	public Vnicether() {
	}

	public Vnicether(int primaryKey, Lsserver lsserver,
			Vniclanconnpolicy vnicLanConnPolicy) {
		this.primaryKey = primaryKey;
		this.lsserver = lsserver;
		this.vnicLanConnPolicy = vnicLanConnPolicy;
	}

	public Vnicether(int primaryKey, Lsserver lsserver,
			Vniclanconnpolicy vnicLanConnPolicy, String statsPolicyName,
			String order, String adminVcon, String switchId,
			String qosPolicyName, String pinToGroupName, String nwTemplName,
			String nwCtrlPolicyName, String mtu, String identPoolName,
			String adaptorProfileName, String name,
			List<Vnicetherif> vnicetherifs) {
		this.primaryKey = primaryKey;
		this.lsserver = lsserver;
		this.vnicLanConnPolicy = vnicLanConnPolicy;
		this.statsPolicyName = statsPolicyName;
		this.order = order;
		this.adminVcon = adminVcon;
		this.switchId = switchId;
		this.qosPolicyName = qosPolicyName;
		this.pinToGroupName = pinToGroupName;
		this.nwTemplName = nwTemplName;
		this.nwCtrlPolicyName = nwCtrlPolicyName;
		this.mtu = mtu;
		this.identPoolName = identPoolName;
		this.adaptorProfileName = adaptorProfileName;
		this.name = name;
		this.vnicEtherIf = vnicetherifs;
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

	public Vniclanconnpolicy getVnicLanConnPolicy() {
		return vnicLanConnPolicy;
	}

	public void setVnicLanConnPolicy(Vniclanconnpolicy vnicLanConnPolicy) {
		this.vnicLanConnPolicy = vnicLanConnPolicy;
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

	public String getSwitchId() {
		return this.switchId;
	}

	public void setSwitchId(String switchId) {
		this.switchId = switchId;
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

	public String getNwTemplName() {
		return this.nwTemplName;
	}

	public void setNwTemplName(String nwTemplName) {
		this.nwTemplName = nwTemplName;
	}

	public String getNwCtrlPolicyName() {
		return this.nwCtrlPolicyName;
	}

	public void setNwCtrlPolicyName(String nwCtrlPolicyName) {
		this.nwCtrlPolicyName = nwCtrlPolicyName;
	}

	public String getMtu() {
		return this.mtu;
	}

	public void setMtu(String mtu) {
		this.mtu = mtu;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Vnicetherif> getVnicEtherIf() {
		return vnicEtherIf;
	}

	public void setVnicEtherIf(List<Vnicetherif> vnicEtherIf) {
		this.vnicEtherIf = vnicEtherIf;
	}
}
