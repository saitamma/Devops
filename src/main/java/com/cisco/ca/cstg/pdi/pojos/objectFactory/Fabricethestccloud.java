package com.cisco.ca.cstg.pdi.pojos.objectFactory;
// Generated Nov 11, 2014 10:41:23 AM by Hibernate Tools 4.0.0

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "fabricEthEstcCloud")
@XmlAccessorType(XmlAccessType.FIELD)
public class Fabricethestccloud implements java.io.Serializable {

	private static final long serialVersionUID = 5937199911551505263L;
	private int primaryKey;
	private Fabricep fabricep;
	
	private List<Fabricvlan> fabricVlan  = new ArrayList<Fabricvlan>();
	private List<Fabricethestc> fabricEthEstc  = new ArrayList<Fabricethestc>();
	private List<Nwctrldefinition> nwctrlDefinition  = new ArrayList<Nwctrldefinition>();
	private List<Statsthresholdpolicy> statsThresholdPolicy  = new ArrayList<Statsthresholdpolicy>();

	public Fabricethestccloud() {
	}

	public Fabricethestccloud(int primaryKey, Fabricep fabricep) {
		this.primaryKey = primaryKey;
		this.fabricep = fabricep;
	}

	public Fabricethestccloud(int primaryKey, Fabricep fabricep,
			List<Fabricvlan> fabricVlan, List<Fabricethestc> fabricEthEstc, List<Nwctrldefinition> nwctrlDefinition,
			List<Statsthresholdpolicy> statsThresholdPolicy) {
		this.primaryKey = primaryKey;
		this.fabricep = fabricep;
		this.fabricVlan = fabricVlan;
		this.fabricEthEstc = fabricEthEstc;
		this.nwctrlDefinition = nwctrlDefinition;
		this.statsThresholdPolicy = statsThresholdPolicy;
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

	public List<Fabricvlan> getFabricVlan() {
		return fabricVlan;
	}

	public void setFabricVlan(List<Fabricvlan> fabricVlan) {
		this.fabricVlan = fabricVlan;
	}

	public List<Fabricethestc> getFabricEthEstc() {
		return fabricEthEstc;
	}

	public void setFabricEthEstc(List<Fabricethestc> fabricEthEstc) {
		this.fabricEthEstc = fabricEthEstc;
	}

	public List<Nwctrldefinition> getNwctrlDefinition() {
		return nwctrlDefinition;
	}

	public void setNwctrlDefinition(List<Nwctrldefinition> nwctrlDefinition) {
		this.nwctrlDefinition = nwctrlDefinition;
	}

	public List<Statsthresholdpolicy> getStatsThresholdPolicy() {
		return statsThresholdPolicy;
	}

	public void setStatsThresholdPolicy(
			List<Statsthresholdpolicy> statsThresholdPolicy) {
		this.statsThresholdPolicy = statsThresholdPolicy;
	}

}
