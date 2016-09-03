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

@Entity
@Table(name = "servers_local_disc")
public class ServersLocalDisc implements java.io.Serializable {

	private static final long serialVersionUID = 8415649280133525120L;
	private Integer id;
	private ProjectDetails projectDetails;
	private Organizations organizations;
	private String name;
	private String description;
	private String mode;
	private int protectConfiguration;
	private List<ServersServiceProfileTemplate> serversServiceProfileTemplates = new ArrayList<ServersServiceProfileTemplate>();

	public ServersLocalDisc() {
	}

	public ServersLocalDisc(Integer id) {
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

	@Column(name = "mode", length = 45)
	public String getMode() {
		return this.mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	@Column(name = "protect_configuration")
	public int getProtectConfiguration() {
		return this.protectConfiguration;
	}

	public void setProtectConfiguration(int protectConfiguration) {
		this.protectConfiguration = protectConfiguration;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "serversLocalDisc")
	public List<ServersServiceProfileTemplate> getServersServiceProfileTemplates() {
		return this.serversServiceProfileTemplates;
	}

	public void setServersServiceProfileTemplates(
			List<ServersServiceProfileTemplate> serversServiceProfileTemplates) {
		this.serversServiceProfileTemplates = serversServiceProfileTemplates;
	}

}
