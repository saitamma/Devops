package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "lsServer")
public class Lsserver implements java.io.Serializable {

	private static final long serialVersionUID = 3471211789550595420L;
	private int primaryKey;
	private Orgorg orgorg;
	
	@XmlAttribute
	private String policyOwner;
	
	@XmlAttribute
	private String vconProfileName;
	
	@XmlAttribute
	private String uuid;
	
	@XmlAttribute
	private String usrLbl;
	
	@XmlAttribute
	private String type;
	
	@XmlAttribute
	private String statsPolicyName;
	
	@XmlAttribute
	private String srcTemplName;
	
	@XmlAttribute
	private String solPolicyName;
	
	@XmlAttribute
	private String scrubPolicyName;
	
	@XmlAttribute
	private String resolveRemote;
	
	@XmlAttribute
	private String powerPolicyName;

	@XmlAttribute
	private String name;
	
	@XmlAttribute
	private String mgmtFwPolicyName;
	
	@XmlAttribute
	private String mgmtAccessPolicyName;
	
	@XmlAttribute
	private String maintPolicyName;
	
	@XmlAttribute
	private String localDiskPolicyName;
	
	@XmlAttribute
	private String identPoolName;
	
	@XmlAttribute
	private String hostFwPolicyName;
	
	@XmlAttribute
	private String extIPState;
	
	@XmlAttribute
	private String extIPPoolName;
	
	@XmlAttribute
	private String dynamicConPolicyName;
	
	@XmlAttribute
	private String descr;
	
	@XmlAttribute
	private String bootPolicyName;
	
	@XmlAttribute
	private String biosProfileName;
	
	@XmlAttribute
	private String agentPolicyName;
	
	@XmlElement
	private List<Vnicfcnode> vnicFcNode = new ArrayList<Vnicfcnode>(); 
	
	@XmlElement
	private List<Fabricvcon> fabricVCon = new ArrayList<Fabricvcon>(); 
	
	@XmlElement
	private List<Lsvconassign> lsVConAssign = new ArrayList<Lsvconassign>(); 
	
	@XmlElement
	private List<Lsversionbeh> lsVersionBeh = new ArrayList<Lsversionbeh>(); 
	
	@XmlElement
	private List<Vnicfc> vnicFc = new ArrayList<Vnicfc>(); 
	
	@XmlElement
	private List<Lsserverextension> lsServerExtension = new ArrayList<Lsserverextension>(); 
	
	@XmlElement
	private List<Vnicconndef> vnicConnDef = new ArrayList<Vnicconndef>(); 
	
	@XmlElement
	private List<Lspower> lsPower = new ArrayList<Lspower>(); 
	
	@XmlElement
	private List<Vnicether> vnicEther = new ArrayList<Vnicether>(); 
	
	@XmlElement
	private List<Lsrequirement> lsRequirement = new ArrayList<Lsrequirement>();

	public Lsserver() {
	}

	public Lsserver(int primaryKey, Orgorg orgorg) {
		this.primaryKey = primaryKey;
		this.orgorg = orgorg;
	}

