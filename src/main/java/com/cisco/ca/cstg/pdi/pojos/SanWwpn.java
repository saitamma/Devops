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
@Table(name = "san_wwpn")
public class SanWwpn implements java.io.Serializable {

	private static final long serialVersionUID = -3320162360716519267L;
	
	private Integer id;
	private ProjectDetails projectDetails;
	private Organizations organizations;
	private String wwpnName;
	private String description;
	private String assignmentOrder;
	private String fromAddress;
	private String toAddress;
	private String noOfAddresses;
	private List<SanVhbaTemplate> sanVhbaTemplates = new ArrayList<SanVhbaTemplate>();

	public SanWwpn() {
	}

	public SanWwpn(int id) {
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

	@Column(name = "wwpn_name", length = 45)
	public String getWwpnName() {
		return this.wwpnName;
	}

	public void setWwpnName(String wwpnName) {
		this.wwpnName = wwpnName;
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
	
	@Transient
	public String getNoOfAddresses() {
		return noOfAddresses;
	}

	public void setNoOfAddresses(String noOfAddresses) {
		this.noOfAddresses = noOfAddresses;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sanWwpn")
	public List<SanVhbaTemplate> getSanVhbaTemplates() {
		return this.sanVhbaTemplates;
	}

	public void setSanVhbaTemplates(List<SanVhbaTemplate> sanVhbaTemplates) {
		this.sanVhbaTemplates = sanVhbaTemplates;
	}

}
