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
@Table(name = "san_connectivity_policy")
public class SanConnectivityPolicy implements java.io.Serializable {

	private static final long serialVersionUID = -8101410666809720011L;
	
	private Integer id;
	private ProjectDetails projectDetails;
	private SanWwnn sanWwnn;
	private Organizations organizations;
	private String name;
	private String description;
	private List<SanScpVhbaMapping> sanScpVhbaMappings = new ArrayList<SanScpVhbaMapping>();
	private List<ServersServiceProfileTemplate> serversServiceProfileTemplates = new ArrayList<ServersServiceProfileTemplate>();

	public SanConnectivityPolicy() {
	}
	
	public SanConnectivityPolicy(int id) {
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
	@JoinColumn(name = "wwnn_id")
	@Fetch(FetchMode.SELECT)
	public SanWwnn getSanWwnn() {
		return this.sanWwnn;
	}

	public void setSanWwnn(SanWwnn sanWwnn) {
		this.sanWwnn = sanWwnn;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization")
	public Organizations getOrganizations() {
		return this.organizations;
	}

	public void setOrganizations(Organizations organizations) {
		this.organizations = organizations;
	}

	@Column(name = "name", length = 16)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 256)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "sanConnectivityPolicy")
	@Fetch(FetchMode.SELECT)
	public List<SanScpVhbaMapping> getSanScpVhbaMappings() {
		return this.sanScpVhbaMappings;
	}

	public void setSanScpVhbaMappings(List<SanScpVhbaMapping> sanScpVhbaMappings) {
		this.sanScpVhbaMappings = sanScpVhbaMappings;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sanConnectivityPolicy")
	public List<ServersServiceProfileTemplate> getServersServiceProfileTemplates() {
		return this.serversServiceProfileTemplates;
	}

	public void setServersServiceProfileTemplates(
			List<ServersServiceProfileTemplate> serversServiceProfileTemplates) {
		this.serversServiceProfileTemplates = serversServiceProfileTemplates;
	}

}
