package com.cisco.ca.cstg.pdi.pojos;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "project_details")
public class XmlGenProjectDetails implements java.io.Serializable {

	private static final long serialVersionUID = -5405538151037998387L;

	private Integer id;
	private String projectName;
	private Boolean isActive;
	private Boolean isUploaded;
	private String theatre;
	private Boolean skipSan;
	private String ipPoolAssignmentOrder;
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
	private List<AdminTacacsProvider> adminTacacsProviders = new ArrayList<AdminTacacsProvider>();
	private List<WizardStatus> wizardStatuses = new ArrayList<WizardStatus>();
	private List<EquipmentUplink> equipmentUplinks = new ArrayList<EquipmentUplink>();
	private List<ServersMaintenancePolicy> serversMaintenancePolicies = new ArrayList<ServersMaintenancePolicy>();
	private List<UcsServerLogs> ucsServerLogses = new ArrayList<UcsServerLogs>();
	private List<AdminBackupConfiguration> adminBackupConfigurations = new ArrayList<AdminBackupConfiguration>();
	private List<ServersLocalDisc> serversLocalDiscs = new ArrayList<ServersLocalDisc>();
	private List<ServersUuidPool> serversUuidPools = new ArrayList<ServersUuidPool>();
	private List<SanVhba> sanVhbas = new ArrayList<SanVhba>();
	private List<ServersBootPolicyLan> serversBootPolicyLans = new ArrayList<ServersBootPolicyLan>();
	private List<AdminTacacsGeneral> adminTacacsGenerals = new ArrayList<AdminTacacsGeneral>();
	private List<LanNetworkControlPolicy> lanNetworkControlPolicies = new ArrayList<LanNetworkControlPolicy>();
	private List<ServersHostFirmware> serversHostFirmwares = new ArrayList<ServersHostFirmware>();
	private List<SanWwnn> sanWwnns = new ArrayList<SanWwnn>();
	private List<SanVsan> sanVsans = new ArrayList<SanVsan>();
	private List<Dns> dnses = new ArrayList<Dns>();
	private List<ServersBiosPolicy> serversBiosPolicies = new ArrayList<ServersBiosPolicy>();
	private List<ServersBootPolicySan> serversBootPolicySans = new ArrayList<ServersBootPolicySan>();
	private List<AdminSettings> adminSettingses = new ArrayList<AdminSettings>();
	private List<ServersServerPoolQualifier> serversServerPoolQualifiers = new ArrayList<ServersServerPoolQualifier>();
	private List<EquipmentFcport> equipmentFcports = new ArrayList<EquipmentFcport>();
	private List<LanPortchannel> lanPortchannels = new ArrayList<LanPortchannel>(
			0);
	private List<ServersServerPool> serversServerPools = new ArrayList<ServersServerPool>();
	private List<Configuration> configurations = new ArrayList<Configuration>();
	private List<AdminLdapGroupMap> adminLdapGroupMaps = new ArrayList<AdminLdapGroupMap>();
	private List<AdminCallhomeSystemInventory> adminCallhomeSystemInventories = new ArrayList<AdminCallhomeSystemInventory>();
	private List<AdminRadiusProviderGroup> adminRadiusProviderGroups = new ArrayList<AdminRadiusProviderGroup>();
	private List<LanEthernetAdapterPolicies> lanEthernetAdapterPolicieses = new ArrayList<LanEthernetAdapterPolicies>();
	private List<LanVnic> lanVnics = new ArrayList<LanVnic>();
	private List<Ntp> ntps = new ArrayList<Ntp>();
	private List<SanWwpn> sanWwpns = new ArrayList<SanWwpn>();
	private List<Organizations> organizationses = new ArrayList<Organizations>();
	private List<ServersBootPolicyLocalDisc> serversBootPolicyLocalDiscs = new ArrayList<ServersBootPolicyLocalDisc>();
	private List<AdminLdapProvider> adminLdapProviders = new ArrayList<AdminLdapProvider>();
	private List<LanConnectivityPolicy> lanConnectivityPolicies = new ArrayList<LanConnectivityPolicy>();
	private List<AdminLdapProviderGroup> adminLdapProviderGroups = new ArrayList<AdminLdapProviderGroup>();
	private List<SanAdapterPolicies> sanAdapterPolicieses = new ArrayList<SanAdapterPolicies>();
	private List<AdminCallhomeProfile> adminCallhomeProfiles = new ArrayList<AdminCallhomeProfile>();
	private List<EquipmentChasis> equipmentChasises = new ArrayList<EquipmentChasis>();
	private List<ProjectSettings> projectSettingses = new ArrayList<ProjectSettings>();
	private List<AdminTacacsProviderGroup> adminTacacsProviderGroups = new ArrayList<AdminTacacsProviderGroup>();
	private List<LanMacpool> lanMacpools = new ArrayList<LanMacpool>(0);
	private List<EquipmentServer> equipmentServers = new ArrayList<EquipmentServer>();
	private List<Infrastructure> infrastructures = new ArrayList<Infrastructure>();
	private List<PowerAdapter> powerAdapters = new ArrayList<PowerAdapter>();
	private List<AdminRadiusGeneral> adminRadiusGenerals = new ArrayList<AdminRadiusGeneral>();
	private List<ServersServiceProfile> serversServiceProfiles = new ArrayList<ServersServiceProfile>();
	private List<AdminLdapRole> adminLdapRoles = new ArrayList<AdminLdapRole>();
	private List<SanConnectivityPolicy> sanConnectivityPolicies = new ArrayList<SanConnectivityPolicy>();
	private List<ServersServiceProfileTemplate> serversServiceProfileTemplates = new ArrayList<ServersServiceProfileTemplate>();
	private List<AdminLdapLocale> adminLdapLocales = new ArrayList<AdminLdapLocale>();
	private List<LanMgmtIppool> lanMgmtIppools = new ArrayList<LanMgmtIppool>();
	private List<SanVhbaTemplate> sanVhbaTemplates = new ArrayList<SanVhbaTemplate>();
	private List<LanQosPolicy> lanQosPolicies = new ArrayList<LanQosPolicy>();
	private List<AdminCallhomeGeneral> adminCallhomeGenerals = new ArrayList<AdminCallhomeGeneral>();
	private List<ServersBootPolicy> serversBootPolicies = new ArrayList<ServersBootPolicy>();
	private List<AdminCallhomePolicy> adminCallhomePolicies = new ArrayList<AdminCallhomePolicy>();
	private List<ServersServerPoolPolicy> serversServerPoolPolicies = new ArrayList<ServersServerPoolPolicy>();
	private List<LanVlan> lanVlans = new ArrayList<LanVlan>(0);
	private List<LanVnicTemplate> lanVnicTemplates = new ArrayList<LanVnicTemplate>();
	private List<AdminLdapGeneral> adminLdapGenerals = new ArrayList<AdminLdapGeneral>();
	private List<AdminRadiusProvider> adminRadiusProviders = new ArrayList<AdminRadiusProvider>();
	private List<AdminAuthenticationDomain> adminAuthenticationDomains = new ArrayList<AdminAuthenticationDomain>();
	private List<EquipmentMiniScalability> equipmentMiniScalabilityPorts = new ArrayList<EquipmentMiniScalability>();
	private List<InfrastructureEthernetFCMode> infrastructureEthernetFCMode = new ArrayList<InfrastructureEthernetFCMode>();

