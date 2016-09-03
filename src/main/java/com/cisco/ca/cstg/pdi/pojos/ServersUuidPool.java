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

@Entity
@Table(name = "servers_uuid_pool")
public class ServersUuidPool implements java.io.Serializable {

	private static final long serialVersionUID = -2066016512996543198L;
	
	private Integer id;
	private ProjectDetails projectDetails;
	private Organizations organizations;
	private String name;
	private String description;
	private String assignmentOrder;
	private String prefix;
	private String prefixType;
	private String fromAddress;
	private String toAddress;
	private String size;
	private List<ServersServiceProfileTemplate> serversServiceProfileTemplates = new ArrayList<ServersServiceProfileTemplate>(0);

	public ServersUuidPool() {
	}
		
	public ServersUuidPool(Integer id) {		
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

	@Column(name = "assignmentOrder", length = 45)
	public String getAssignmentOrder() {
		return this.assignmentOrder;
	}

	public void setAssignmentOrder(String assignmentOrder) {
		this.assignmentOrder = assignmentOrder;
	}

	@Column(name = "prefix", length = 45)
	public String getPrefix() {
		return this.prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	@Column(name = "prefixType", length = 45)
	public String getPrefixType() {
		return this.prefixType;
	}

	public void setPrefixType(String prefixType) {
		this.prefixType = prefixType;
	}

	@Column(name = "from_address", length = 45)
	public String getFromAddress() {
		return this.fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	@Column(name = "to_address", length = 45)
	public String getToAddress() {
		return this.toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "serversUuidPool")
	public List<ServersServiceProfileTemplate> getServersServiceProfileTemplates() {
		return this.serversServiceProfileTemplates;
	}

	public void setServersServiceProfileTemplates(
			List<ServersServiceProfileTemplate> serversServiceProfileTemplates) {
		this.serversServiceProfileTemplates = serversServiceProfileTemplates;
	}
	
	@Transient
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
		
}
