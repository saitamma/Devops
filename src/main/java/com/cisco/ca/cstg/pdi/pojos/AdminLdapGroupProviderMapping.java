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
@Table(name = "admin_ldap_group_provider_mapping")
public class AdminLdapGroupProviderMapping implements java.io.Serializable {

	private static final long serialVersionUID = -4249716825152550326L;
	private Integer id;
	private AdminLdapProvider adminLdapProvider;
	private AdminLdapProviderGroup adminLdapProviderGroup;
	private Integer providerOrder;

	public AdminLdapGroupProviderMapping() {
	}

	public AdminLdapGroupProviderMapping(Integer id) {
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
	@JoinColumn(name = "ldap_provider_id", nullable = false)
	public AdminLdapProvider getAdminLdapProvider() {
		return this.adminLdapProvider;
	}

	public void setAdminLdapProvider(AdminLdapProvider adminLdapProvider) {
		this.adminLdapProvider = adminLdapProvider;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ldap_group_id", nullable = false)
	public AdminLdapProviderGroup getAdminLdapProviderGroup() {
		return this.adminLdapProviderGroup;
	}

	public void setAdminLdapProviderGroup(
			AdminLdapProviderGroup adminLdapProviderGroup) {
		this.adminLdapProviderGroup = adminLdapProviderGroup;
	}

	@Column(name = "provider_order")
	public Integer getProviderOrder() {
		return this.providerOrder;
	}

	public void setProviderOrder(Integer providerOrder) {
		this.providerOrder = providerOrder;
	}

}
