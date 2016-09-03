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
@Table(name = "admin_ldap_locales_org_mapping")
public class AdminLdapLocalesOrgMapping implements java.io.Serializable {

	private static final long serialVersionUID = 2871665141408890555L;
	private Integer id;
	private Organizations organizations;
	private AdminLdapLocale adminLdapLocale;

	public AdminLdapLocalesOrgMapping() {
	}

	public AdminLdapLocalesOrgMapping(Integer id) {
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
	@JoinColumn(name = "organization")
	public Organizations getOrganizations() {
		return this.organizations;
	}

	public void setOrganizations(Organizations organizations) {
		this.organizations = organizations;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ldap_locale_id", nullable = false)
	public AdminLdapLocale getAdminLdapLocale() {
		return this.adminLdapLocale;
	}

	public void setAdminLdapLocale(AdminLdapLocale adminLdapLocale) {
		this.adminLdapLocale = adminLdapLocale;
	}

}
