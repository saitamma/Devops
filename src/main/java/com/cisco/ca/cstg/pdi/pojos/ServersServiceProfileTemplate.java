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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "servers_service_profile_template")
public class ServersServiceProfileTemplate implements java.io.Serializable {

	private static final long serialVersionUID = -4843513520801044247L;
	private Integer id;
	private LanConnectivityPolicy lanConnectivityPolicy;
	private ProjectDetails projectDetails;
	private ServersBootPolicy serversBootPolicy;
	private ServersLocalDisc serversLocalDisc;
	private SanWwnn sanWwnn;
	private SanConnectivityPolicy sanConnectivityPolicy;
	private Organizations organizations;
	private ServersUuidPool serversUuidPool;
	private ServersServerPool serversServerPool;
	private ServersMaintenancePolicy serversMaintenancePolicy;
	private ServersHostFirmware serversHostFirmware;
	private ServersBiosPolicy serversBiosPolicy;
	private String name;
	private String type;
	private String description;
	private List<ServersSPTVnicMapping> serversSptVnicMappings = new ArrayList<ServersSPTVnicMapping>();
	private List<ServersSPTVhbaMapping> serversSptVhbaMappings = new ArrayList<ServersSPTVhbaMapping>();
	private List<ServersServiceProfile> serversServiceProfiles = new ArrayList<ServersServiceProfile>();

	public ServersServiceProfileTemplate() {
	}

	public ServersServiceProfileTemplate(int id) {
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "lan_connectivity_policy_id")
	@Fetch(FetchMode.SELECT)
	public LanConnectivityPolicy getLanConnectivityPolicy() {
		return this.lanConnectivityPolicy;
	}

	public void setLanConnectivityPolicy(
			LanConnectivityPolicy lanConnectivityPolicy) {
		this.lanConnectivityPolicy = lanConnectivityPolicy;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	public ProjectDetails getProjectDetails() {
		return this.projectDetails;
	}

	public void setProjectDetails(ProjectDetails projectDetails) {
		this.projectDetails = projectDetails;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "boot_policy_id")
	@Fetch(FetchMode.SELECT)
	public ServersBootPolicy getServersBootPolicy() {
		return this.serversBootPolicy;
	}

	public void setServersBootPolicy(ServersBootPolicy serversBootPolicy) {
		this.serversBootPolicy = serversBootPolicy;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "local_disk_policy_id")
	@Fetch(FetchMode.SELECT)
	public ServersLocalDisc getServersLocalDisc() {
		return this.serversLocalDisc;
	}

	public void setServersLocalDisc(ServersLocalDisc serversLocalDisc) {
		this.serversLocalDisc = serversLocalDisc;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "wwnn_id")
	@Fetch(FetchMode.SELECT)
	public SanWwnn getSanWwnn() {
		return this.sanWwnn;
	}

	public void setSanWwnn(SanWwnn sanWwnn) {
		this.sanWwnn = sanWwnn;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "san_connectivity_policy_id")
	@Fetch(FetchMode.SELECT)
	public SanConnectivityPolicy getSanConnectivityPolicy() {
		return this.sanConnectivityPolicy;
	}

	public void setSanConnectivityPolicy(
			SanConnectivityPolicy sanConnectivityPolicy) {
		this.sanConnectivityPolicy = sanConnectivityPolicy;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization")
	public Organizations getOrganizations() {
		return this.organizations;
	}

	public void setOrganizations(Organizations organizations) {
		this.organizations = organizations;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "uuid_id")
	@Fetch(FetchMode.SELECT)
	public ServersUuidPool getServersUuidPool() {
		return this.serversUuidPool;
	}

	public void setServersUuidPool(ServersUuidPool serversUuidPool) {
		this.serversUuidPool = serversUuidPool;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "server_pool_id")
	@Fetch(FetchMode.SELECT)
	public ServersServerPool getServersServerPool() {
		return this.serversServerPool;
	}

	public void setServersServerPool(ServersServerPool serversServerPool) {
		this.serversServerPool = serversServerPool;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "servers_maintenance_policy_id")
	@Fetch(FetchMode.SELECT)
	public ServersMaintenancePolicy getServersMaintenancePolicy() {
		return this.serversMaintenancePolicy;
	}

	public void setServersMaintenancePolicy(
			ServersMaintenancePolicy serversMaintenancePolicy) {
		this.serversMaintenancePolicy = serversMaintenancePolicy;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "servers_host_firmware_id")
	@Fetch(FetchMode.SELECT)
	public ServersHostFirmware getServersHostFirmware() {
		return this.serversHostFirmware;
	}

	public void setServersHostFirmware(ServersHostFirmware serversHostFirmware) {
		this.serversHostFirmware = serversHostFirmware;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "servers_bios_policy_id")
	@Fetch(FetchMode.SELECT)
	public ServersBiosPolicy getServersBiosPolicy() {
		return this.serversBiosPolicy;
	}

	public void setServersBiosPolicy(ServersBiosPolicy serversBiosPolicy) {
		this.serversBiosPolicy = serversBiosPolicy;
	}

	@Column(name = "name", length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "profile_template_type", length = 45)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "description", length = 45)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "serversServiceProfileTemplate")
	@Fetch(FetchMode.SELECT)
	public List<ServersSPTVnicMapping> getServersSptVnicMappings() {
		return this.serversSptVnicMappings;
	}

	public void setServersSptVnicMappings(
			List<ServersSPTVnicMapping> serversSptVnicMappings) {
		this.serversSptVnicMappings = serversSptVnicMappings;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "serversServiceProfileTemplate")
	@Fetch(FetchMode.SELECT)
	public List<ServersSPTVhbaMapping> getServersSptVhbaMappings() {
		return this.serversSptVhbaMappings;
	}

	public void setServersSptVhbaMappings(
			List<ServersSPTVhbaMapping> serversSptVhbaMappings) {
		this.serversSptVhbaMappings = serversSptVhbaMappings;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "serversServiceProfileTemplate")
	public List<ServersServiceProfile> getServersServiceProfiles() {
		return this.serversServiceProfiles;
	}

	public void setServersServiceProfiles(
			List<ServersServiceProfile> serversServiceProfiles) {
		this.serversServiceProfiles = serversServiceProfiles;
	}

}
