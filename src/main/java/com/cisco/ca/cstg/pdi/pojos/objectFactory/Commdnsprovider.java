package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="commDnsProvider")
public class Commdnsprovider implements java.io.Serializable {

	private static final long serialVersionUID = 5599062908261264155L;
	
	private Commdns commDns;
	
	private int primaryKey;
	
	@XmlAttribute(name="descr")
	private String descr;
	
	@XmlAttribute(name="name")
	private String name;

	public Commdnsprovider() {
	}

	public Commdnsprovider(Commdns commDns, int primaryKey, String descr,
			String name) {
		super();
		this.commDns = commDns;
		this.primaryKey = primaryKey;
		this.descr = descr;
		this.name = name;
	}

	public int getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Commdns getCommDns() {
		return commDns;
	}

	public void setCommDns(Commdns commDns) {
		this.commDns = commDns;
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

	
}
