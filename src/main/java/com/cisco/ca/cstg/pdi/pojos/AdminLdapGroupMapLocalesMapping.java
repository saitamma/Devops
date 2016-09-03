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
@Table(name = "admin_ldap_group_map_locales_mapping")
public class AdminLdapGroupMapLocalesMapping implements java.io.Serializable {

	private static final long serialVersionUID = 5914574916946643389L;
	private Integer id;
	private AdminLdapGroupMap adminLdapGroupMap;
	private AdminLdapLocale adminLdapLocale;

	public AdminLdapGroupMapLocalesMapping() {
	}

	public AdminLdapGroupMapLocalesMapping(Integer id) {
		this.id = id;
	}

	public AdminLdapGroupMapLocalesMapping(AdminLdapGroupMap adminLdapGroupMap) {
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
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name = "ldap_locale_id")
	public AdminLdapLocale getAdminLdapLocale() {
		return this.adminLdapLocale;
	}

	public void setAdminLdapLocale(AdminLdapLocale adminLdapLocale) {
		this.adminLdapLocale = adminLdapLocale;
	}

}
