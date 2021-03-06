package com.cisco.ca.cstg.pdi.pojos.objectFactory;
// Generated Nov 11, 2014 10:41:23 AM by Hibernate Tools 4.0.0

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Fabricfcestccloud generated by hbm2java
 */
@XmlRootElement(name = "fabricFcEstcCloud")
@XmlAccessorType(XmlAccessType.FIELD)
public class Fabricfcestccloud implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5153637307910863471L;
	private int primaryKey;
	private Fabricep fabricep;
	
	@XmlElement(name="fabricFcEstc")
	private List<Fabricfcestc> fabricfcestcs  = new ArrayList<Fabricfcestc>();
	
	@XmlElement(name="fabricBHVlan")
	private List<Fabricbhvlan> fabricbhvlans  = new ArrayList<Fabricbhvlan>();
	
	@XmlElement(name="statsThresholdPolicy")
	private List<Statsthresholdpolicy> statsthresholdpolicies  = new ArrayList<Statsthresholdpolicy>();
	
	@XmlElement(name="fabricVsan")
	private List<Fabricvsan> fabricvsans  = new ArrayList<Fabricvsan>();

	public Fabricfcestccloud() {
	}

	public Fabricfcestccloud(int primaryKey, Fabricep fabricep) {
		this.primaryKey = primaryKey;
		this.fabricep = fabricep;
	}

	public Fabricfcestccloud(int primaryKey, Fabricep fabricep,
			List<Fabricfcestc> fabricfcestcs, List<Fabricbhvlan> fabricbhvlans, List<Statsthresholdpolicy> statsthresholdpolicies,
			List<Fabricvsan> fabricvsans) {
		this.primaryKey = primaryKey;
		this.fabricep = fabricep;
		this.fabricfcestcs = fabricfcestcs;
		this.fabricbhvlans = fabricbhvlans;
		this.statsthresholdpolicies = statsthresholdpolicies;
		this.fabricvsans = fabricvsans;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Fabricep getFabricep() {
		return this.fabricep;
	}

	public void setFabricep(Fabricep fabricep) {
		this.fabricep = fabricep;
	}

	public List<Fabricfcestc> getFabricfcestcs() {
		return fabricfcestcs;
	}

	public void setFabricfcestcs(List<Fabricfcestc> fabricfcestcs) {
		this.fabricfcestcs = fabricfcestcs;
	}

	public List<Fabricbhvlan> getFabricbhvlans() {
		return fabricbhvlans;
	}

	public void setFabricbhvlans(List<Fabricbhvlan> fabricbhvlans) {
		this.fabricbhvlans = fabricbhvlans;
	}

	public List<Statsthresholdpolicy> getStatsthresholdpolicies() {
		return statsthresholdpolicies;
	}

	public void setStatsthresholdpolicies(
			List<Statsthresholdpolicy> statsthresholdpolicies) {
		this.statsthresholdpolicies = statsthresholdpolicies;
	}

	public List<Fabricvsan> getFabricvsans() {
		return fabricvsans;
	}

	public void setFabricvsans(List<Fabricvsan> fabricvsans) {
		this.fabricvsans = fabricvsans;
	}

}
