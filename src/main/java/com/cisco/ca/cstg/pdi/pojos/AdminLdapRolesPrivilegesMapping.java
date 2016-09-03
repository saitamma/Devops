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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "admin_ldap_roles_privileges_mapping")
public class AdminLdapRolesPrivilegesMapping implements java.io.Serializable {

	private static final long serialVersionUID = 7495236078133570418L;
	private Integer id;
	private AdminPrivilege adminPrivilege;
	private AdminLdapRole adminLdapRole;

	public AdminLdapRolesPrivilegesMapping() {
	}

	public AdminLdapRolesPrivilegesMapping(Integer id) {
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
	@JoinColumn(name = "roles_privileges_id")
	@Fetch(FetchMode.SELECT)
	public AdminPrivilege getAdminPrivilege() {
		return this.adminPrivilege;
	}

	public void setAdminPrivilege(AdminPrivilege adminPrivilege) {
		this.adminPrivilege = adminPrivilege;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ldap_role_id", nullable = false)
	public AdminLdapRole getAdminLdapRole() {
		return this.adminLdapRole;
	}

	public void setAdminLdapRole(AdminLdapRole adminLdapRole) {
		this.adminLdapRole = adminLdapRole;
	}

}
