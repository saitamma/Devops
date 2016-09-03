package com.cisco.ca.cstg.pdi.pojos.objectFactory;
// Generated Nov 11, 2014 10:41:23 AM by Hibernate Tools 4.0.0

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "fabricEthLan")
@XmlAccessorType(XmlAccessType.FIELD)
public class Fabricethlan implements java.io.Serializable {

	private static final long serialVersionUID = 4931981144876410740L;
	private int primaryKey;
	private Fabriclancloud fabriclancloud;
	
	@XmlAttribute(name="name")
	private String name;
	
	@XmlAttribute(name="id")
	private String id;
	
	private List<Fabricethlanpc> fabricEthLanPc   = new ArrayList<Fabricethlanpc>();
	private List<Fabricethlanep> fabricEthLanEp   = new ArrayList<Fabricethlanep>();
	public Fabricethlan() {
	}

	public Fabricethlan(int primaryKey, Fabriclancloud fabriclancloud) {
		this.primaryKey = primaryKey;
		this.fabriclancloud = fabriclancloud;
	}

	public Fabricethlan(int primaryKey, Fabriclancloud fabriclancloud,
			String name, String id, List<Fabricethlanpc> fabricEthLanPc, List<Fabricethlanep> fabricEthLanEp) {
		this.primaryKey = primaryKey;
		this.fabriclancloud = fabriclancloud;
		this.name = name;
		this.id = id;
		this.fabricEthLanPc = fabricEthLanPc;
		this.fabricEthLanEp = fabricEthLanEp;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Fabricethlanpc> getFabricEthLanPc() {
		return fabricEthLanPc;
	}

	public void setFabricEthLanPc(List<Fabricethlanpc> fabricEthLanPc) {
		this.fabricEthLanPc = fabricEthLanPc;
	}

	public List<Fabricethlanep> getFabricEthLanEp() {
		return fabricEthLanEp;
	}

	public void setFabricEthLanEp(List<Fabricethlanep> fabricEthLanEp) {
		this.fabricEthLanEp = fabricEthLanEp;
	}

}
