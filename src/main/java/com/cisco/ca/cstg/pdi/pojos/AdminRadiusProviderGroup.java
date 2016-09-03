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
@Table(name = "admin_radius_provider_group")
public class AdminRadiusProviderGroup implements java.io.Serializable {

	private static final long serialVersionUID = 3205169303217855867L;
	private Integer id;
	private ProjectDetails projectDetails;
	private String name;
	private List<AdminRadiusGroupProviderMapping> adminRadiusGroupProviderMappings = new ArrayList<AdminRadiusGroupProviderMapping>();
	private List<AdminAuthenticationDomain> adminAuthenticationDomains = new ArrayList<AdminAuthenticationDomain>();

	public AdminRadiusProviderGroup() {
	}

	public AdminRadiusProviderGroup(Integer id) {
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
	@JoinColumn(name = "project_id", nullable = false)
	public ProjectDetails getProjectDetails() {
		return this.projectDetails;
	}

	public void setProjectDetails(ProjectDetails projectDetails) {
		this.projectDetails = projectDetails;
	}

	@Column(name = "name", length = 128)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "adminRadiusProviderGroup")
	@Fetch(FetchMode.SELECT)
	public List<AdminRadiusGroupProviderMapping> getAdminRadiusGroupProviderMappings() {
		return this.adminRadiusGroupProviderMappings;
	}

	public void setAdminRadiusGroupProviderMappings(
			List<AdminRadiusGroupProviderMapping> adminRadiusGroupProviderMappings) {
		this.adminRadiusGroupProviderMappings = adminRadiusGroupProviderMappings;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "adminRadiusProviderGroup")
	public List<AdminAuthenticationDomain> getAdminAuthenticationDomains() {
		return this.adminAuthenticationDomains;
	}

	public void setAdminAuthenticationDomains(
			List<AdminAuthenticationDomain> adminAuthenticationDomains) {
		this.adminAuthenticationDomains = adminAuthenticationDomains;
	}

}
