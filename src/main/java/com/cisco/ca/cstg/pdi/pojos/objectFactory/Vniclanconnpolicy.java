package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "vnicLanConnPolicy")
public class Vniclanconnpolicy implements java.io.Serializable {

	private static final long serialVersionUID = -4593808566358085094L;
	private int primaryKey;
	private Orgorg orgOrg;
	@XmlAttribute
	private String policyOwner;
	@XmlAttribute
	private String descr;
	@XmlAttribute
	private String name;
	
	List<Vnicether> vnicEther = new ArrayList<Vnicether>();

	public Vniclanconnpolicy() {
	}

	public Vniclanconnpolicy(int primaryKey, Orgorg orgorg) {
		this.primaryKey = primaryKey;
		this.orgOrg = orgorg;
	}

	
	public Vniclanconnpolicy(Orgorg orgOrg, String policyOwner,
			String descr, String name, List<Vnicether> vnicEther) {
		super();
		this.orgOrg = orgOrg;
		this.policyOwner = policyOwner;
		this.descr = descr;
		this.name = name;
		this.vnicEther = vnicEther;
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

	public String getPolicyOwner() {
		return this.policyOwner;
	}

	public void setPolicyOwner(String policyOwner) {
		this.policyOwner = policyOwner;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Vnicether> getVnicEther() {
		return vnicEther;
	}

	public void setVnicEther(List<Vnicether> vnicEther) {
		this.vnicEther = vnicEther;
	}
	
}
