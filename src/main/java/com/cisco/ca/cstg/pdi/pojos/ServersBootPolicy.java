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
@Table(name = "servers_boot_policy")
public class ServersBootPolicy implements java.io.Serializable {

	private static final long serialVersionUID = 8341478368117402724L;
	
	private Integer id;
	private ProjectDetails projectDetails;
	private Organizations organizations;
	private String name;
	private String description;
	private int enforceVnicName;
	private int rebootOnUpdate;
	private String bootMode;
	private String secureBoot;
	private List<ServersBootPolicySan> serversBootPolicySans = new ArrayList<ServersBootPolicySan>();
	private List<ServersBootPolicyLocalDisc> serversBootPolicyLocalDiscs = new ArrayList<ServersBootPolicyLocalDisc>();
	private List<ServersBootPolicyLan> serversBootPolicyLans = new ArrayList<ServersBootPolicyLan>();
	private List<ServersServiceProfileTemplate> serversServiceProfileTemplates = new ArrayList<ServersServiceProfileTemplate>();
	private List<ServersBootPolicySanTarget> serversBootPolicySanTargets = new ArrayList<ServersBootPolicySanTarget>();

	public ServersBootPolicy() {
	}

	public ServersBootPolicy(Integer id) {
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization")
	public Organizations getOrganizations() {
		return this.organizations;
	}

	public void setOrganizations(Organizations organizations) {
		this.organizations = organizations;
	}

	@Column(name = "name", length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 45)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "enforce_vnic_name")
	public int getEnforceVnicName() {
		return this.enforceVnicName;
	}

	public void setEnforceVnicName(int enforceVnicName) {
		this.enforceVnicName = enforceVnicName;
	}

	@Column(name = "reboot_on_update")
	public int getRebootOnUpdate() {
		return this.rebootOnUpdate;
	}

	public void setRebootOnUpdate(int rebootOnUpdate) {
		this.rebootOnUpdate = rebootOnUpdate;
	}

	@Column(name = "boot_mode", length = 45)
	public String getBootMode() {
		return this.bootMode;
	}

	public void setBootMode(String bootMode) {
		this.bootMode = bootMode;
	}

	@Column(name = "secure_boot", length = 45)
	public String getSecureBoot() {
		return this.secureBoot;
	}

	public void setSecureBoot(String secureBoot) {
		this.secureBoot = secureBoot;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "serversBootPolicy")
	@Fetch(FetchMode.SELECT)
	public List<ServersBootPolicySan> getServersBootPolicySans() {
		return this.serversBootPolicySans;
	}

	public void setServersBootPolicySans(List<ServersBootPolicySan> serversBootPolicySans) {
		this.serversBootPolicySans = serversBootPolicySans;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "serversBootPolicy")
	@Fetch(FetchMode.SELECT)
	public List<ServersBootPolicyLocalDisc> getServersBootPolicyLocalDiscs() {
		return this.serversBootPolicyLocalDiscs;
	}

	public void setServersBootPolicyLocalDiscs(List<ServersBootPolicyLocalDisc> serversBootPolicyLocalDiscs) {
		this.serversBootPolicyLocalDiscs = serversBootPolicyLocalDiscs;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "serversBootPolicy")
	@Fetch(FetchMode.SELECT)
	public List<ServersBootPolicyLan> getServersBootPolicyLans() {
		return this.serversBootPolicyLans;
	}

	public void setServersBootPolicyLans(List<ServersBootPolicyLan> serversBootPolicyLans) {
		this.serversBootPolicyLans = serversBootPolicyLans;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "serversBootPolicy")
	public List<ServersServiceProfileTemplate> getServersServiceProfileTemplates() {
		return this.serversServiceProfileTemplates;
	}

	public void setServersServiceProfileTemplates(
			List<ServersServiceProfileTemplate> serversServiceProfileTemplates) {
		this.serversServiceProfileTemplates = serversServiceProfileTemplates;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "serversBootPolicy")
	public List<ServersBootPolicySanTarget> getServersBootPolicySanTargets() {
		return this.serversBootPolicySanTargets;
	}

	public void setServersBootPolicySanTargets(List<ServersBootPolicySanTarget> serversBootPolicySanTargets) {
		this.serversBootPolicySanTargets = serversBootPolicySanTargets;
	}

}
