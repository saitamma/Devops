package com.cisco.ca.cstg.pdi.pojos.objectFactory;
// Generated Nov 11, 2014 10:41:23 AM by Hibernate Tools 4.0.0

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "fabricLanCloud")
@XmlAccessorType(XmlAccessType.FIELD)
public class Fabriclancloud implements java.io.Serializable {

	private static final long serialVersionUID = 5268258145843672411L;
	private int primaryKey;
	private Fabricep fabricep;
	
	@XmlAttribute(name="vlanCompression")
	private String vlanCompression;
	
	@XmlAttribute(name="mode")
	private String mode;
	
	@XmlAttribute(name="macAging")
	private String macAging;
	
	@XmlAttribute(name="")
	private String extvmmVmnetworkSets;
	
	@XmlAttribute(name="")
	private String extvmmNetworkSets;
	
	@XmlAttribute(name="")
	private String vnicProfileSet;
	
	private List<Fabricethlinkprofile> fabricEthLinkProfile  = new ArrayList<Fabricethlinkprofile>();
	private List<Qosclassdefinition> qosclassDefinition  = new ArrayList<Qosclassdefinition>();
	private List<Fabricvlan> fabricVlan  = new ArrayList<Fabricvlan>();
	private List<Fabricethlan> fabricEthLan  = new ArrayList<Fabricethlan>();
	private List<Mgmtinbandprofile> mgmtInbandProfile  = new ArrayList<Mgmtinbandprofile>();
	private List<Fabricudldlinkpolicy> fabricUdldLinkPolicy  = new ArrayList<Fabricudldlinkpolicy>();
	private List<Statsthresholdpolicy> statsThresholdPolicy  = new ArrayList<Statsthresholdpolicy>();
	private List<Flowctrldefinition> flowctrlDefinition  = new ArrayList<Flowctrldefinition>();

	public Fabriclancloud() {
	}

	public Fabriclancloud(int primaryKey, Fabricep fabricep) {
		this.primaryKey = primaryKey;
		this.fabricep = fabricep;
	}

	public Fabriclancloud(int primaryKey, Fabricep fabricep,
			String vlanCompression, String mode, String macAging,
			String extvmmVmnetworkSets, String extvmmNetworkSets,
			String vnicProfileSet, List<Fabricethlinkprofile> fabricEthLinkProfile,
			List<Qosclassdefinition> qosclassDefinition, List<Fabricvlan> fabricVlan, List<Fabricethlan> fabricEthLan,
			List<Mgmtinbandprofile> mgmtInbandProfile, List<Fabricudldlinkpolicy> fabricUdldLinkPolicy,
			List<Statsthresholdpolicy> statsThresholdPolicy, List<Flowctrldefinition> flowctrlDefinition) {
		this.primaryKey = primaryKey;
		this.fabricep = fabricep;
		this.vlanCompression = vlanCompression;
		this.mode = mode;
		this.macAging = macAging;
		this.extvmmVmnetworkSets = extvmmVmnetworkSets;
		this.extvmmNetworkSets = extvmmNetworkSets;
		this.vnicProfileSet = vnicProfileSet;
		this.fabricEthLinkProfile = fabricEthLinkProfile;
		this.qosclassDefinition = qosclassDefinition;
		this.fabricVlan = fabricVlan;
		this.fabricEthLan = fabricEthLan;
		this.mgmtInbandProfile = mgmtInbandProfile;
		this.fabricUdldLinkPolicy = fabricUdldLinkPolicy;
		this.statsThresholdPolicy = statsThresholdPolicy;
		this.flowctrlDefinition = flowctrlDefinition;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Fabricep getFabricep() {
		return this.fabricep;
	}

	public void setFabricep(Fabricep fabricep) {
		this.fabricep = fabricep;
	}

	public String getVlanCompression() {
		return this.vlanCompression;
	}

	public void setVlanCompression(String vlanCompression) {
		this.vlanCompression = vlanCompression;
	}

	public String getMode() {
		return this.mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getMacAging() {
		return this.macAging;
	}

	public void setMacAging(String macAging) {
		this.macAging = macAging;
	}

	public String getExtvmmVmnetworkSets() {
		return this.extvmmVmnetworkSets;
	}

	public void setExtvmmVmnetworkSets(String extvmmVmnetworkSets) {
		this.extvmmVmnetworkSets = extvmmVmnetworkSets;
	}

	public String getExtvmmNetworkSets() {
		return this.extvmmNetworkSets;
	}

	public void setExtvmmNetworkSets(String extvmmNetworkSets) {
		this.extvmmNetworkSets = extvmmNetworkSets;
	}

	public String getVnicProfileSet() {
		return this.vnicProfileSet;
	}

	public void setVnicProfileSet(String vnicProfileSet) {
		this.vnicProfileSet = vnicProfileSet;
	}

	public List<Fabricethlinkprofile> getFabricEthLinkProfile() {
		return fabricEthLinkProfile;
	}

	public void setFabricEthLinkProfile(
			List<Fabricethlinkprofile> fabricEthLinkProfile) {
		this.fabricEthLinkProfile = fabricEthLinkProfile;
	}

	public List<Qosclassdefinition> getQosclassDefinition() {
		return qosclassDefinition;
	}

	public void setQosclassDefinition(List<Qosclassdefinition> qosclassDefinition) {
		this.qosclassDefinition = qosclassDefinition;
	}

	public List<Fabricvlan> getFabricVlan() {
		return fabricVlan;
	}

	public void setFabricVlan(List<Fabricvlan> fabricVlan) {
		this.fabricVlan = fabricVlan;
	}

	public List<Fabricethlan> getFabricEthLan() {
		return fabricEthLan;
	}

	public void setFabricEthLan(List<Fabricethlan> fabricEthLan) {
		this.fabricEthLan = fabricEthLan;
	}

	public List<Mgmtinbandprofile> getMgmtInbandProfile() {
		return mgmtInbandProfile;
	}

	public void setMgmtInbandProfile(List<Mgmtinbandprofile> mgmtInbandProfile) {
		this.mgmtInbandProfile = mgmtInbandProfile;
	}

	public List<Fabricudldlinkpolicy> getFabricUdldLinkPolicy() {
		return fabricUdldLinkPolicy;
	}

	public void setFabricUdldLinkPolicy(
			List<Fabricudldlinkpolicy> fabricUdldLinkPolicy) {
		this.fabricUdldLinkPolicy = fabricUdldLinkPolicy;
	}

	public List<Statsthresholdpolicy> getStatsThresholdPolicy() {
		return statsThresholdPolicy;
	}

	public void setStatsThresholdPolicy(
			List<Statsthresholdpolicy> statsThresholdPolicy) {
		this.statsThresholdPolicy = statsThresholdPolicy;
	}

	public List<Flowctrldefinition> getFlowctrlDefinition() {
		return flowctrlDefinition;
	}

	public void setFlowctrlDefinition(List<Flowctrldefinition> flowctrlDefinition) {
		this.flowctrlDefinition = flowctrlDefinition;
	}
}
