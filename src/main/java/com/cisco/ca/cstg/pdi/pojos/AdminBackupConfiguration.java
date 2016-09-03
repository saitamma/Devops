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

import com.cisco.ucs.mgr.util.Base64;

@Entity
@Table(name = "admin_backup_configuration")
public class AdminBackupConfiguration implements java.io.Serializable {

	private static final long serialVersionUID = 8767620652345009796L;
	private Integer id;
	private ProjectDetails projectDetails;
	private String backupStatus;
	private String adminState;
	private String backupType;
	private String preserveIdentities;
	private String protocol;
	private String hostname;
	private String remoteFile;
	private String userName;
	private String password;
	private String encPassword;

	public AdminBackupConfiguration() {
	}

	public AdminBackupConfiguration(Integer id) {
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

	@Column(name = "backup_status", length = 45)
	public String getBackupStatus() {
		return this.backupStatus;
	}

	public void setBackupStatus(String backupStatus) {
		this.backupStatus = backupStatus;
	}

	@Column(name = "admin_state", length = 45)
	public String getAdminState() {
		return this.adminState;
	}

	public void setAdminState(String adminState) {
		this.adminState = adminState;
	}

	@Column(name = "backup_type", length = 45)
	public String getBackupType() {
		return this.backupType;
	}

	public void setBackupType(String backupType) {
		this.backupType = backupType;
	}

	@Column(name = "preserve_identities", length = 45)
	public String getPreserveIdentities() {
		return this.preserveIdentities;
	}

	public void setPreserveIdentities(String preserveIdentities) {
		this.preserveIdentities = preserveIdentities;
	}

	@Column(name = "protocol", length = 45)
	public String getProtocol() {
		return this.protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	@Column(name = "host_name", length = 256)
	public String getHostname() {
		return this.hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	@Column(name = "remote_file", length = 128)
	public String getRemoteFile() {
		return this.remoteFile;
	}

	public void setRemoteFile(String remoteFile) {
		this.remoteFile = remoteFile;
	}

	@Column(name = "user_name", length = 45)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Transient
	public String getPassword() {

		if (null != this.encPassword && !this.encPassword.isEmpty()) {
			this.password = new String(Base64.decode(this.encPassword));
		}
		return this.password;
	}

	public void setPassword(String password) {

		if (null != password && !password.isEmpty()) {
			this.encPassword = Base64.encode(password.getBytes());
		}
	}

	@Column(name = "password")
	public String getEncPassword() {
		return encPassword;
	}

	public void setEncPassword(String encPassword) {
		this.encPassword = encPassword;
	}
}
