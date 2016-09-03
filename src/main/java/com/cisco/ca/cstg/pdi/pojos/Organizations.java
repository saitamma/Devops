package com.cisco.ca.cstg.pdi.pojos;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OrderBy;

@Entity
@Table(name = "organizations")
public class Organizations implements java.io.Serializable {

	private static final long serialVersionUID = -2861713088031565336L;
	private Integer id;
	private ProjectDetails projectDetails;
	private Integer parentId;
	private String name;
	private List<ServersServerPool> serversServerPools = new ArrayList<ServersServerPool>(0);
	private List<ServersMaintenancePolicy> serversMaintenancePolicies = new ArrayList<ServersMaintenancePolicy>(0);
	private List<ServersBootPolicy> serversBootPolicies = new ArrayList<ServersBootPolicy>(0);
	private List<SanVhbaTemplate> sanVhbaTemplates = new ArrayList<SanVhbaTemplate>(0);
	private List<LanConnectivityPolicy> lanConnectivityPolicies = new ArrayList<LanConnectivityPolicy>(0);
	private List<LanQosPolicy> lanQosPolicies = new ArrayList<LanQosPolicy>(0);
	private List<LanMacpool> lanMacpools = new ArrayList<LanMacpool>(0);
	private List<SanAdapterPolicies> sanAdapterPolicieses = new ArrayList<SanAdapterPolicies>(0);
	private List<ServersLocalDisc> serversLocalDiscs = new ArrayList<ServersLocalDisc>(0);
	private List<SanWwpn> sanWwpns = new ArrayList<SanWwpn>(0);
	private List<LanMgmtIppool> lanMgmtIppools = new ArrayList<LanMgmtIppool>(0);
	private List<ServersServerPoolQualifier> serversServerPoolQualifiers = new ArrayList<ServersServerPoolQualifier>(0);
	private List<SanConnectivityPolicy> sanConnectivityPolicies = new ArrayList<SanConnectivityPolicy>(0);
	private List<LanEthernetAdapterPolicies> lanEthernetAdapterPolicieses = new ArrayList<LanEthernetAdapterPolicies>(0);
	private List<ServersUuidPool> serversUuidPools = new ArrayList<ServersUuidPool>(0);
	private List<ServersServiceProfileTemplate> serversServiceProfileTemplates = new ArrayList<ServersServiceProfileTemplate>(0);
	private List<ServersHostFirmware> serversHostFirmwares = new ArrayList<ServersHostFirmware>(0);
	private List<ServersServiceProfile> serversServiceProfiles = new ArrayList<ServersServiceProfile>(0);
	private List<ServersBiosPolicy> serversBiosPolicies = new ArrayList<ServersBiosPolicy>(0);
	private List<AdminLdapLocalesOrgMapping> adminLdapLocalesOrgMappings = new ArrayList<AdminLdapLocalesOrgMapping>(0);
	private List<Organizations> organizationses = new ArrayList<Organizations>(0);
	private List<LanVnicTemplate> lanVnicTemplates = new ArrayList<LanVnicTemplate>(0);
	private List<ServersServerPoolPolicy> serversServerPoolPolicies = new ArrayList<ServersServerPoolPolicy>(0);
	private List<SanWwnn> sanWwnns = new ArrayList<SanWwnn>(0);
	private List<LanNetworkControlPolicy> lanNetworkControlPolicies = new ArrayList<LanNetworkControlPolicy>(0);
	
	private Integer depth;

	public Organizations() {
	}