	public XmlGenProjectDetails() {
	}

	public XmlGenProjectDetails(Integer id) {
		this.id = id;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "project_name", length = 45)
	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Column(name = "isActive")
	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Column(name = "isUploaded")
	public Boolean getIsUploaded() {
		return this.isUploaded;
	}

	public void setIsUploaded(Boolean isUploaded) {
		this.isUploaded = isUploaded;
	}

	@Column(name = "theatre", length = 45)
	public String getTheatre() {
		return this.theatre;
	}

	public void setTheatre(String theatre) {
		this.theatre = theatre;
	}

	@Column(name = "skip_san")
	public Boolean getSkipSan() {
		return this.skipSan;
	}

	public void setSkipSan(Boolean skipSan) {
		this.skipSan = skipSan;
	}

	@Column(name = "ip_pool_assignment_order", length = 45)
	public String getIpPoolAssignmentOrder() {
		return this.ipPoolAssignmentOrder;
	}

	public void setIpPoolAssignmentOrder(String ipPoolAssignmentOrder) {
		this.ipPoolAssignmentOrder = ipPoolAssignmentOrder;
	}

	@Column(name = "createdBy", length = 45)
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", length = 19)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "updatedBy", length = 45)
	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date", length = 19)
	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<AdminTacacsProvider> getAdminTacacsProviders() {
		return this.adminTacacsProviders;
	}

