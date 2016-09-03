package com.cisco.ca.cstg.pdi.pojos.mo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "metadata")
@XmlAccessorType(XmlAccessType.FIELD)
public class Metadata implements java.io.Serializable {

	private static final long serialVersionUID = 4436156832822733661L;

	@XmlAttribute
	private String projectName;
	@XmlAttribute
	private Float version;
	@XmlElement(name = "infrastructure")
	private Infrastructure infrastructure;
	@XmlElement(name = "ucsDetails")
	private UcsDetails ucsDetails;
	@XmlElement(name = "mgmtBackupPolicy")
	private Mgmtbackuppolicy mgmtBackupPolicy;
	@XmlElement(name = "equipmentChassis")
	private EquipmentChassis equipmentChassis;
	private EquipmentServers equipmentServers;
	private SanVHBA sanVHBA;
	private AdminLdapProvider adminLdapProvider;
	private AdminTacacsProvider adminTacacsProvider;
	private AdminRadiusProvider adminRadiusProvider;
	private AdminLdapRole adminLdapRole;
	private AdminLdapLocale adminLdapLocale;
	private AdminLdapGroupMap adminLdapGroupMap;
	private FCPorts fcPorts;
	private EquipmentMiniScalabilityPorts equipmentMiniScalabilityPorts;

	public Metadata() {
	}

	public Metadata(String projectName, Float version,
			Infrastructure infrastructure, UcsDetails ucsDetails,
			Mgmtbackuppolicy mgmtBackupPolicy,
			EquipmentChassis equipmentChassis,
			EquipmentServers equipmentServers, SanVHBA sanVHBA,
			AdminLdapProvider adminLdapProvider,
			AdminTacacsProvider adminTacacsProvider,
			AdminRadiusProvider adminRadiusProvider,
			AdminLdapRole adminLdapRole, AdminLdapLocale adminLdapLocale,
			AdminLdapGroupMap adminLdapGroupMap, FCPorts fcPorts,
			EquipmentMiniScalabilityPorts equipmentMiniScalabilityPorts) {
		this.projectName = projectName;
		this.version = version;
		this.infrastructure = infrastructure;
		this.ucsDetails = ucsDetails;
		this.mgmtBackupPolicy = mgmtBackupPolicy;
		this.equipmentChassis = equipmentChassis;
		this.equipmentServers = equipmentServers;
		this.sanVHBA = sanVHBA;
		this.adminLdapProvider = adminLdapProvider;
		this.adminTacacsProvider = adminTacacsProvider;
		this.adminRadiusProvider = adminRadiusProvider;
		this.adminLdapRole = adminLdapRole;
		this.adminLdapLocale = adminLdapLocale;
		this.adminLdapGroupMap = adminLdapGroupMap;
		this.fcPorts = fcPorts;
		this.equipmentMiniScalabilityPorts = equipmentMiniScalabilityPorts;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Float getVersion() {
		return version;
	}

	public void setVersion(Float version) {
		this.version = version;
	}

	public Infrastructure getInfrastructure() {
		return infrastructure;
	}

	public void setInfrastructure(Infrastructure infrastructure) {
		this.infrastructure = infrastructure;
	}

	public Mgmtbackuppolicy getMgmtBackupPolicy() {
		return mgmtBackupPolicy;
	}

	public void setMgmtBackupPolicy(Mgmtbackuppolicy mgmtBackupPolicy) {
		this.mgmtBackupPolicy = mgmtBackupPolicy;
	}

	public UcsDetails getUcsDetails() {
		return ucsDetails;
	}

	public void setUcsDetails(UcsDetails ucsDetails) {
		this.ucsDetails = ucsDetails;
	}

	public EquipmentChassis getEquipmentChassis() {
		return equipmentChassis;
	}

	public void setEquipmentChassis(EquipmentChassis equipmentChassis) {
		this.equipmentChassis = equipmentChassis;
	}

	public EquipmentServers getEquipmentServers() {
		return equipmentServers;
	}

	public void setEquipmentServers(EquipmentServers equipmentServers) {
		this.equipmentServers = equipmentServers;
	}

	public SanVHBA getSanVHBA() {
		return sanVHBA;
	}

	public void setSanVHBA(SanVHBA sanVHBA) {
		this.sanVHBA = sanVHBA;
	}

	public AdminLdapProvider getAdminLdapProvider() {
		return adminLdapProvider;
	}

	public void setAdminLdapProvider(AdminLdapProvider adminLdapProvider) {
		this.adminLdapProvider = adminLdapProvider;
	}

	public AdminTacacsProvider getAdminTacacsProvider() {
		return adminTacacsProvider;
	}

	public void setAdminTacacsProvider(AdminTacacsProvider adminTacacsProvider) {
		this.adminTacacsProvider = adminTacacsProvider;
	}

	public AdminRadiusProvider getAdminRadiusProvider() {
		return adminRadiusProvider;
	}

	public void setAdminRadiusProvider(AdminRadiusProvider adminRadiusProvider) {
		this.adminRadiusProvider = adminRadiusProvider;
	}

	public AdminLdapRole getAdminLdapRole() {
		return adminLdapRole;
	}

	public void setAdminLdapRole(AdminLdapRole adminLdapRole) {
		this.adminLdapRole = adminLdapRole;
	}

	public AdminLdapLocale getAdminLdapLocale() {
		return adminLdapLocale;
	}

	public void setAdminLdapLocale(AdminLdapLocale adminLdapLocale) {
		this.adminLdapLocale = adminLdapLocale;
	}

	public AdminLdapGroupMap getAdminLdapGroupMap() {
		return adminLdapGroupMap;
	}

	public void setAdminLdapGroupMap(AdminLdapGroupMap adminLdapGroupMap) {
		this.adminLdapGroupMap = adminLdapGroupMap;
	}

	public FCPorts getFcPorts() {
		return fcPorts;
	}

	public void setFcPorts(FCPorts fcPorts) {
		this.fcPorts = fcPorts;
	}

	public EquipmentMiniScalabilityPorts getEquipmentMiniScalabilityPorts() {
		return equipmentMiniScalabilityPorts;
	}

	public void setEquipmentMiniScalabilityPorts(
			EquipmentMiniScalabilityPorts equipmentMiniScalabilityPorts) {
		this.equipmentMiniScalabilityPorts = equipmentMiniScalabilityPorts;
	}

}
