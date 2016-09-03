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
@Table(name = "san_vhba")
public class SanVhba implements java.io.Serializable {

	private static final long serialVersionUID = -6495258713895481393L;
	
	private Integer id;
	private SanAdapterPolicies sanAdapterPolicies;
	private ProjectDetails projectDetails;
	private SanVhbaTemplate sanVhbaTemplate;
	private String name;
	private List<ServersBootPolicySan> serversBootPolicySans = new ArrayList<ServersBootPolicySan>();
	private List<ServersSPTVhbaMapping> serversSptVhbaMappings = new ArrayList<ServersSPTVhbaMapping>();
	private List<SanScpVhbaMapping> sanScpVhbaMappings = new ArrayList<SanScpVhbaMapping>();

	public SanVhba() {
	}
	
	public SanVhba(Integer id) {
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
	@JoinColumn(name = "adapter_policy_id")
	@Fetch(FetchMode.SELECT)
	public SanAdapterPolicies getSanAdapterPolicies() {
		return this.sanAdapterPolicies;
	}

	public void setSanAdapterPolicies(SanAdapterPolicies sanAdapterPolicies) {
		this.sanAdapterPolicies = sanAdapterPolicies;
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
	@JoinColumn(name = "vhba_template_id")
	@Fetch(FetchMode.SELECT)
	public SanVhbaTemplate getSanVhbaTemplate() {
		return this.sanVhbaTemplate;
	}

	public void setSanVhbaTemplate(SanVhbaTemplate sanVhbaTemplate) {
		this.sanVhbaTemplate = sanVhbaTemplate;
	}

	@Column(name = "name", length = 16)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sanVhba")
	public List<ServersBootPolicySan> getServersBootPolicySans() {
		return this.serversBootPolicySans;
	}

	public void setServersBootPolicySans(List<ServersBootPolicySan> serversBootPolicySans) {
		this.serversBootPolicySans = serversBootPolicySans;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sanVhba")
	public List<ServersSPTVhbaMapping> getServersSptVhbaMappings() {
		return this.serversSptVhbaMappings;
	}

	public void setServersSptVhbaMappings(List<ServersSPTVhbaMapping> serversSptVhbaMappings) {
		this.serversSptVhbaMappings = serversSptVhbaMappings;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sanVhba")
	public List<SanScpVhbaMapping> getSanScpVhbaMappings() {
		return this.sanScpVhbaMappings;
	}

	public void setSanScpVhbaMappings(List<SanScpVhbaMapping> sanScpVhbaMappings) {
		this.sanScpVhbaMappings = sanScpVhbaMappings;
	}

}
