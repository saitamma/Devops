package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "orgOrg")
public class Orgorg implements java.io.Serializable {

	private static final long serialVersionUID = -715480936532486339L;
	private int primaryKey;
	private Toproot toproot;
	private Orgorg parentOrg;
	
	@XmlAttribute(name="name")
	private String name;
	
	@XmlAttribute(name="descr")
	private String descr;
	
	private List<Uuidpoolpool> uuidpoolPool = new ArrayList<Uuidpoolpool>();
	private List<Macpoolpool> macpoolPool = new ArrayList<Macpoolpool>();
	private List<Vnicsanconntempl> vnicSanConnTempl = new ArrayList<Vnicsanconntempl>();
	private List<Lsserver> lsServer = new ArrayList<Lsserver>();
	@XmlElement(name="computePool")
	private List<Computepool> computepool = new ArrayList<Computepool>();
	private List<Nwctrldefinition> nwctrlDefinition = new ArrayList<Nwctrldefinition>();
	private List<Vniclanconntempl> vnicLanConnTempl = new ArrayList<Vniclanconntempl>();
	private List<Vnicsanconnpolicy> vnicSanConnPolicy = new ArrayList<Vnicsanconnpolicy>();
	private List<Computequal> computeQual = new ArrayList<Computequal>();
	private List<Adaptorhostfcifprofile> adaptorHostFcIfProfile = new ArrayList<Adaptorhostfcifprofile>();
	private List<Epqosdefinition> epqosDefinition = new ArrayList<Epqosdefinition>();
	private List<Fcpoolinitiators> fcpoolInitiators = new ArrayList<Fcpoolinitiators>();
	private List<Ippoolpool> ippoolPool = new ArrayList<Ippoolpool>();
	private List<Computepsupolicy> computePsuPolicy = new ArrayList<Computepsupolicy>();
	private List<Storagelocaldiskconfigpolicy> storageLocalDiskConfigPolicy = new ArrayList<Storagelocaldiskconfigpolicy>();
	private List<Computepoolingpolicy> computePoolingPolicy = new ArrayList<Computepoolingpolicy>();
	private List<Lsbootpolicy> lsbootPolicy = new ArrayList<Lsbootpolicy>();
	private List<FirmwareComputeHostPack> firmwareComputeHostPack = new ArrayList<FirmwareComputeHostPack>();
	private List<LsmaintMaintPolicy> lsmaintMaintPolicy = new ArrayList<LsmaintMaintPolicy>();
	private List<Biosvprofile> biosVProfile = new ArrayList<Biosvprofile>();
	private List<Adaptorhostethifprofile> adaptorHostEthIfProfile = new ArrayList<Adaptorhostethifprofile>();
	private List<Vniclanconnpolicy> vnicLanConnPolicy = new ArrayList<Vniclanconnpolicy>();
	private List<Mgmtbackuppolicy> mgmtBackupPolicy = new ArrayList<Mgmtbackuppolicy>();
	
	@XmlElement(name="orgOrg")
	private List<Orgorg> orgorg = new ArrayList<Orgorg>();

	public Orgorg() {
	}
	
