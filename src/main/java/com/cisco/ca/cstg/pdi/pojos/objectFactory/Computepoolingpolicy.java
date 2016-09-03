package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "computePoolingPolicy")
public class Computepoolingpolicy implements java.io.Serializable {

	private static final long serialVersionUID = 5950490430550305288L;
	private int primaryKey;
	private Orgorg orgorg;
	
	@XmlAttribute
	private String policyOwner;
	
	@XmlAttribute
	private String descr;
	
	@XmlAttribute
	private String qualifier;
	
	@XmlAttribute
	private String poolDn;
	
	@XmlAttribute
	private String name;

	public Computepoolingpolicy() {
	}

	public Computepoolingpolicy(int primaryKey, Orgorg orgorg) {
		this.primaryKey = primaryKey;
		this.orgorg = orgorg;
	}

	public Computepoolingpolicy(int primaryKey, Orgorg orgorg,
			String policyOwner, String descr, String qualifier, String poolDn,
			String name) {
		this.primaryKey = primaryKey;
		this.orgorg = orgorg;
		this.policyOwner = policyOwner;
		this.descr = descr;
		this.qualifier = qualifier;
		this.poolDn = poolDn;
		this.name = name;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Orgorg getOrgorg() {
		return this.orgorg;
	}

	public void setOrgorg(Orgorg orgorg) {
		this.orgorg = orgorg;
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

	public String getQualifier() {
		return this.qualifier;
	}

	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	public String getPoolDn() {
		return this.poolDn;
	}

	public void setPoolDn(String poolDn) {
		this.poolDn = poolDn;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