	public void setAdminTacacsProviders(
			List<AdminTacacsProvider> adminTacacsProviders) {
		this.adminTacacsProviders = adminTacacsProviders;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "projectDetails")
	public List<WizardStatus> getWizardStatuses() {
		return this.wizardStatuses;
	}

	public void setWizardStatuses(List<WizardStatus> wizardStatuses) {
		this.wizardStatuses = wizardStatuses;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<EquipmentUplink> getEquipmentUplinks() {
		return this.equipmentUplinks;
	}

	public void setEquipmentUplinks(List<EquipmentUplink> equipmentUplinks) {
		this.equipmentUplinks = equipmentUplinks;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<ServersMaintenancePolicy> getServersMaintenancePolicies() {
		return this.serversMaintenancePolicies;
	}

	public void setServersMaintenancePolicies(
			List<ServersMaintenancePolicy> serversMaintenancePolicies) {
		this.serversMaintenancePolicies = serversMaintenancePolicies;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "projectDetails")
	public List<UcsServerLogs> getUcsServerLogses() {
		return this.ucsServerLogses;
	}

	public void setUcsServerLogses(List<UcsServerLogs> ucsServerLogses) {
		this.ucsServerLogses = ucsServerLogses;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<AdminBackupConfiguration> getAdminBackupConfigurations() {
		return this.adminBackupConfigurations;
	}

	public void setAdminBackupConfigurations(
			List<AdminBackupConfiguration> adminBackupConfigurations) {
		this.adminBackupConfigurations = adminBackupConfigurations;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<ServersLocalDisc> getServersLocalDiscs() {
		return this.serversLocalDiscs;
	}

	public void setServersLocalDiscs(List<ServersLocalDisc> serversLocalDiscs) {
		this.serversLocalDiscs = serversLocalDiscs;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<ServersUuidPool> getServersUuidPools() {
		return this.serversUuidPools;
	}

	public void setServersUuidPools(List<ServersUuidPool> serversUuidPools) {
		this.serversUuidPools = serversUuidPools;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<SanVhba> getSanVhbas() {
		return this.sanVhbas;
	}

	public void setSanVhbas(List<SanVhba> sanVhbas) {
		this.sanVhbas = sanVhbas;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<ServersBootPolicyLan> getServersBootPolicyLans() {
		return this.serversBootPolicyLans;
	}

	public void setServersBootPolicyLans(
			List<ServersBootPolicyLan> serversBootPolicyLans) {
		this.serversBootPolicyLans = serversBootPolicyLans;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<AdminTacacsGeneral> getAdminTacacsGenerals() {
		return this.adminTacacsGenerals;
	}

	public void setAdminTacacsGenerals(
			List<AdminTacacsGeneral> adminTacacsGenerals) {
		this.adminTacacsGenerals = adminTacacsGenerals;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<LanNetworkControlPolicy> getLanNetworkControlPolicies() {
		return this.lanNetworkControlPolicies;
	}

	public void setLanNetworkControlPolicies(
			List<LanNetworkControlPolicy> lanNetworkControlPolicies) {
		this.lanNetworkControlPolicies = lanNetworkControlPolicies;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<ServersHostFirmware> getServersHostFirmwares() {
		return this.serversHostFirmwares;
	}

	public void setServersHostFirmwares(
			List<ServersHostFirmware> serversHostFirmwares) {
		this.serversHostFirmwares = serversHostFirmwares;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<SanWwnn> getSanWwnns() {
		return this.sanWwnns;
	}

	public void setSanWwnns(List<SanWwnn> sanWwnns) {
		this.sanWwnns = sanWwnns;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<SanVsan> getSanVsans() {
		return this.sanVsans;
	}

	public void setSanVsans(List<SanVsan> sanVsans) {
		this.sanVsans = sanVsans;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<Dns> getDnses() {
		return this.dnses;
	}

	public void setDnses(List<Dns> dnses) {
		this.dnses = dnses;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<ServersBiosPolicy> getServersBiosPolicies() {
		return this.serversBiosPolicies;
	}

	public void setServersBiosPolicies(
			List<ServersBiosPolicy> serversBiosPolicies) {
		this.serversBiosPolicies = serversBiosPolicies;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<ServersBootPolicySan> getServersBootPolicySans() {
		return this.serversBootPolicySans;
	}

	public void setServersBootPolicySans(
			List<ServersBootPolicySan> serversBootPolicySans) {
		this.serversBootPolicySans = serversBootPolicySans;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<AdminSettings> getAdminSettingses() {
		return this.adminSettingses;
	}

	public void setAdminSettingses(List<AdminSettings> adminSettingses) {
		this.adminSettingses = adminSettingses;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<ServersServerPoolQualifier> getServersServerPoolQualifiers() {
		return this.serversServerPoolQualifiers;
	}

	public void setServersServerPoolQualifiers(
			List<ServersServerPoolQualifier> serversServerPoolQualifiers) {
		this.serversServerPoolQualifiers = serversServerPoolQualifiers;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<EquipmentFcport> getEquipmentFcports() {
		return this.equipmentFcports;
	}

	public void setEquipmentFcports(List<EquipmentFcport> equipmentFcports) {
		this.equipmentFcports = equipmentFcports;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<LanPortchannel> getLanPortchannels() {
		return this.lanPortchannels;
	}

	public void setLanPortchannels(List<LanPortchannel> lanPortchannels) {
		this.lanPortchannels = lanPortchannels;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<ServersServerPool> getServersServerPools() {
		return this.serversServerPools;
	}

	public void setServersServerPools(List<ServersServerPool> serversServerPools) {
		this.serversServerPools = serversServerPools;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<Configuration> getConfigurations() {
		return this.configurations;
	}

	public void setConfigurations(List<Configuration> configurations) {
		this.configurations = configurations;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<AdminLdapGroupMap> getAdminLdapGroupMaps() {
		return this.adminLdapGroupMaps;
	}

	public void setAdminLdapGroupMaps(List<AdminLdapGroupMap> adminLdapGroupMaps) {
		this.adminLdapGroupMaps = adminLdapGroupMaps;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<AdminCallhomeSystemInventory> getAdminCallhomeSystemInventories() {
		return this.adminCallhomeSystemInventories;
	}

	public void setAdminCallhomeSystemInventories(
			List<AdminCallhomeSystemInventory> adminCallhomeSystemInventories) {
		this.adminCallhomeSystemInventories = adminCallhomeSystemInventories;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<AdminRadiusProviderGroup> getAdminRadiusProviderGroups() {
		return this.adminRadiusProviderGroups;
	}

	public void setAdminRadiusProviderGroups(
			List<AdminRadiusProviderGroup> adminRadiusProviderGroups) {
		this.adminRadiusProviderGroups = adminRadiusProviderGroups;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<LanEthernetAdapterPolicies> getLanEthernetAdapterPolicieses() {
		return this.lanEthernetAdapterPolicieses;
	}

	public void setLanEthernetAdapterPolicieses(
			List<LanEthernetAdapterPolicies> lanEthernetAdapterPolicieses) {
		this.lanEthernetAdapterPolicieses = lanEthernetAdapterPolicieses;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<LanVnic> getLanVnics() {
		return this.lanVnics;
	}

	public void setLanVnics(List<LanVnic> lanVnics) {
		this.lanVnics = lanVnics;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<Ntp> getNtps() {
		return this.ntps;
	}

	public void setNtps(List<Ntp> ntps) {
		this.ntps = ntps;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<SanWwpn> getSanWwpns() {
		return this.sanWwpns;
	}

	public void setSanWwpns(List<SanWwpn> sanWwpns) {
		this.sanWwpns = sanWwpns;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<Organizations> getOrganizationses() {
		return this.organizationses;
	}

	public void setOrganizationses(List<Organizations> organizationses) {
		this.organizationses = organizationses;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<ServersBootPolicyLocalDisc> getServersBootPolicyLocalDiscs() {
		return this.serversBootPolicyLocalDiscs;
	}

	public void setServersBootPolicyLocalDiscs(
			List<ServersBootPolicyLocalDisc> serversBootPolicyLocalDiscs) {
		this.serversBootPolicyLocalDiscs = serversBootPolicyLocalDiscs;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<AdminLdapProvider> getAdminLdapProviders() {
		return this.adminLdapProviders;
	}

	public void setAdminLdapProviders(List<AdminLdapProvider> adminLdapProviders) {
		this.adminLdapProviders = adminLdapProviders;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<LanConnectivityPolicy> getLanConnectivityPolicies() {
		return this.lanConnectivityPolicies;
	}

	public void setLanConnectivityPolicies(
			List<LanConnectivityPolicy> lanConnectivityPolicies) {
		this.lanConnectivityPolicies = lanConnectivityPolicies;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<AdminLdapProviderGroup> getAdminLdapProviderGroups() {
		return this.adminLdapProviderGroups;
	}

	public void setAdminLdapProviderGroups(
			List<AdminLdapProviderGroup> adminLdapProviderGroups) {
		this.adminLdapProviderGroups = adminLdapProviderGroups;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<SanAdapterPolicies> getSanAdapterPolicieses() {
		return this.sanAdapterPolicieses;
	}

	public void setSanAdapterPolicieses(
			List<SanAdapterPolicies> sanAdapterPolicieses) {
		this.sanAdapterPolicieses = sanAdapterPolicieses;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<AdminCallhomeProfile> getAdminCallhomeProfiles() {
		return this.adminCallhomeProfiles;
	}

	public void setAdminCallhomeProfiles(
			List<AdminCallhomeProfile> adminCallhomeProfiles) {
		this.adminCallhomeProfiles = adminCallhomeProfiles;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<EquipmentChasis> getEquipmentChasises() {
		return this.equipmentChasises;
	}

	public void setEquipmentChasises(List<EquipmentChasis> equipmentChasises) {
		this.equipmentChasises = equipmentChasises;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<ProjectSettings> getProjectSettingses() {
		return this.projectSettingses;
	}

	public void setProjectSettingses(List<ProjectSettings> projectSettingses) {
		this.projectSettingses = projectSettingses;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<AdminTacacsProviderGroup> getAdminTacacsProviderGroups() {
		return this.adminTacacsProviderGroups;
	}

	public void setAdminTacacsProviderGroups(
			List<AdminTacacsProviderGroup> adminTacacsProviderGroups) {
		this.adminTacacsProviderGroups = adminTacacsProviderGroups;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<LanMacpool> getLanMacpools() {
		return this.lanMacpools;
	}

	public void setLanMacpools(List<LanMacpool> lanMacpools) {
		this.lanMacpools = lanMacpools;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<EquipmentServer> getEquipmentServers() {
		return this.equipmentServers;
	}

	public void setEquipmentServers(List<EquipmentServer> equipmentServers) {
		this.equipmentServers = equipmentServers;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<Infrastructure> getInfrastructures() {
		return this.infrastructures;
	}

	public void setInfrastructures(List<Infrastructure> infrastructures) {
		this.infrastructures = infrastructures;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<PowerAdapter> getPowerAdapters() {
		return this.powerAdapters;
	}

	public void setPowerAdapters(List<PowerAdapter> powerAdapters) {
		this.powerAdapters = powerAdapters;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<AdminRadiusGeneral> getAdminRadiusGenerals() {
		return this.adminRadiusGenerals;
	}

	public void setAdminRadiusGenerals(
			List<AdminRadiusGeneral> adminRadiusGenerals) {
		this.adminRadiusGenerals = adminRadiusGenerals;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<ServersServiceProfile> getServersServiceProfiles() {
		return this.serversServiceProfiles;
	}

	public void setServersServiceProfiles(
			List<ServersServiceProfile> serversServiceProfiles) {
		this.serversServiceProfiles = serversServiceProfiles;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<AdminLdapRole> getAdminLdapRoles() {
		return this.adminLdapRoles;
	}

	public void setAdminLdapRoles(List<AdminLdapRole> adminLdapRoles) {
		this.adminLdapRoles = adminLdapRoles;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<SanConnectivityPolicy> getSanConnectivityPolicies() {
		return this.sanConnectivityPolicies;
	}

	public void setSanConnectivityPolicies(
			List<SanConnectivityPolicy> sanConnectivityPolicies) {
		this.sanConnectivityPolicies = sanConnectivityPolicies;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<ServersServiceProfileTemplate> getServersServiceProfileTemplates() {
		return this.serversServiceProfileTemplates;
	}

	public void setServersServiceProfileTemplates(
			List<ServersServiceProfileTemplate> serversServiceProfileTemplates) {
		this.serversServiceProfileTemplates = serversServiceProfileTemplates;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<AdminLdapLocale> getAdminLdapLocales() {
		return this.adminLdapLocales;
	}

	public void setAdminLdapLocales(List<AdminLdapLocale> adminLdapLocales) {
		this.adminLdapLocales = adminLdapLocales;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<LanMgmtIppool> getLanMgmtIppools() {
		return this.lanMgmtIppools;
	}

	public void setLanMgmtIppools(List<LanMgmtIppool> lanMgmtIppools) {
		this.lanMgmtIppools = lanMgmtIppools;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<SanVhbaTemplate> getSanVhbaTemplates() {
		return this.sanVhbaTemplates;
	}

	public void setSanVhbaTemplates(List<SanVhbaTemplate> sanVhbaTemplates) {
		this.sanVhbaTemplates = sanVhbaTemplates;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<LanQosPolicy> getLanQosPolicies() {
		return this.lanQosPolicies;
	}

	public void setLanQosPolicies(List<LanQosPolicy> lanQosPolicies) {
		this.lanQosPolicies = lanQosPolicies;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<AdminCallhomeGeneral> getAdminCallhomeGenerals() {
		return this.adminCallhomeGenerals;
	}

	public void setAdminCallhomeGenerals(
			List<AdminCallhomeGeneral> adminCallhomeGenerals) {
		this.adminCallhomeGenerals = adminCallhomeGenerals;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<ServersBootPolicy> getServersBootPolicies() {
		return this.serversBootPolicies;
	}

	public void setServersBootPolicies(
			List<ServersBootPolicy> serversBootPolicies) {
		this.serversBootPolicies = serversBootPolicies;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<AdminCallhomePolicy> getAdminCallhomePolicies() {
		return this.adminCallhomePolicies;
	}

	public void setAdminCallhomePolicies(
			List<AdminCallhomePolicy> adminCallhomePolicies) {
		this.adminCallhomePolicies = adminCallhomePolicies;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<ServersServerPoolPolicy> getServersServerPoolPolicies() {
		return this.serversServerPoolPolicies;
	}

	public void setServersServerPoolPolicies(
			List<ServersServerPoolPolicy> serversServerPoolPolicies) {
		this.serversServerPoolPolicies = serversServerPoolPolicies;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<LanVlan> getLanVlans() {
		return this.lanVlans;
	}

	public void setLanVlans(List<LanVlan> lanVlans) {
		this.lanVlans = lanVlans;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<LanVnicTemplate> getLanVnicTemplates() {
		return this.lanVnicTemplates;
	}

	public void setLanVnicTemplates(List<LanVnicTemplate> lanVnicTemplates) {
		this.lanVnicTemplates = lanVnicTemplates;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<AdminLdapGeneral> getAdminLdapGenerals() {
		return this.adminLdapGenerals;
	}

	public void setAdminLdapGenerals(List<AdminLdapGeneral> adminLdapGenerals) {
		this.adminLdapGenerals = adminLdapGenerals;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<AdminRadiusProvider> getAdminRadiusProviders() {
		return this.adminRadiusProviders;
	}

	public void setAdminRadiusProviders(
			List<AdminRadiusProvider> adminRadiusProviders) {
		this.adminRadiusProviders = adminRadiusProviders;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<AdminAuthenticationDomain> getAdminAuthenticationDomains() {
		return this.adminAuthenticationDomains;
	}

	public void setAdminAuthenticationDomains(
			List<AdminAuthenticationDomain> adminAuthenticationDomains) {
		this.adminAuthenticationDomains = adminAuthenticationDomains;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<EquipmentMiniScalability> getEquipmentMiniScalabilityPorts() {
		return equipmentMiniScalabilityPorts;
	}

	public void setEquipmentMiniScalabilityPorts(
			List<EquipmentMiniScalability> equipmentMiniScalabilityPorts) {
		this.equipmentMiniScalabilityPorts = equipmentMiniScalabilityPorts;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectDetails")
	@Fetch(FetchMode.SUBSELECT)
	public List<InfrastructureEthernetFCMode> getInfrastructureEthernetFCMode() {
		return infrastructureEthernetFCMode;
	}

	public void setInfrastructureEthernetFCMode(
			List<InfrastructureEthernetFCMode> infrastructureEthernetFCMode) {
		this.infrastructureEthernetFCMode = infrastructureEthernetFCMode;
	}
	
}
