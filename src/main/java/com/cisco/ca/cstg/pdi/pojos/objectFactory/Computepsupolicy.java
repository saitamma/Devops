package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "computePsuPolicy")
public class Computepsupolicy implements java.io.Serializable {

	private static final long serialVersionUID = 6002917504109877859L;
	private int primaryKey;
	private Orgorg orgorg;
	
	@XmlAttribute
	private String policyOwner;
	
	@XmlAttribute
	private String descr;
	
	@XmlAttribute
	private String redundancy;

	public Computepsupolicy() {
	}

	public Computepsupolicy(int primaryKey, Orgorg orgorg) {
		this.primaryKey = primaryKey;
		this.orgorg = orgorg;
	}

	public Computepsupolicy(int primaryKey, Orgorg orgorg, String policyOwner,
			String descr, String redundancy) {
		this.primaryKey = primaryKey;
		this.orgorg = orgorg;
		this.policyOwner = policyOwner;
		this.descr = descr;
		this.redundancy = redundancy;
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

	public String getRedundancy() {
		return this.redundancy;
	}

	public void setRedundancy(String redundancy) {
		this.redundancy = redundancy;
	}

}
