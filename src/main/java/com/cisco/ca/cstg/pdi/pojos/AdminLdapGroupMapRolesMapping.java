package com.cisco.ca.cstg.pdi.pojos;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "admin_ldap_group_map_roles_mapping")
public class AdminLdapGroupMapRolesMapping implements java.io.Serializable {

	private static final long serialVersionUID = 7315235063453698695L;
	private Integer id;
	private AdminLdapGroupMap adminLdapGroupMap;
	private AdminLdapRole adminLdapRole;

	public AdminLdapGroupMapRolesMapping() {
	}

	public AdminLdapGroupMapRolesMapping(Integer id) {
		this.id = id;
	}

	public AdminLdapGroupMapRolesMapping(AdminLdapGroupMap adminLdapGroupMap) {
		this.adminLdapGroupMap = adminLdapGroupMap;
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
	@JoinColumn(name = "ldap_group_map_id", nullable = false)
	public AdminLdapGroupMap getAdminLdapGroupMap() {
		return this.adminLdapGroupMap;
	}

	public void setAdminLdapGroupMap(AdminLdapGroupMap adminLdapGroupMap) {
		this.adminLdapGroupMap = adminLdapGroupMap;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ldap_role_id")
	public AdminLdapRole getAdminLdapRole() {
		return this.adminLdapRole;
	}

	public void setAdminLdapRole(AdminLdapRole adminLdapRole) {
		this.adminLdapRole = adminLdapRole;
	}

}
