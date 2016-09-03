package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "commDateTime")
public class Commdatetime implements java.io.Serializable {

	private static final long serialVersionUID = -7145260554203669424L;

	private Commsvcep commSvcEp;
	
	private int primaryKey;

	@XmlAttribute(name = "policyOwner")
	private String policyOwner;

	@XmlAttribute(name = "descr")
	private String descr;

	@XmlAttribute(name = "name")
	private String name;

	@XmlAttribute(name = "port")
	private String port;

	@XmlAttribute(name = "adminState")
	private String adminState;

	@XmlAttribute(name = "timezone")
	private String timezone;

	private List<Commntpprovider> commNtpProvider  = new ArrayList<Commntpprovider>();

	public Commdatetime() {
	}

	public Commdatetime(Commsvcep commSvcEp, int primaryKey,
			String policyOwner, String descr, String name, String port,
			String adminState, String timezone,
			List<Commntpprovider> commNtpProvider) {
		super();
		this.commSvcEp = commSvcEp;
		this.primaryKey = primaryKey;
		this.policyOwner = policyOwner;
		this.descr = descr;
		this.name = name;
		this.port = port;
		this.adminState = adminState;
		this.timezone = timezone;
		this.commNtpProvider = commNtpProvider;
	}


	public int getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Commsvcep getCommSvcEp() {
		return commSvcEp;
	}

	public void setCommSvcEp(Commsvcep commSvcEp) {
		this.commSvcEp = commSvcEp;
	}

	public String getPolicyOwner() {
		return policyOwner;
	}

	public void setPolicyOwner(String policyOwner) {
		this.policyOwner = policyOwner;
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

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getAdminState() {
		return adminState;
	}

	public void setAdminState(String adminState) {
		this.adminState = adminState;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public List<Commntpprovider> getCommNtpProvider() {
		return commNtpProvider;
	}

	public void setCommNtpProvider(List<Commntpprovider> commNtpProvider) {
		this.commNtpProvider = commNtpProvider;
	}

}
