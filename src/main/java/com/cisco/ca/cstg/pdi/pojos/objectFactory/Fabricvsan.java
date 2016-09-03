package com.cisco.ca.cstg.pdi.pojos.objectFactory;
// Generated Nov 11, 2014 10:41:23 AM by Hibernate Tools 4.0.0

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "fabricVsan")
@XmlAccessorType(XmlAccessType.FIELD)
public class Fabricvsan implements java.io.Serializable {

	private static final long serialVersionUID = -2639159449417262514L;
	private int primaryKey;
	private Fabricsancloud fabricsancloud;
	private Fabricfcsan fabricfcsan;
	private Fabricfcestccloud fabricfcestccloud;
	
	@XmlAttribute(name = "policyOwner")
	private String policyOwner;
	
	@XmlAttribute(name = "id")
	private String id;
	
	@XmlAttribute(name = "zoningState")
	private String zoningState;
	
	@XmlAttribute(name = "name")
	private String name;
	
	@XmlAttribute(name = "fcoeVlan")
	private String fcoeVlan;
	
	@XmlAttribute(name = "text")
	private String text;
	
	@XmlElement(name="fabricFcVsanPortEp")
	private List<Fabricfcvsanportep> fabricfcvsanporteps  = new ArrayList<Fabricfcvsanportep>();

	public Fabricvsan() {
	}

	public Fabricvsan(int primaryKey, Fabricsancloud fabricsancloud,
			Fabricfcsan fabricfcsan, Fabricfcestccloud fabricfcestccloud) {
		this.primaryKey = primaryKey;
		this.fabricsancloud = fabricsancloud;
		this.fabricfcsan = fabricfcsan;
		this.fabricfcestccloud = fabricfcestccloud;
	}

	public Fabricvsan(int primaryKey, Fabricsancloud fabricsancloud,
			Fabricfcsan fabricfcsan, Fabricfcestccloud fabricfcestccloud,
			String policyOwner, String id, String zoningState, String name,
			String fcoeVlan, String text, List<Fabricfcvsanportep> fabricfcvsanporteps) {
		this.primaryKey = primaryKey;
		this.fabricsancloud = fabricsancloud;
		this.fabricfcsan = fabricfcsan;
		this.fabricfcestccloud = fabricfcestccloud;
		this.policyOwner = policyOwner;
		this.id = id;
		this.zoningState = zoningState;
		this.name = name;
		this.fcoeVlan = fcoeVlan;
		this.text = text;
		this.fabricfcvsanporteps = fabricfcvsanporteps;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Fabricsancloud getFabricsancloud() {
		return this.fabricsancloud;
	}

	public void setFabricsancloud(Fabricsancloud fabricsancloud) {
		this.fabricsancloud = fabricsancloud;
	}

	public Fabricfcsan getFabricfcsan() {
		return this.fabricfcsan;
	}

	public void setFabricfcsan(Fabricfcsan fabricfcsan) {
		this.fabricfcsan = fabricfcsan;
	}

	public Fabricfcestccloud getFabricfcestccloud() {
		return this.fabricfcestccloud;
	}

	public void setFabricfcestccloud(Fabricfcestccloud fabricfcestccloud) {
		this.fabricfcestccloud = fabricfcestccloud;
	}

	public String getPolicyOwner() {
		return this.policyOwner;
	}

	public void setPolicyOwner(String policyOwner) {
		this.policyOwner = policyOwner;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getZoningState() {
		return this.zoningState;
	}

	public void setZoningState(String zoningState) {
		this.zoningState = zoningState;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFcoeVlan() {
		return this.fcoeVlan;
	}

	public void setFcoeVlan(String fcoeVlan) {
		this.fcoeVlan = fcoeVlan;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Fabricfcvsanportep> getFabricfcvsanporteps() {
		return fabricfcvsanporteps;
	}

	public void setFabricfcvsanporteps(List<Fabricfcvsanportep> fabricfcvsanporteps) {
		this.fabricfcvsanporteps = fabricfcvsanporteps;
	}

}
