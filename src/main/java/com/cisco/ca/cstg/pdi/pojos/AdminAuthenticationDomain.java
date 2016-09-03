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
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "admin_authentication_domain")
public class AdminAuthenticationDomain implements java.io.Serializable {

	private static final long serialVersionUID = 8658658128432392181L;
	private Integer id;
	private ProjectDetails projectDetails;
	private AdminLdapProviderGroup adminLdapProviderGroup;
	private AdminRadiusProviderGroup adminRadiusProviderGroup;
	private AdminTacacsProviderGroup adminTacacsProviderGroup;
	private String name;
	private Integer refreshPeriod;
	private Integer sessionTimeout;
	private String realm;
	private String twoFactor;
	private Integer providerGroup;

	public AdminAuthenticationDomain() {
	}

	public AdminAuthenticationDomain(Integer id) {
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
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ldap_provider_group_id")
	@Fetch(FetchMode.SELECT)
	public AdminLdapProviderGroup getAdminLdapProviderGroup() {
		return this.adminLdapProviderGroup;
	}
	
	public void setAdminLdapProviderGroup(
			AdminLdapProviderGroup adminLdapProviderGroup) {
		this.adminLdapProviderGroup = adminLdapProviderGroup;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name = "radius_provider_group_id")
	public AdminRadiusProviderGroup getAdminRadiusProviderGroup() {
		return this.adminRadiusProviderGroup;
	}

	public void setAdminRadiusProviderGroup(
			AdminRadiusProviderGroup adminRadiusProviderGroup) {
		this.adminRadiusProviderGroup = adminRadiusProviderGroup;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name = "tacacs_provider_group_id")
	public AdminTacacsProviderGroup getAdminTacacsProviderGroup() {
		return this.adminTacacsProviderGroup;
	}

	public void setAdminTacacsProviderGroup(
			AdminTacacsProviderGroup adminTacacsProviderGroup) {
		this.adminTacacsProviderGroup = adminTacacsProviderGroup;
	}

	@Column(name = "name", length = 16)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "refresh_period")
	public Integer getRefreshPeriod() {
		return this.refreshPeriod;
	}

	public void setRefreshPeriod(Integer refreshPeriod) {
		this.refreshPeriod = refreshPeriod;
	}

	@Column(name = "session_timeout")
	public Integer getSessionTimeout() {
		return this.sessionTimeout;
	}

	public void setSessionTimeout(Integer sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}

	@Column(name = "realm", length = 45)
	public String getRealm() {
		return this.realm;
	}

	public void setRealm(String realm) {
		this.realm = realm;
	}

	@Column(name = "two_factor", length = 16)
	public String getTwoFactor() {
		return this.twoFactor;
	}

	public void setTwoFactor(String twoFactor) {
		this.twoFactor = twoFactor;
	}
	
	@Transient
	public Integer getProviderGroup() {
		return providerGroup;
	}

	public void setProviderGroup(Integer providerGroup) {
		this.providerGroup = providerGroup;
	}

}
