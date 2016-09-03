package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "lsbootLan")
public class Lsbootlan implements java.io.Serializable {

	private static final long serialVersionUID = 4651218063869449072L;
	private int primaryKey;
	private Lsbootpolicy lsbootpolicy;
	
	@XmlAttribute
	private String prot;
	
	@XmlAttribute
	private String order;
	
	@XmlElement
	private List<Lsbootlanimagepath> lsbootLanImagePath = new ArrayList<Lsbootlanimagepath>();

	public Lsbootlan() {
	}

	public Lsbootlan(int primaryKey, Lsbootpolicy lsbootpolicy) {
		this.primaryKey = primaryKey;
		this.lsbootpolicy = lsbootpolicy;
	}

	public Lsbootlan(int primaryKey, Lsbootpolicy lsbootpolicy, String prot,
			String order, List<Lsbootlanimagepath> lsbootlanimagepaths) {
		this.primaryKey = primaryKey;
		this.lsbootpolicy = lsbootpolicy;
		this.prot = prot;
		this.order = order;
		this.lsbootLanImagePath = lsbootlanimagepaths;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Lsbootpolicy getLsbootpolicy() {
		return this.lsbootpolicy;
	}

	public void setLsbootpolicy(Lsbootpolicy lsbootpolicy) {
		this.lsbootpolicy = lsbootpolicy;
	}

	public String getProt() {
		return this.prot;
	}

	public void setProt(String prot) {
		this.prot = prot;
	}

	public String getOrder() {
		return this.order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public List<Lsbootlanimagepath> getLsbootLanImagePath() {
		return lsbootLanImagePath;
	}

	public void setLsbootLanImagePath(List<Lsbootlanimagepath> lsbootLanImagePath) {
		this.lsbootLanImagePath = lsbootLanImagePath;
	}

}
