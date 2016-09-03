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
@Table(name = "admin_tacacs_provider")
public class AdminTacacsProvider implements java.io.Serializable {

	private static final long serialVersionUID = -5483393474347071897L;
	private Integer id;
	private ProjectDetails projectDetails;
	private String hostname;
	private Integer providerOrder;
	private Integer port;
	private String providerKey;
	private Integer timeout;
	private List<AdminTacacsGroupProviderMapping> adminTacacsGroupProviderMappings = new ArrayList<AdminTacacsGroupProviderMapping>();
	private String encryptedPassword;

	public AdminTacacsProvider() {
	}

	public AdminTacacsProvider(Integer id) {
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

	@Column(name = "port")
	public Integer getPort() {
		return this.port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	@Transient
	public String getProviderKey() {
		if (null != this.encryptedPassword && !this.encryptedPassword.isEmpty()) {
			this.providerKey = new String(Base64.decode(this.encryptedPassword));
		}
		return this.providerKey;
	}

	public void setProviderKey(String providerKey) {

		if (null != providerKey && !providerKey.isEmpty()) {
			this.encryptedPassword = Base64.encode(providerKey.getBytes());
		}
	}

	@Column(name = "provider_key", length = 64)
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "adminTacacsProvider")
	public List<AdminTacacsGroupProviderMapping> getAdminTacacsGroupProviderMappings() {
		return this.adminTacacsGroupProviderMappings;
	}

	public void setAdminTacacsGroupProviderMappings(
			List<AdminTacacsGroupProviderMapping> adminTacacsGroupProviderMappings) {
		this.adminTacacsGroupProviderMappings = adminTacacsGroupProviderMappings;
	}

}
