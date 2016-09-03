package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "commSvcEp")
public class Commsvcep implements java.io.Serializable {

	private static final long serialVersionUID = 2016564790362194380L;

	private Topsystem topSystem;
	
	private int primaryKey;

	@XmlAttribute(name = "policyOwner")
	private String policyOwner;

	@XmlAttribute(name = "descr")
	private String descr;

	@XmlAttribute(name = "name")
	private String name;

	private List<Commdatetime> commDateTime  = new ArrayList<Commdatetime>();
	private List<Commdns> commDns  = new ArrayList<Commdns>();

	public Commsvcep() {
	}



	public Commsvcep(Topsystem topSystem, int primaryKey, String policyOwner,
			String descr, String name, List<Commdatetime> commDateTime,
			List<Commdns> commDns) {
		super();
		this.topSystem = topSystem;
		this.primaryKey = primaryKey;
		this.policyOwner = policyOwner;
		this.descr = descr;
		this.name = name;
		this.commDateTime = commDateTime;
		this.commDns = commDns;
	}



	public int getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Topsystem getTopSystem() {
		return topSystem;
	}

	public void setTopSystem(Topsystem topSystem) {
		this.topSystem = topSystem;
	}

	public String getPolicyOwner() {
		return policyOwner;
	}

	public void setPolicyOwner(String policyOwner) {
		this.policyOwner = policyOwner;
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

	public List<Commdatetime> getCommDateTime() {
		return commDateTime;
	}

	public void setCommDateTime(List<Commdatetime> commDateTime) {
		this.commDateTime = commDateTime;
	}

	public List<Commdns> getCommDns() {
		return commDns;
	}

	public void setCommDns(List<Commdns> commDns) {
		this.commDns = commDns;
	}

	

}
