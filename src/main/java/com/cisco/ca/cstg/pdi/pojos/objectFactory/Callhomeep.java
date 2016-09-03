package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "callhomeEp")
public class Callhomeep implements java.io.Serializable {

	private static final long serialVersionUID = -4797233714778188423L;
	private int primaryKey;
	private Toproot toproot;
	@XmlAttribute
	private String adminState;
	@XmlAttribute
	private String alertThrottlingAdminState;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String descr;
	@XmlAttribute
	private String policyOwner;
	@XmlElement(name="callhomeSmtp")
	private List<Callhomesmtp> callhomesmtps = new ArrayList<Callhomesmtp>();
	@XmlElement(name="callhomeSource")
	private List<Callhomesource> callhomesources = new ArrayList<Callhomesource>();
	@XmlElement(name="callhomePolicy")
	private List<Callhomepolicy> callhomepolicies = new ArrayList<Callhomepolicy>();
	@XmlElement(name="callhomePeriodicSystemInventory")
	private List<Callhomeperiodicsysteminventory> callhomeperiodicsysteminventories = new ArrayList<Callhomeperiodicsysteminventory>();
	@XmlElement(name="callhomeProfile")
	private List<Callhomeprofile> callhomeprofiles = new ArrayList<Callhomeprofile>();
	@XmlElement(name="callhomeTestAlert")
	private List<Callhometestalert> callhometestalerts = new ArrayList<Callhometestalert>();

	public Callhomeep() {
	}

	public Callhomeep(Toproot toproot) {
		this.toproot = toproot;
	}

	public Callhomeep(
			Toproot toproot,
			String adminState,
			String alertThrottlingAdminState,
			String name,
			String descr,
			String policyOwner,
			List<Callhomesmtp> callhomesmtps,
			List<Callhomesource> callhomesources,
			List<Callhomepolicy> callhomepolicies,
			List<Callhomeperiodicsysteminventory> callhomeperiodicsysteminventories,
			List<Callhomeprofile> callhomeprofiles,
			List<Callhometestalert> callhometestalerts) {
		super();
		this.toproot = toproot;
		this.adminState = adminState;
		this.alertThrottlingAdminState = alertThrottlingAdminState;
		this.name = name;
		this.descr = descr;
		this.policyOwner = policyOwner;
		this.callhomesmtps = callhomesmtps;
		this.callhomesources = callhomesources;
		this.callhomepolicies = callhomepolicies;
		this.callhomeperiodicsysteminventories = callhomeperiodicsysteminventories;
		this.callhomeprofiles = callhomeprofiles;
		this.callhometestalerts = callhometestalerts;
	}

	public int getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Toproot getToproot() {
		return toproot;
	}

	public void setToproot(Toproot toproot) {
		this.toproot = toproot;
	}

	public String getAdminState() {
		return adminState;
	}

	public void setAdminState(String adminState) {
		this.adminState = adminState;
	}

	public String getAlertThrottlingAdminState() {
		return alertThrottlingAdminState;
	}

	public void setAlertThrottlingAdminState(String alertThrottlingAdminState) {
		this.alertThrottlingAdminState = alertThrottlingAdminState;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getPolicyOwner() {
		return policyOwner;
	}

	public void setPolicyOwner(String policyOwner) {
		this.policyOwner = policyOwner;
	}

	public List<Callhomesmtp> getCallhomesmtps() {
		return callhomesmtps;
	}

	public void setCallhomesmtps(List<Callhomesmtp> callhomesmtps) {
		this.callhomesmtps = callhomesmtps;
	}

	public List<Callhomesource> getCallhomesources() {
		return callhomesources;
	}

	public void setCallhomesources(List<Callhomesource> callhomesources) {
		this.callhomesources = callhomesources;
	}

	public List<Callhomepolicy> getCallhomepolicies() {
		return callhomepolicies;
	}

	public void setCallhomepolicies(List<Callhomepolicy> callhomepolicies) {
		this.callhomepolicies = callhomepolicies;
	}

	public List<Callhomeperiodicsysteminventory> getCallhomeperiodicsysteminventories() {
		return callhomeperiodicsysteminventories;
	}

	public void setCallhomeperiodicsysteminventories(
			List<Callhomeperiodicsysteminventory> callhomeperiodicsysteminventories) {
		this.callhomeperiodicsysteminventories = callhomeperiodicsysteminventories;
	}

	public List<Callhomeprofile> getCallhomeprofiles() {
		return callhomeprofiles;
	}

	public void setCallhomeprofiles(List<Callhomeprofile> callhomeprofiles) {
		this.callhomeprofiles = callhomeprofiles;
	}

	public List<Callhometestalert> getCallhometestalerts() {
		return callhometestalerts;
	}

	public void setCallhometestalerts(List<Callhometestalert> callhometestalerts) {
		this.callhometestalerts = callhometestalerts;
	}

}
