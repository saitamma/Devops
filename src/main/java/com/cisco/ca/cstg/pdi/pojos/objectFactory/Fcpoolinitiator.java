package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fcpoolInitiator")
public class Fcpoolinitiator implements java.io.Serializable {

	private static final long serialVersionUID = -2718928832484168714L;
	private int primaryKey;
	private Fcpoolinitiators fcpoolinitiators;
	
	@XmlAttribute
	private String name;
	
	@XmlAttribute
	private String id;
	
	@XmlAttribute
	private String descr;

	public Fcpoolinitiator() {
	}

	public Fcpoolinitiator(int primaryKey, Fcpoolinitiators fcpoolinitiators) {
		this.primaryKey = primaryKey;
		this.fcpoolinitiators = fcpoolinitiators;
	}

	public Fcpoolinitiator(int primaryKey, Fcpoolinitiators fcpoolinitiators,
			String name, String id, String descr) {
		this.primaryKey = primaryKey;
		this.fcpoolinitiators = fcpoolinitiators;
		this.name = name;
		this.id = id;
		this.descr = descr;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Fcpoolinitiators getFcpoolinitiators() {
		return this.fcpoolinitiators;
	}

	public void setFcpoolinitiators(Fcpoolinitiators fcpoolinitiators) {
		this.fcpoolinitiators = fcpoolinitiators;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

}