	public Lsserver(int primaryKey, Orgorg orgorg, String policyOwner,
			String vconProfileName, String uuid, String usrLbl, String type,
			String statsPolicyName, String srcTemplName, String solPolicyName,
			String scrubPolicyName, String resolveRemote,
			String powerPolicyName, String name, String mgmtFwPolicyName,
			String mgmtAccessPolicyName, String maintPolicyName,
			String localDiskPolicyName, String identPoolName,
			String hostFwPolicyName, String extIPState, String extIPPoolName,
			String dynamicConPolicyName, String descr, String bootPolicyName,
			String biosProfileName, String agentPolicyName,
			List<Vnicfcnode> vnicFcNode, List<Fabricvcon> fabricVCon,
			List<Lsvconassign> lsVConAssign, List<Lsversionbeh> lsVersionBeh,
			List<Vnicfc> vnicFc, List<Lsserverextension> lsServerExtension,
			List<Vnicconndef> vnicConnDef, List<Lspower> lsPower,
			List<Vnicether> vnicEther, List<Lsrequirement> lsRequirement) {
		super();
		this.primaryKey = primaryKey;
		this.orgorg = orgorg;
		this.policyOwner = policyOwner;
		this.vconProfileName = vconProfileName;
		this.uuid = uuid;
		this.usrLbl = usrLbl;
		this.type = type;
		this.statsPolicyName = statsPolicyName;
		this.srcTemplName = srcTemplName;
		this.solPolicyName = solPolicyName;
		this.scrubPolicyName = scrubPolicyName;
		this.resolveRemote = resolveRemote;
		this.powerPolicyName = powerPolicyName;
		this.name = name;
		this.mgmtFwPolicyName = mgmtFwPolicyName;
		this.mgmtAccessPolicyName = mgmtAccessPolicyName;
		this.maintPolicyName = maintPolicyName;
		this.localDiskPolicyName = localDiskPolicyName;
		this.identPoolName = identPoolName;
		this.hostFwPolicyName = hostFwPolicyName;
		this.extIPState = extIPState;
		this.extIPPoolName = extIPPoolName;
		this.dynamicConPolicyName = dynamicConPolicyName;
		this.descr = descr;
		this.bootPolicyName = bootPolicyName;
		this.biosProfileName = biosProfileName;
		this.agentPolicyName = agentPolicyName;
		this.vnicFcNode = vnicFcNode;
		this.fabricVCon = fabricVCon;
		this.lsVConAssign = lsVConAssign;
		this.lsVersionBeh = lsVersionBeh;
		this.vnicFc = vnicFc;
		this.lsServerExtension = lsServerExtension;
		this.vnicConnDef = vnicConnDef;
		this.lsPower = lsPower;
		this.vnicEther = vnicEther;
		this.lsRequirement = lsRequirement;
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

	public String getVconProfileName() {
		return this.vconProfileName;
	}

	public void setVconProfileName(String vconProfileName) {
		this.vconProfileName = vconProfileName;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUsrLbl() {
		return this.usrLbl;
	}

	public void setUsrLbl(String usrLbl) {
		this.usrLbl = usrLbl;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatsPolicyName() {
		return this.statsPolicyName;
	}

	public void setStatsPolicyName(String statsPolicyName) {
		this.statsPolicyName = statsPolicyName;
	}

	public String getSrcTemplName() {
		return this.srcTemplName;
	}

	public void setSrcTemplName(String srcTemplName) {
		this.srcTemplName = srcTemplName;
	}

	public String getSolPolicyName() {
		return this.solPolicyName;
	}

	public void setSolPolicyName(String solPolicyName) {
		this.solPolicyName = solPolicyName;
	}

	public String getScrubPolicyName() {
		return this.scrubPolicyName;
	}

	public void setScrubPolicyName(String scrubPolicyName) {
		this.scrubPolicyName = scrubPolicyName;
	}

	public String getResolveRemote() {
		return this.resolveRemote;
	}

	public void setResolveRemote(String resolveRemote) {
		this.resolveRemote = resolveRemote;
	}

	public String getPowerPolicyName() {
		return this.powerPolicyName;
	}

	public void setPowerPolicyName(String powerPolicyName) {
		this.powerPolicyName = powerPolicyName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMgmtFwPolicyName() {
		return this.mgmtFwPolicyName;
	}

	public void setMgmtFwPolicyName(String mgmtFwPolicyName) {
		this.mgmtFwPolicyName = mgmtFwPolicyName;
	}

	public String getMgmtAccessPolicyName() {
		return this.mgmtAccessPolicyName;
	}

	public void setMgmtAccessPolicyName(String mgmtAccessPolicyName) {
		this.mgmtAccessPolicyName = mgmtAccessPolicyName;
	}

	public String getMaintPolicyName() {
		return this.maintPolicyName;
	}

	public void setMaintPolicyName(String maintPolicyName) {
		this.maintPolicyName = maintPolicyName;
	}

	public String getLocalDiskPolicyName() {
		return this.localDiskPolicyName;
	}

	public void setLocalDiskPolicyName(String localDiskPolicyName) {
		this.localDiskPolicyName = localDiskPolicyName;
	}

	public String getIdentPoolName() {
		return this.identPoolName;
	}

	public void setIdentPoolName(String identPoolName) {
		this.identPoolName = identPoolName;
	}

	public String getHostFwPolicyName() {
		return this.hostFwPolicyName;
	}

	public void setHostFwPolicyName(String hostFwPolicyName) {
		this.hostFwPolicyName = hostFwPolicyName;
	}

	public String getDynamicConPolicyName() {
		return this.dynamicConPolicyName;
	}

	public void setDynamicConPolicyName(String dynamicConPolicyName) {
		this.dynamicConPolicyName = dynamicConPolicyName;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getBootPolicyName() {
		return this.bootPolicyName;
	}

	public void setBootPolicyName(String bootPolicyName) {
		this.bootPolicyName = bootPolicyName;
	}

	public String getBiosProfileName() {
		return this.biosProfileName;
	}

	public void setBiosProfileName(String biosProfileName) {
		this.biosProfileName = biosProfileName;
	}

	public String getAgentPolicyName() {
		return this.agentPolicyName;
	}

	public void setAgentPolicyName(String agentPolicyName) {
		this.agentPolicyName = agentPolicyName;
	}

	public String getExtIPState() {
		return extIPState;
	}

	public void setExtIPState(String extIPState) {
		this.extIPState = extIPState;
	}

	public String getExtIPPoolName() {
		return extIPPoolName;
	}

	public void setExtIPPoolName(String extIPPoolName) {
		this.extIPPoolName = extIPPoolName;
	}

	public List<Vnicfcnode> getVnicFcNode() {
		return vnicFcNode;
	}

	public void setVnicFcNode(List<Vnicfcnode> vnicFcNode) {
		this.vnicFcNode = vnicFcNode;
	}

	public List<Fabricvcon> getFabricVCon() {
		return fabricVCon;
	}

	public void setFabricVCon(List<Fabricvcon> fabricVCon) {
		this.fabricVCon = fabricVCon;
	}

	public List<Lsvconassign> getLsVConAssign() {
		return lsVConAssign;
	}

	public void setLsVConAssign(List<Lsvconassign> lsVConAssign) {
		this.lsVConAssign = lsVConAssign;
	}

	public List<Lsversionbeh> getLsVersionBeh() {
		return lsVersionBeh;
	}

	public void setLsVersionBeh(List<Lsversionbeh> lsVersionBeh) {
		this.lsVersionBeh = lsVersionBeh;
	}

	public List<Vnicfc> getVnicFc() {
		return vnicFc;
	}

	public void setVnicFc(List<Vnicfc> vnicFc) {
		this.vnicFc = vnicFc;
	}

	public List<Lsserverextension> getLsServerExtension() {
		return lsServerExtension;
	}

	public void setLsServerExtension(List<Lsserverextension> lsServerExtension) {
		this.lsServerExtension = lsServerExtension;
	}

	public List<Vnicconndef> getVnicConnDef() {
		return vnicConnDef;
	}

	public void setVnicConnDef(List<Vnicconndef> vnicConnDef) {
		this.vnicConnDef = vnicConnDef;
	}

	public List<Lspower> getLsPower() {
		return lsPower;
	}

	public void setLsPower(List<Lspower> lsPower) {
		this.lsPower = lsPower;
	}

	public List<Vnicether> getVnicEther() {
		return vnicEther;
	}

	public void setVnicEther(List<Vnicether> vnicEther) {
		this.vnicEther = vnicEther;
	}

	public List<Lsrequirement> getLsRequirement() {
		return lsRequirement;
	}

	public void setLsRequirement(List<Lsrequirement> lsRequirement) {
		this.lsRequirement = lsRequirement;
	}
	
	
}
