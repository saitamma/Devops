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
@Table(name = "admin_ldap_role")
public class AdminLdapRole implements java.io.Serializable {

	private static final long serialVersionUID = 6648220095892073689L;
	private Integer id;
	private ProjectDetails projectDetails;
	private String name;
	private List<AdminLdapGroupMapRolesMapping> adminLdapGroupMapRolesMappings = new ArrayList<AdminLdapGroupMapRolesMapping>();
	private List<AdminLdapRolesPrivilegesMapping> adminLdapRolesPrivilegesMappings = new ArrayList<AdminLdapRolesPrivilegesMapping>();

	public AdminLdapRole() {
	}

	public AdminLdapRole(Integer id) {
		this.id = id;
	}

	public AdminLdapRole(ProjectDetails projectDetails) {
		this.projectDetails = projectDetails;
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

	@Column(name = "name", length = 16)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "adminLdapRole")
	public List<AdminLdapGroupMapRolesMapping> getAdminLdapGroupMapRolesMappings() {
		return this.adminLdapGroupMapRolesMappings;
	}

	public void setAdminLdapGroupMapRolesMappings(
			List<AdminLdapGroupMapRolesMapping> adminLdapGroupMapRolesMappings) {
		this.adminLdapGroupMapRolesMappings = adminLdapGroupMapRolesMappings;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "adminLdapRole")
	@Fetch(FetchMode.SELECT)
	public List<AdminLdapRolesPrivilegesMapping> getAdminLdapRolesPrivilegesMappings() {
		return this.adminLdapRolesPrivilegesMappings;
	}

	public void setAdminLdapRolesPrivilegesMappings(
			List<AdminLdapRolesPrivilegesMapping> adminLdapRolesPrivilegesMappings) {
		this.adminLdapRolesPrivilegesMappings = adminLdapRolesPrivilegesMappings;
	}

}
