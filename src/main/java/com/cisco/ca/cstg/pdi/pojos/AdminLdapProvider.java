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
import javax.persistence.Transient;

import com.cisco.ucs.mgr.util.Base64;

@Entity
@Table(name = "admin_ldap_provider")
public class AdminLdapProvider implements java.io.Serializable {

	private static final long serialVersionUID = -4292065525866162211L;
	private Integer id;
	private ProjectDetails projectDetails;
	private String hostname;
	private Integer providerOrder;
	private String bindDn;
	private String baseDn;
	private Integer port;
	private String enableSsl;
	private String filter;
	private String attribute;
	private String providerPassword;
	private Integer timeout;
	private String vendor;
	private String groupAuthorization;
	private String groupRecursion;
	private String targetAttribute;
	private String usePrimaryGroup;
	private List<AdminLdapGroupRule> adminLdapGroupRules = new ArrayList<AdminLdapGroupRule>();
	private List<AdminLdapGroupProviderMapping> adminLdapGroupProviderMappings = new ArrayList<AdminLdapGroupProviderMapping>();
	private String encryptedPassword;

	public AdminLdapProvider() {
	}

	public AdminLdapProvider(Integer id) {
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

	@Column(name = "host_name", length = 64)
	public String getHostname() {
		return this.hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	@Column(name = "provider_order")
	public Integer getProviderOrder() {
		return this.providerOrder;
	}

	public void setProviderOrder(Integer providerOrder) {
		this.providerOrder = providerOrder;
	}

	@Column(name = "bind_dn", length = 128)
	public String getBindDn() {
		return this.bindDn;
	}

	public void setBindDn(String bindDn) {
		this.bindDn = bindDn;
	}

	@Column(name = "base_dn", length = 128)
	public String getBaseDn() {
		return this.baseDn;
	}

	public void setBaseDn(String baseDn) {
		this.baseDn = baseDn;
	}

	@Column(name = "port")
	public Integer getPort() {
		return this.port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	@Column(name = "enable_ssl", length = 16)
	public String getEnableSsl() {
		return this.enableSsl;
	}

	public void setEnableSsl(String enableSsl) {
		this.enableSsl = enableSsl;
	}

	@Column(name = "filter", length = 45)
	public String getFilter() {
		return this.filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	@Column(name = "attribute", length = 45)
	public String getAttribute() {
		return this.attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	@Transient
	public String getProviderPassword() {

		if (null != this.encryptedPassword && !this.encryptedPassword.isEmpty()) {
			this.providerPassword = new String(
					Base64.decode(this.encryptedPassword));
		}
		return this.providerPassword;
	}

	public void setProviderPassword(String providerPassword) {

		if (null != providerPassword && !providerPassword.isEmpty()) {
			this.encryptedPassword = Base64.encode(providerPassword.getBytes());
		}
	}

	@Column(name = "provider_password", length = 45)
	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	@Column(name = "timeout")
	public Integer getTimeout() {
		return this.timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	@Column(name = "vendor", length = 45)
	public String getVendor() {
		return this.vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	@Column(name = "group_authorization", length = 16)
	public String getGroupAuthorization() {
		return this.groupAuthorization;
	}

	public void setGroupAuthorization(String groupAuthorization) {
		this.groupAuthorization = groupAuthorization;
	}

	@Column(name = "group_recursion", length = 16)
	public String getGroupRecursion() {
		return this.groupRecursion;
	}

	public void setGroupRecursion(String groupRecursion) {
		this.groupRecursion = groupRecursion;
	}

	@Column(name = "target_attribute", length = 64)
	public String getTargetAttribute() {
		return this.targetAttribute;
	}

	public void setTargetAttribute(String targetAttribute) {
		this.targetAttribute = targetAttribute;
	}

	@Column(name = "use_primary_group", length = 16)
	public String getUsePrimaryGroup() {
		return this.usePrimaryGroup;
	}

	public void setUsePrimaryGroup(String usePrimaryGroup) {
		this.usePrimaryGroup = usePrimaryGroup;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "adminLdapProvider")
	public List<AdminLdapGroupRule> getAdminLdapGroupRules() {
		return this.adminLdapGroupRules;
	}

	public void setAdminLdapGroupRules(
			List<AdminLdapGroupRule> adminLdapGroupRules) {
		this.adminLdapGroupRules = adminLdapGroupRules;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "adminLdapProvider")
	public List<AdminLdapGroupProviderMapping> getAdminLdapGroupProviderMappings() {
		return this.adminLdapGroupProviderMappings;
	}

	public void setAdminLdapGroupProviderMappings(
			List<AdminLdapGroupProviderMapping> adminLdapGroupProviderMappings) {
		this.adminLdapGroupProviderMappings = adminLdapGroupProviderMappings;
	}

}
