package com.cisco.ca.cstg.pdi.pojos.objectFactory;
// Generated Nov 11, 2014 10:41:23 AM by Hibernate Tools 4.0.0

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "fabricSanCloud")
@XmlAccessorType(XmlAccessType.FIELD)
public class Fabricsancloud implements java.io.Serializable {

	private static final long serialVersionUID = -6655659721973442566L;
	private int primaryKey;
	private Fabricep fabricep;
	
	@XmlAttribute(name="mode")
	private String mode;
	
	private List<Statsthresholdpolicy> statsThresholdPolicy  = new ArrayList<Statsthresholdpolicy>();
	private List<Fabricvsan> fabricVsan  = new ArrayList<Fabricvsan>();
	private List<Fabricfcsan> fabricFcSan  = new ArrayList<Fabricfcsan>();

	public Fabricsancloud() {
	}

	public Fabricsancloud(int primaryKey, Fabricep fabricep) {
		this.primaryKey = primaryKey;
		this.fabricep = fabricep;
	}

	public Fabricsancloud(int primaryKey, Fabricep fabricep, String mode,
			List<Statsthresholdpolicy> statsThresholdPolicy, List<Fabricvsan> fabricVsan, List<Fabricfcsan> fabricFcSan) {
		this.primaryKey = primaryKey;
		this.fabricep = fabricep;
		this.mode = mode;
		this.statsThresholdPolicy = statsThresholdPolicy;
		this.fabricVsan = fabricVsan;
		this.fabricFcSan = fabricFcSan;
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

	public String getMode() {
		return this.mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public List<Statsthresholdpolicy> getStatsThresholdPolicy() {
		return statsThresholdPolicy;
	}

	public void setStatsThresholdPolicy(
			List<Statsthresholdpolicy> statsThresholdPolicy) {
		this.statsThresholdPolicy = statsThresholdPolicy;
	}

	public List<Fabricvsan> getFabricVsan() {
		return fabricVsan;
	}

	public void setFabricVsan(List<Fabricvsan> fabricVsan) {
		this.fabricVsan = fabricVsan;
	}

	public List<Fabricfcsan> getFabricFcSan() {
		return fabricFcSan;
	}

	public void setFabricFcSan(List<Fabricfcsan> fabricFcSan) {
		this.fabricFcSan = fabricFcSan;
	}


}
