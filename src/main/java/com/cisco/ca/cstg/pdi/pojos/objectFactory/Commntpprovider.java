package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="commNtpProvider")
public class Commntpprovider implements java.io.Serializable {

	private static final long serialVersionUID = -5936506318428171859L;
	
	private Commdatetime commDateTime;
	
	private int primaryKey;
	
	@XmlAttribute(name="descr")
	private String descr;
	
	@XmlAttribute(name="name")
	private String name;

	public Commntpprovider() {
	}

	public Commntpprovider(Commdatetime commDateTime, int primaryKey,
			String descr, String name) {
		super();
		this.commDateTime = commDateTime;
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

	public Commdatetime getCommDateTime() {
		return commDateTime;
	}

	public void setCommDateTime(Commdatetime commDateTime) {
		this.commDateTime = commDateTime;
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
