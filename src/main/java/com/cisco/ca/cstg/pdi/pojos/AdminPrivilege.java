package com.cisco.ca.cstg.pdi.pojos;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "admin_privilege")
public class AdminPrivilege implements java.io.Serializable {

	private static final long serialVersionUID = 8695320003238192793L;
	private Integer id;
	private String name;
	private List<AdminLdapRolesPrivilegesMapping> adminLdapRolesPrivilegesMappings = new ArrayList<AdminLdapRolesPrivilegesMapping>();

	public AdminPrivilege() {
	}

	public AdminPrivilege(Integer id) {
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

	@Column(name = "name", length = 128)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "adminPrivilege")
	public List<AdminLdapRolesPrivilegesMapping> getAdminLdapRolesPrivilegesMappings() {
		return this.adminLdapRolesPrivilegesMappings;
	}

	public void setAdminLdapRolesPrivilegesMappings(
			List<AdminLdapRolesPrivilegesMapping> adminLdapRolesPrivilegesMappings) {
		this.adminLdapRolesPrivilegesMappings = adminLdapRolesPrivilegesMappings;
	}

}