	public Organizations(Integer id) {
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")	
	public ProjectDetails getProjectDetails() {
		return this.projectDetails;
	}

	public void setProjectDetails(ProjectDetails projectDetails) {
		this.projectDetails = projectDetails;
	}
	
	@Column(name = "name", length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizations")
	public List<ServersServerPool> getServersServerPools() {
		return this.serversServerPools;
	}

	public void setServersServerPools(List<ServersServerPool> serversServerPools) {
		this.serversServerPools = serversServerPools;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizations")
	public List<ServersMaintenancePolicy> getServersMaintenancePolicies() {
		return this.serversMaintenancePolicies;
	}

	public void setServersMaintenancePolicies(List<ServersMaintenancePolicy> serversMaintenancePolicies) {
		this.serversMaintenancePolicies = serversMaintenancePolicies;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizations")
	public List<ServersBootPolicy> getServersBootPolicies() {
		return this.serversBootPolicies;
	}

	public void setServersBootPolicies(List<ServersBootPolicy> serversBootPolicies) {
		this.serversBootPolicies = serversBootPolicies;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizations")
	public List<SanVhbaTemplate> getSanVhbaTemplates() {
		return this.sanVhbaTemplates;
	}

	public void setSanVhbaTemplates(List<SanVhbaTemplate> sanVhbaTemplates) {
		this.sanVhbaTemplates = sanVhbaTemplates;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizations")
	public List<LanConnectivityPolicy> getLanConnectivityPolicies() {
		return this.lanConnectivityPolicies;
	}

	public void setLanConnectivityPolicies(List<LanConnectivityPolicy> lanConnectivityPolicies) {
		this.lanConnectivityPolicies = lanConnectivityPolicies;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizations")
	public List<LanQosPolicy> getLanQosPolicies() {
		return this.lanQosPolicies;
	}

	public void setLanQosPolicies(List<LanQosPolicy> lanQosPolicies) {
		this.lanQosPolicies = lanQosPolicies;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizations")
	public List<LanMacpool> getLanMacpools() {
		return this.lanMacpools;
	}

	public void setLanMacpools(List<LanMacpool> lanMacpools) {
		this.lanMacpools = lanMacpools;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizations")
	public List<SanAdapterPolicies> getSanAdapterPolicieses() {
		return this.sanAdapterPolicieses;
	}

	public void setSanAdapterPolicieses(List<SanAdapterPolicies> sanAdapterPolicieses) {
		this.sanAdapterPolicieses = sanAdapterPolicieses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizations")
	public List<ServersLocalDisc> getServersLocalDiscs() {
		return this.serversLocalDiscs;
	}

	public void setServersLocalDiscs(List<ServersLocalDisc> serversLocalDiscs) {
		this.serversLocalDiscs = serversLocalDiscs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizations")
	public List<SanWwpn> getSanWwpns() {
		return this.sanWwpns;
	}

	public void setSanWwpns(List<SanWwpn> sanWwpns) {
		this.sanWwpns = sanWwpns;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizations")
	public List<LanMgmtIppool> getLanMgmtIppools() {
		return this.lanMgmtIppools;
	}

	public void setLanMgmtIppools(List<LanMgmtIppool> lanMgmtIppools) {
		this.lanMgmtIppools = lanMgmtIppools;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizations")
	public List<ServersServerPoolQualifier> getServersServerPoolQualifiers() {
		return this.serversServerPoolQualifiers;
	}

	public void setServersServerPoolQualifiers(List<ServersServerPoolQualifier> serversServerPoolQualifiers) {
		this.serversServerPoolQualifiers = serversServerPoolQualifiers;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizations")
	public List<SanConnectivityPolicy> getSanConnectivityPolicies() {
		return this.sanConnectivityPolicies;
	}

	public void setSanConnectivityPolicies(List<SanConnectivityPolicy> sanConnectivityPolicies) {
		this.sanConnectivityPolicies = sanConnectivityPolicies;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizations")
	public List<LanEthernetAdapterPolicies> getLanEthernetAdapterPolicieses() {
		return this.lanEthernetAdapterPolicieses;
	}

	public void setLanEthernetAdapterPolicieses(List<LanEthernetAdapterPolicies> lanEthernetAdapterPolicieses) {
		this.lanEthernetAdapterPolicieses = lanEthernetAdapterPolicieses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizations")
	public List<ServersUuidPool> getServersUuidPools() {
		return this.serversUuidPools;
	}

	public void setServersUuidPools(List<ServersUuidPool> serversUuidPools) {
		this.serversUuidPools = serversUuidPools;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizations")
	public List<ServersServiceProfileTemplate> getServersServiceProfileTemplates() {
		return this.serversServiceProfileTemplates;
	}

	public void setServersServiceProfileTemplates(
			List<ServersServiceProfileTemplate> serversServiceProfileTemplates) {
		this.serversServiceProfileTemplates = serversServiceProfileTemplates;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizations")
	public List<ServersHostFirmware> getServersHostFirmwares() {
		return this.serversHostFirmwares;
	}

	public void setServersHostFirmwares(List<ServersHostFirmware> serversHostFirmwares) {
		this.serversHostFirmwares = serversHostFirmwares;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizations")
	public List<ServersServiceProfile> getServersServiceProfiles() {
		return this.serversServiceProfiles;
	}

	public void setServersServiceProfiles(List<ServersServiceProfile> serversServiceProfiles) {
		this.serversServiceProfiles = serversServiceProfiles;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizations")
	public  List<ServersBiosPolicy> getServersBiosPolicies() {
		return this.serversBiosPolicies;
	}

	public void setServersBiosPolicies( List<ServersBiosPolicy> serversBiosPolicies) {
		this.serversBiosPolicies = serversBiosPolicies;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizations")
	public List<AdminLdapLocalesOrgMapping> getAdminLdapLocalesOrgMappings() {
		return this.adminLdapLocalesOrgMappings;
	}

	public void setAdminLdapLocalesOrgMappings(List<AdminLdapLocalesOrgMapping> adminLdapLocalesOrgMappings) {
		this.adminLdapLocalesOrgMappings = adminLdapLocalesOrgMappings;
	}

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "parent_id",insertable = false, updatable = false)
	@OrderBy(clause = "id")
	@Fetch(FetchMode.SELECT)
	public List<Organizations> getOrganizationses() {
		return this.organizationses;
	}

	public void setOrganizationses(List<Organizations> organizationses) {
		this.organizationses = organizationses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizations")
	public List<LanVnicTemplate> getLanVnicTemplates() {
		return this.lanVnicTemplates;
	}

	public void setLanVnicTemplates(List<LanVnicTemplate> lanVnicTemplates) {
		this.lanVnicTemplates = lanVnicTemplates;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizations")
	public List<ServersServerPoolPolicy> getServersServerPoolPolicies() {
		return this.serversServerPoolPolicies;
	}

	public void setServersServerPoolPolicies(List<ServersServerPoolPolicy> serversServerPoolPolicies) {
		this.serversServerPoolPolicies = serversServerPoolPolicies;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizations")
	public List<SanWwnn> getSanWwnns() {
		return this.sanWwnns;
	}

	public void setSanWwnns(List<SanWwnn> sanWwnns) {
		this.sanWwnns = sanWwnns;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizations")
	public List<LanNetworkControlPolicy> getLanNetworkControlPolicies() {
		return this.lanNetworkControlPolicies;
	}

	public void setLanNetworkControlPolicies(List<LanNetworkControlPolicy> lanNetworkControlPolicies) {
		this.lanNetworkControlPolicies = lanNetworkControlPolicies;
	}
	
	@Transient
	public Integer getDepth() {
		return depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}
	
	@Column(name = "parent_id")	
	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
}