	public Orgorg(String name, String descr, List<Uuidpoolpool> uuidpoolPool,
			List<Macpoolpool> macpoolPool,
			List<Vnicsanconntempl> vnicSanConnTempl, List<Lsserver> lsServer,
			List<Computepool> computepool,
			List<Nwctrldefinition> nwctrlDefinition,
			List<Vniclanconntempl> vnicLanConnTempl,
			List<Vnicsanconnpolicy> vnicSanConnPolicy,
			List<Computequal> computeQual,
			List<Adaptorhostfcifprofile> adaptorHostFcIfProfile,
			List<Epqosdefinition> epqosDefinition,
			List<Fcpoolinitiators> fcpoolInitiators,
			List<Ippoolpool> ippoolPool,
			List<Computepsupolicy> computePsuPolicy,
			List<Storagelocaldiskconfigpolicy> storageLocalDiskConfigPolicy,
			List<Computepoolingpolicy> computePoolingPolicy,
			List<Lsbootpolicy> lsbootPolicy, List<FirmwareComputeHostPack> firmwareComputeHostPack,
			List<LsmaintMaintPolicy> lsmaintMaintPolicy, List<Biosvprofile> biosVProfile,
			List<Adaptorhostethifprofile> adaptorHostEthIfProfile,
			List<Vniclanconnpolicy> vnicLanConnPolicy,
			List<Mgmtbackuppolicy> mgmtBackupPolicy,
			List<Orgorg> orgOrg) {
		super();
		this.name = name;
		this.descr = descr;
		this.uuidpoolPool = uuidpoolPool;
		this.macpoolPool = macpoolPool;
		this.vnicSanConnTempl = vnicSanConnTempl;
		this.lsServer = lsServer;
		this.computepool = computepool;
		this.nwctrlDefinition = nwctrlDefinition;
		this.vnicLanConnTempl = vnicLanConnTempl;
		this.vnicSanConnPolicy = vnicSanConnPolicy;
		this.computeQual = computeQual;
		this.adaptorHostFcIfProfile = adaptorHostFcIfProfile;
		this.epqosDefinition = epqosDefinition;
		this.fcpoolInitiators = fcpoolInitiators;
		this.ippoolPool = ippoolPool;
		this.computePsuPolicy = computePsuPolicy;
		this.storageLocalDiskConfigPolicy = storageLocalDiskConfigPolicy;
		this.computePoolingPolicy = computePoolingPolicy;
		this.lsbootPolicy = lsbootPolicy;
		this.firmwareComputeHostPack = firmwareComputeHostPack;
		this.lsmaintMaintPolicy = lsmaintMaintPolicy;
		this.biosVProfile = biosVProfile;
		this.adaptorHostEthIfProfile = adaptorHostEthIfProfile;
		this.vnicLanConnPolicy = vnicLanConnPolicy;
		this.mgmtBackupPolicy = mgmtBackupPolicy;
		this.orgorg = orgOrg;
	}
	
	public int getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Toproot getToproot() {
		return toproot;
	}

	public void setToproot(Toproot toproot) {
		this.toproot = toproot;
	}

	public Orgorg getParentOrg() {
		return parentOrg;
	}

