package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "lsmaintMaintPolicy")
public class LsmaintMaintPolicy implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int primaryKey;
	
	private Orgorg orgOrg;
	
	@XmlAttribute
	private String descr;
	
	@XmlAttribute
	private String name;
	
	@XmlAttribute
	private String policyOwner;
	
	@XmlAttribute
	private String schedName;
	
	@XmlAttribute
	private String uptimeDisr;
	
	public LsmaintMaintPolicy() {
	}

	public LsmaintMaintPolicy(int primaryKey, Orgorg orgorg) {
		this.primaryKey = primaryKey;
		this.setOrgOrg(orgorg);
	}

	public LsmaintMaintPolicy(int primaryKey, Orgorg orgorg, String policyOwner,
			String descr, String name, String schedName, String uptimeDisr) {
		this.primaryKey = primaryKey;
		this.setOrgOrg(orgorg);
		this.policyOwner = policyOwner;
		this.descr = descr;
		this.name = name;
		this.schedName = schedName;
		this.uptimeDisr = uptimeDisr;
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

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPolicyOwner() {
		return policyOwner;
	}

	public void setPolicyOwner(String policyOwner) {
		this.policyOwner = policyOwner;
	}

	public String getSchedName() {
		return schedName;
	}

	public void setSchedName(String schedName) {
		this.schedName = schedName;
	}

	public String getUptimeDisr() {
		return uptimeDisr;
	}

	public void setUptimeDisr(String uptimeDisr) {
		this.uptimeDisr = uptimeDisr;
	}
	
}
