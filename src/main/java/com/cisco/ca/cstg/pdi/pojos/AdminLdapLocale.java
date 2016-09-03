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
@Table(name = "admin_ldap_locale")
public class AdminLdapLocale implements java.io.Serializable {

	private static final long serialVersionUID = -2083393568523239216L;
	private Integer id;
	private ProjectDetails projectDetails;
	private String name;
	private List<AdminLdapLocalesOrgMapping> adminLdapLocalesOrgMappings = new ArrayList<AdminLdapLocalesOrgMapping>();
	private List<AdminLdapGroupMapLocalesMapping> adminLdapGroupMapLocalesMappings = new ArrayList<AdminLdapGroupMapLocalesMapping>();

	public AdminLdapLocale() {
	}

	public AdminLdapLocale(Integer id) {
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

	@Column(name = "name", length = 16)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "adminLdapLocale")
	@Fetch(FetchMode.SELECT)
	public List<AdminLdapLocalesOrgMapping> getAdminLdapLocalesOrgMappings() {
		return this.adminLdapLocalesOrgMappings;
	}

	public void setAdminLdapLocalesOrgMappings(
			List<AdminLdapLocalesOrgMapping> adminLdapLocalesOrgMappings) {
		this.adminLdapLocalesOrgMappings = adminLdapLocalesOrgMappings;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "adminLdapLocale")
	public List<AdminLdapGroupMapLocalesMapping> getAdminLdapGroupMapLocalesMappings() {
		return this.adminLdapGroupMapLocalesMappings;
	}

	public void setAdminLdapGroupMapLocalesMappings(
			List<AdminLdapGroupMapLocalesMapping> adminLdapGroupMapLocalesMappings) {
		this.adminLdapGroupMapLocalesMappings = adminLdapGroupMapLocalesMappings;
	}

}