	public void setParentOrg(Orgorg parentOrg) {
		this.parentOrg = parentOrg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public List<Uuidpoolpool> getUuidpoolPool() {
		return uuidpoolPool;
	}

	public void setUuidpoolPool(List<Uuidpoolpool> uuidpoolPool) {
		this.uuidpoolPool = uuidpoolPool;
	}

	public List<Macpoolpool> getMacpoolPool() {
		return macpoolPool;
	}

	public void setMacpoolPool(List<Macpoolpool> macpoolPool) {
		this.macpoolPool = macpoolPool;
	}

	public List<Vnicsanconntempl> getVnicSanConnTempl() {
		return vnicSanConnTempl;
	}

	public void setVnicSanConnTempl(List<Vnicsanconntempl> vnicSanConnTempl) {
		this.vnicSanConnTempl = vnicSanConnTempl;
	}

	public List<Lsserver> getLsServer() {
		return lsServer;
	}

	public void setLsServer(List<Lsserver> lsServer) {
		this.lsServer = lsServer;
	}

	public List<Computepool> getComputepool() {
		return computepool;
	}

	public void setComputepool(List<Computepool> computepool) {
		this.computepool = computepool;
	}

	public List<Nwctrldefinition> getNwctrlDefinition() {
		return nwctrlDefinition;
	}

	public void setNwctrlDefinition(List<Nwctrldefinition> nwctrlDefinition) {
		this.nwctrlDefinition = nwctrlDefinition;
	}

	public List<Vniclanconntempl> getVnicLanConnTempl() {
		return vnicLanConnTempl;
	}

	public void setVnicLanConnTempl(List<Vniclanconntempl> vnicLanConnTempl) {
		this.vnicLanConnTempl = vnicLanConnTempl;
	}

	public List<Vnicsanconnpolicy> getVnicSanConnPolicy() {
		return vnicSanConnPolicy;
	}

	public void setVnicSanConnPolicy(List<Vnicsanconnpolicy> vnicSanConnPolicy) {
		this.vnicSanConnPolicy = vnicSanConnPolicy;
	}

	public List<Computequal> getComputeQual() {
		return computeQual;
	}

	public void setComputeQual(List<Computequal> computeQual) {
		this.computeQual = computeQual;
	}

	public List<Adaptorhostfcifprofile> getAdaptorHostFcIfProfile() {
		return adaptorHostFcIfProfile;
	}

	public void setAdaptorHostFcIfProfile(
			List<Adaptorhostfcifprofile> adaptorHostFcIfProfile) {
		this.adaptorHostFcIfProfile = adaptorHostFcIfProfile;
	}

	public List<Epqosdefinition> getEpqosDefinition() {
		return epqosDefinition;
	}

	public void setEpqosDefinition(List<Epqosdefinition> epqosDefinition) {
		this.epqosDefinition = epqosDefinition;
	}

	public List<Fcpoolinitiators> getFcpoolInitiators() {
		return fcpoolInitiators;
	}

	public void setFcpoolInitiators(List<Fcpoolinitiators> fcpoolInitiators) {
		this.fcpoolInitiators = fcpoolInitiators;
	}

	public List<Ippoolpool> getIppoolPool() {
		return ippoolPool;
	}

	public void setIppoolPool(List<Ippoolpool> ippoolPool) {
		this.ippoolPool = ippoolPool;
	}

	public List<Computepsupolicy> getComputePsuPolicy() {
		return computePsuPolicy;
	}

	public void setComputePsuPolicy(List<Computepsupolicy> computePsuPolicy) {
		this.computePsuPolicy = computePsuPolicy;
	}

	public List<Storagelocaldiskconfigpolicy> getStorageLocalDiskConfigPolicy() {
		return storageLocalDiskConfigPolicy;
	}

	public void setStorageLocalDiskConfigPolicy(
			List<Storagelocaldiskconfigpolicy> storageLocalDiskConfigPolicy) {
		this.storageLocalDiskConfigPolicy = storageLocalDiskConfigPolicy;
	}

	public List<Computepoolingpolicy> getComputePoolingPolicy() {
		return computePoolingPolicy;
	}

	public void setComputePoolingPolicy(
			List<Computepoolingpolicy> computePoolingPolicy) {
		this.computePoolingPolicy = computePoolingPolicy;
	}

	public List<Lsbootpolicy> getLsbootPolicy() {
		return lsbootPolicy;
	}

	public void setLsbootPolicy(List<Lsbootpolicy> lsbootPolicy) {
		this.lsbootPolicy = lsbootPolicy;
	}

	public List<FirmwareComputeHostPack> getFirmwareComputeHostPack() {
		return firmwareComputeHostPack;
	}

	public void setFirmwareComputeHostPack(
			List<FirmwareComputeHostPack> firmwareComputeHostPack) {
		this.firmwareComputeHostPack = firmwareComputeHostPack;
	}

	public List<LsmaintMaintPolicy> getLsmaintMaintPolicy() {
		return lsmaintMaintPolicy;
	}

	public void setLsmaintMaintPolicy(List<LsmaintMaintPolicy> lsmaintMaintPolicy) {
		this.lsmaintMaintPolicy = lsmaintMaintPolicy;
	}

	public List<Biosvprofile> getBiosVProfile() {
		return biosVProfile;
	}

	public void setBiosVProfile(List<Biosvprofile> biosVProfile) {
		this.biosVProfile = biosVProfile;
	}

	public List<Adaptorhostethifprofile> getAdaptorHostEthIfProfile() {
		return adaptorHostEthIfProfile;
	}

	public void setAdaptorHostEthIfProfile(
			List<Adaptorhostethifprofile> adaptorHostEthIfProfile) {
		this.adaptorHostEthIfProfile = adaptorHostEthIfProfile;
	}

	public List<Vniclanconnpolicy> getVnicLanConnPolicy() {
		return vnicLanConnPolicy;
	}

	public void setVnicLanConnPolicy(List<Vniclanconnpolicy> vnicLanConnPolicy) {
		this.vnicLanConnPolicy = vnicLanConnPolicy;
	}

	public List<Mgmtbackuppolicy> getMgmtBackupPolicy() {
		return mgmtBackupPolicy;
	}

	public void setMgmtBackupPolicy(List<Mgmtbackuppolicy> mgmtBackupPolicy) {
		this.mgmtBackupPolicy = mgmtBackupPolicy;
	}

	public List<Orgorg> getOrgorg() {
		return orgorg;
	}

	public void setOrgorg(List<Orgorg> orgorg) {
		this.orgorg = orgorg;
	}

}
