package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "computePool")
public class Computepool implements java.io.Serializable {

	private static final long serialVersionUID = 200989574841772455L;
	
	private int primaryKey;
	
	private Orgorg orgOrg;
	
	@XmlAttribute
	private String policyOwner;
	
	@XmlAttribute
	private String descr;
	
	@XmlAttribute
	private String name;
	
	@XmlAttribute
	private String text;

	public Computepool() {
	}

	public Computepool(int primaryKey, Orgorg orgorg) {
		this.primaryKey = primaryKey;
		this.setOrgOrg(orgorg);
	}

	public Computepool(int primaryKey, Orgorg orgorg, String policyOwner,
			String descr, String name, String text) {
		this.primaryKey = primaryKey;
		this.setOrgOrg(orgorg);
		this.policyOwner = policyOwner;
		this.descr = descr;
		this.name = name;
		this.text = text;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
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

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
