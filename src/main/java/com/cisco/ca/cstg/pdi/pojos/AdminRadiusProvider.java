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
@Table(name = "admin_radius_provider")
public class AdminRadiusProvider implements java.io.Serializable {

	private static final long serialVersionUID = 8246302316414268011L;
	private Integer id;
	private ProjectDetails projectDetails;
	private String hostname;
	private int radiusOrder;
	private int authorizationPort;
	private int timeout;
	private int retries;
	private String sslKey;
	private List<AdminRadiusGroupProviderMapping> adminRadiusGroupProviderMappings = new ArrayList<AdminRadiusGroupProviderMapping>();
	private String encKey;

	public AdminRadiusProvider() {
	}

	public AdminRadiusProvider(Integer id) {
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
	@JoinColumn(name = "project_id", nullable = false)
	public ProjectDetails getProjectDetails() {
		return this.projectDetails;
	}

	public void setProjectDetails(ProjectDetails projectDetails) {
		this.projectDetails = projectDetails;
	}

	@Column(name = "hostname", nullable = false, length = 64)
	public String getHostname() {
		return this.hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	@Column(name = "radius_order", nullable = false)
	public int getRadiusOrder() {
		return this.radiusOrder;
	}

	public void setRadiusOrder(int radiusOrder) {
		this.radiusOrder = radiusOrder;
	}

	@Column(name = "authorization_port", nullable = false)
	public int getAuthorizationPort() {
		return this.authorizationPort;
	}

	public void setAuthorizationPort(int authorizationPort) {
		this.authorizationPort = authorizationPort;
	}

	@Column(name = "timeout", nullable = false)
	public int getTimeout() {
		return this.timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	@Column(name = "retries", nullable = false)
	public int getRetries() {
		return this.retries;
	}

	public void setRetries(int retries) {
		this.retries = retries;
	}

	@Transient
	public String getSslKey() {

		if (null != this.encKey && !this.encKey.isEmpty()) {
			this.sslKey = new String(Base64.decode(this.encKey));
		}
		return this.sslKey;
	}

	public void setSslKey(String sslKey) {
		if (null != sslKey && !sslKey.isEmpty()) {
			this.encKey = Base64.encode(sslKey.getBytes());
		}

	}

	@Column(name = "ssl_key", length = 64)
	public String getEncKey() {
		return encKey;
	}

	public void setEncKey(String encKey) {
		this.encKey = encKey;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "adminRadiusProvider")
	public List<AdminRadiusGroupProviderMapping> getAdminRadiusGroupProviderMappings() {
		return this.adminRadiusGroupProviderMappings;
	}

	public void setAdminRadiusGroupProviderMappings(
			List<AdminRadiusGroupProviderMapping> adminRadiusGroupProviderMappings) {
		this.adminRadiusGroupProviderMappings = adminRadiusGroupProviderMappings;
	}

}
