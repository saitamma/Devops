package com.cisco.ca.cstg.pdi.pojos.mo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cisco.ca.cstg.pdi.utils.AssessmentKey;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mgmtBackupPolicy")
public class Mgmtbackuppolicy implements java.io.Serializable {

	
	private static final long serialVersionUID = 7178533661228792430L;
	private static final Logger LOGGER = LoggerFactory.getLogger(Mgmtbackuppolicy.class);
	
	@XmlAttribute
	private String host;
	@XmlAttribute
	private String pwd;
	@XmlAttribute
	private String descr;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String adminState;
	@XmlAttribute
	private String proto;
	@XmlAttribute
	private String remoteFile;
	@XmlAttribute
	private String schedule;
	@XmlAttribute
	private String user;
	@XmlAttribute
	private String status;
	@XmlAttribute
	private String backupType;
	@XmlAttribute
	private String preserveIdentities;
	
	
	public Mgmtbackuppolicy() {
	}


	public Mgmtbackuppolicy(String host, String pwd, String descr,
					String name, String adminState, String proto,
					String remoteFile, String schedule, String user,
					String status, String backupType, String preserveIdentities) {
		this.host = host;
		this.pwd = pwd;
		this.descr = descr;
		this.name = name;
		this.adminState = adminState;
		this.proto = proto;
		this.remoteFile = remoteFile;
		this.schedule = schedule;
		this.user = user;
		this.status = status;
		this.backupType = backupType;
		this.preserveIdentities = preserveIdentities;
	}

	public String getHost() {
		return this.host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		if (null != pwd && !pwd.isEmpty()) {
			AssessmentKey assessmentKey = new AssessmentKey();
			try {
				assessmentKey.setMetaData(pwd);
				this.pwd = assessmentKey.encrypt();
			} catch (Exception ex) {
				LOGGER.error("Error occured while setting password.", ex);
			}
		}
	}
	
	public String getDecryptedPwd() {
		String decryptedKey = "";
		if (null != pwd && !pwd.isEmpty()) {
			AssessmentKey assessmentKey = new AssessmentKey();
			assessmentKey.setAssessmentKey(pwd);
			try {
				decryptedKey = assessmentKey.decrypt();
			} catch (Exception ex) {
				LOGGER.error("Error occured while getting password.", ex);
			}
		}
		return decryptedKey;
	}


	public String getDescr() {
		return descr;
	}


	public void setDescr(String descr) {
		this.descr = descr;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAdminState() {
		return adminState;
	}


	public void setAdminState(String adminState) {
		this.adminState = adminState;
	}


	public String getProto() {
		return proto;
	}


	public void setProto(String proto) {
		this.proto = proto;
	}


	public String getRemoteFile() {
		return remoteFile;
	}


	public void setRemoteFile(String remoteFile) {
		this.remoteFile = remoteFile;
	}


	public String getSchedule() {
		return schedule;
	}


	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getBackupType() {
		return backupType;
	}


	public void setBackupType(String backupType) {
		this.backupType = backupType;
	}


	public String getPreserveIdentities() {
		return preserveIdentities;
	}


	public void setPreserveIdentities(String preserveIdentities) {
		this.preserveIdentities = preserveIdentities;
	}
}
