package com.cisco.ca.cstg.pdi.pojos;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cisco.ca.cstg.pdi.utils.AssessmentKey;

public class ServerDetails implements java.io.Serializable{

	private static Logger logger = LoggerFactory.getLogger(ServerDetails.class);

	private static final long serialVersionUID = 5563103339464959159L;
	private int id;
	private String nameOrIp;
	private String userName;
	private String password;
	private boolean pingStatus = false;
	private String configStatus;
	private ProjectDetails projectDetails;
	private String encryptedPassword;
	private Date verficationTimeStamp;
	private String url;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the nameOrIp
	 */
	public String getNameOrIp() {
		return nameOrIp;
	}
	/**
	 * @param nameOrIp the nameOrIp to set
	 */
	public void setNameOrIp(String nameOrIp) {
		this.nameOrIp = nameOrIp;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param username the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {

		if (null != this.encryptedPassword && !this.encryptedPassword.isEmpty()) {
			AssessmentKey assessmentKey = new AssessmentKey();
			assessmentKey.setAssessmentKey(this.encryptedPassword);
			try {
				this.password = assessmentKey.decrypt();
			} catch (Exception ex) {
				logger.error("Error occured while getting password.", ex);
			}
		}
		return this.password;
	}
	
	public String getEncryptedPassword() {

		return this.encryptedPassword;
	}

	public void setPassword(String password) {

		if (null != password && !password.isEmpty()) {
			AssessmentKey assessmentKey = new AssessmentKey();
			try {
				assessmentKey.setMetaData(password);
				this.encryptedPassword = assessmentKey.encrypt();
			} catch (Exception ex) {
				logger.error("Error occured while setting password.", ex);
			}
		}
	}

	/**
	 * @return the pingStatus
	 */
	public boolean isPingStatus() {
		return pingStatus;
	}
	/**
	 * @param pingStatus the pingStatus to set
	 */
	public void setPingStatus(boolean pingStatus) {
		this.pingStatus = pingStatus;
	}
	/**
	 * @return the configStatus
	 */
	public String getConfigStatus() {
		return configStatus;
	}
	/**
	 * @param configStatus the configStatus to set
	 */
	public void setConfigStatus(String configStatus) {
		this.configStatus = configStatus;
	}
	/**
	 * @return the projectDetails
	 */
	public ProjectDetails getProjectDetails() {
		return projectDetails;
	}
	/**
	 * @param projectDetails the projectDetails to set
	 */
	public void setProjectDetails(ProjectDetails projectDetails) {
		this.projectDetails = projectDetails;
	}
	/**
	 * @return the verficationTimeStamp
	 */
	public Date getVerficationTimeStamp() {
		if (null != verficationTimeStamp) {
			return (Date) verficationTimeStamp.clone();
		} 
		return null;
	}
	/**
	 * @param verficationTimeStamp the verficationTimeStamp to set
	 */
	public void setVerficationTimeStamp(Date verficationTimeStamp) {
		if (null != verficationTimeStamp) {
			this.verficationTimeStamp = (Date) verficationTimeStamp.clone();
		} else {
			this.verficationTimeStamp = null;
		}
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

}