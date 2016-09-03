package com.cisco.ca.cstg.pdi.pojos;


import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "admin_ldap_group_rule")
public class AdminLdapGroupRule implements java.io.Serializable {

	private static final long serialVersionUID = -55470514828877578L;
	private Integer id;
	private AdminLdapProvider adminLdapProvider;
	private String authorization;
	private String recursion;
	private String target;
	private String primaryGroup;

	public AdminLdapGroupRule() {
	}

	public AdminLdapGroupRule(Integer id) {
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
	@JoinColumn(name = "admin_ldap_provider_id")
	public AdminLdapProvider getAdminLdapProvider() {
		return this.adminLdapProvider;
	}

	public void setAdminLdapProvider(AdminLdapProvider adminLdapProvider) {
		this.adminLdapProvider = adminLdapProvider;
	}

	@Column(name = "authorization", nullable = false, length = 45)
	public String getAuthorization() {
		return this.authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}

	@Column(name = "recursion", nullable = false, length = 45)
	public String getRecursion() {
		return this.recursion;
	}

	public void setRecursion(String recursion) {
		this.recursion = recursion;
	}

	@Column(name = "target", length = 64)
	public String getTarget() {
		return this.target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	@Column(name = "primaryGroup", length = 45)
	public String getPrimaryGroup() {
		return this.primaryGroup;
	}

	public void setPrimaryGroup(String primaryGroup) {
		this.primaryGroup = primaryGroup;
	}

}
