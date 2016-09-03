package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mgmtBackupPolicy")
public class Mgmtbackuppolicy implements java.io.Serializable {

	private static final long serialVersionUID = 1023654548568508555L;
	private int primaryKey;
	private Orgorg orgOrg;
	
	@XmlAttribute
	private String adminState;
	@XmlAttribute
	private String descr;
	@XmlAttribute
	private String host;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String policyOwner;
	@XmlAttribute
	private String proto;
	@XmlAttribute
	private String pwd;
	@XmlAttribute
	private String remoteFile;
	@XmlAttribute
	private String schedule;
	@XmlAttribute
	private String user;

	public Mgmtbackuppolicy() {
	}

	public Mgmtbackuppolicy(Orgorg orgOrg) {
		this.orgOrg = orgOrg;
	}

	public Mgmtbackuppolicy(Orgorg orgOrg, String adminState, String descr,
			String host, String name, String policyOwner, String proto,
			String pwd, String remoteFile, String schedule, String user) {
		this.orgOrg = orgOrg;
		this.adminState = adminState;
		this.descr = descr;
		this.host = host;
		this.name = name;
		this.policyOwner = policyOwner;
		this.proto = proto;
		this.pwd = pwd;
		this.remoteFile = remoteFile;
		this.schedule = schedule;
		this.user = user;
	}

	public int getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Orgorg getOrgOrg() {
		return orgOrg;
	}

	public void setOrgOrg(Orgorg orgOrg) {
		this.orgOrg = orgOrg;
	}

	public String getAdminState() {
		return this.adminState;
	}

	public void setAdminState(String adminState) {
		this.adminState = adminState;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getHost() {
		return this.host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPolicyOwner() {
		return this.policyOwner;
	}

	public void setPolicyOwner(String policyOwner) {
		this.policyOwner = policyOwner;
	}

	public String getProto() {
		return this.proto;
	}

	public void setProto(String proto) {
		this.proto = proto;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getRemoteFile() {
		return this.remoteFile;
	}

	public void setRemoteFile(String remoteFile) {
		this.remoteFile = remoteFile;
	}

	public String getSchedule() {
		return this.schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
