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
@Table(name = "servers_server_pool_qualifier")
public class ServersServerPoolQualifier implements java.io.Serializable {

	private static final long serialVersionUID = 8688041665725831441L;
	
	private Integer id;
	private ProjectDetails projectDetails;
	private Organizations organizations;
	private String name;
	private String description;
	private Integer chassisMinId;
	private Integer chassisMaxId;
	private List<ServersServerPoolPolicy> serversServerPoolPolicies = new ArrayList<ServersServerPoolPolicy>();
	private List<ServersSPQSlotMapping> serversSpqSlotMappings = new ArrayList<ServersSPQSlotMapping>();

	public ServersServerPoolQualifier() {
	}
	
	public ServersServerPoolQualifier(int id) {
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

	@Column(name = "chassis_min_id")
	public Integer getChassisMinId() {
		return this.chassisMinId;
	}

	public void setChassisMinId(Integer chassisMinId) {
		this.chassisMinId = chassisMinId;
	}

	@Column(name = "chassis_max_id")
	public Integer getChassisMaxId() {
		return this.chassisMaxId;
	}

	public void setChassisMaxId(Integer chassisMaxId) {
		this.chassisMaxId = chassisMaxId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "serversServerPoolQualifier")
	public List<ServersServerPoolPolicy> getServersServerPoolPolicies() {
		return this.serversServerPoolPolicies;
	}

	public void setServersServerPoolPolicies(List<ServersServerPoolPolicy> serversServerPoolPolicies) {
		this.serversServerPoolPolicies = serversServerPoolPolicies;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "serversServerPoolQualifier")
	@Fetch(FetchMode.SELECT)
	public List<ServersSPQSlotMapping> getServersSpqSlotMappings() {
		return this.serversSpqSlotMappings;
	}

	public void setServersSpqSlotMappings(List<ServersSPQSlotMapping> serversSpqSlotMappings) {
		this.serversSpqSlotMappings = serversSpqSlotMappings;
	}

}
