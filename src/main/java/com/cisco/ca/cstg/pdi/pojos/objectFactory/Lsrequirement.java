package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lsRequirement")
public class Lsrequirement implements java.io.Serializable {

	private static final long serialVersionUID = -4204186887774781721L;
	private int primaryKey;
	private Lsserver lsserver;
	
	@XmlAttribute
	private String restrictMigration;
	
	@XmlAttribute
	private String qualifier;
	
	@XmlAttribute
	private String name;

	public Lsrequirement() {
	}

	public Lsrequirement(int primaryKey, Lsserver lsserver) {
		this.primaryKey = primaryKey;
		this.lsserver = lsserver;
	}

	public Lsrequirement(int primaryKey, Lsserver lsserver,
			String restrictMigration, String qualifier, String name) {
		this.primaryKey = primaryKey;
		this.lsserver = lsserver;
		this.restrictMigration = restrictMigration;
		this.qualifier = qualifier;
		this.name = name;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Lsserver getLsserver() {
		return this.lsserver;
	}

	public void setLsserver(Lsserver lsserver) {
		this.lsserver = lsserver;
	}

	public String getRestrictMigration() {
		return this.restrictMigration;
	}

	public void setRestrictMigration(String restrictMigration) {
		this.restrictMigration = restrictMigration;
	}

	public String getQualifier() {
		return this.qualifier;
	}

	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
