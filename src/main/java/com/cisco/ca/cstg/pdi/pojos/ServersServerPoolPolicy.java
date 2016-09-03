package com.cisco.ca.cstg.pdi.pojos;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "servers_server_pool_policy")
public class ServersServerPoolPolicy implements java.io.Serializable {

	private static final long serialVersionUID = 605442945800989320L;
	
	private Integer id;
	private ProjectDetails projectDetails;
	private ServersServerPoolQualifier serversServerPoolQualifier;
	private Organizations organizations;
	private ServersServerPool serversServerPool;
	private String name;
	private String description;

	public ServersServerPoolPolicy() {
	}
	
	public ServersServerPoolPolicy(int id) {
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "qualifier_id")
	@Fetch(FetchMode.SELECT)
	public ServersServerPoolQualifier getServersServerPoolQualifier() {
		return this.serversServerPoolQualifier;
	}

	public void setServersServerPoolQualifier(
			ServersServerPoolQualifier serversServerPoolQualifier) {
		this.serversServerPoolQualifier = serversServerPoolQualifier;
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
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name = "server_pool_id")
	public ServersServerPool getServersServerPool() {
		return this.serversServerPool;
	}

	public void setServersServerPool(ServersServerPool serversServerPool) {
		this.serversServerPool = serversServerPool;
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

}
