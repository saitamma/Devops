package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
// Generated Nov 11, 2014 10:41:23 AM by Hibernate Tools 4.0.0
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "fabricVlan")
@XmlAccessorType(XmlAccessType.FIELD)
public class Fabricvlan implements java.io.Serializable {

	private static final long serialVersionUID = -7480537955638693807L;
	private int primaryKey;
	private Fabriclancloud fabriclancloud;
	private Fabricethestccloud fabricethestccloud;
	
	@XmlAttribute(name = "policyOwner")
	private String policyOwner;
	
	@XmlAttribute(name = "sharing")
	private String sharing;
	
	@XmlAttribute(name = "pubNwName")
	private String pubNwName;
	
	@XmlAttribute(name = "name")
	private String name;
	
	@XmlAttribute(name = "mcastPolicyName")
	private String mcastPolicyName;
	
	@XmlAttribute(name = "id")
	private String id;
	
	@XmlAttribute(name = "defaultNet")
	private String defaultNet;
	
	@XmlAttribute(name = "compressionType")
	private String compressionType;
	
	@XmlAttribute(name = "text")
	private String text;

	public Fabricvlan() {
	}

	public Fabricvlan(int primaryKey, Fabriclancloud fabriclancloud,
			Fabricethestccloud fabricethestccloud) {
		this.primaryKey = primaryKey;
		this.fabriclancloud = fabriclancloud;
		this.fabricethestccloud = fabricethestccloud;
	}

	public Fabricvlan(int primaryKey, Fabriclancloud fabriclancloud,
			Fabricethestccloud fabricethestccloud, String policyOwner,
			String sharing, String pubNwName, String name,
			String mcastPolicyName, String id, String defaultNet,
			String compressionType, String text) {
		this.primaryKey = primaryKey;
		this.fabriclancloud = fabriclancloud;
		this.fabricethestccloud = fabricethestccloud;
		this.policyOwner = policyOwner;
		this.sharing = sharing;
		this.pubNwName = pubNwName;
		this.name = name;
		this.mcastPolicyName = mcastPolicyName;
		this.id = id;
		this.defaultNet = defaultNet;
		this.compressionType = compressionType;
		this.text = text;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Fabriclancloud getFabriclancloud() {
		return this.fabriclancloud;
	}

	public void setFabriclancloud(Fabriclancloud fabriclancloud) {
		this.fabriclancloud = fabriclancloud;
	}

	public Fabricethestccloud getFabricethestccloud() {
		return this.fabricethestccloud;
	}

	public void setFabricethestccloud(Fabricethestccloud fabricethestccloud) {
		this.fabricethestccloud = fabricethestccloud;
	}

	public String getPolicyOwner() {
		return this.policyOwner;
	}

	public void setPolicyOwner(String policyOwner) {
		this.policyOwner = policyOwner;
	}

	public String getSharing() {
		return this.sharing;
	}

	public void setSharing(String sharing) {
		this.sharing = sharing;
	}

	public String getPubNwName() {
		return this.pubNwName;
	}

	public void setPubNwName(String pubNwName) {
		this.pubNwName = pubNwName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMcastPolicyName() {
		return this.mcastPolicyName;
	}

	public void setMcastPolicyName(String mcastPolicyName) {
		this.mcastPolicyName = mcastPolicyName;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDefaultNet() {
		return this.defaultNet;
	}

	public void setDefaultNet(String defaultNet) {
		this.defaultNet = defaultNet;
	}

	public String getCompressionType() {
		return this.compressionType;
	}

	public void setCompressionType(String compressionType) {
		this.compressionType = compressionType;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
