package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fcpoolBlock")
public class Fcpoolblock implements java.io.Serializable {

	private static final long serialVersionUID = -185384343525303887L;
	private int primaryKey;
	private Fcpoolinitiators fcpoolinitiators;
	
	@XmlAttribute
	private String to;
	
	@XmlAttribute
	private String from;

	public Fcpoolblock() {
	}

	public Fcpoolblock(int primaryKey, Fcpoolinitiators fcpoolinitiators) {
		this.primaryKey = primaryKey;
		this.fcpoolinitiators = fcpoolinitiators;
	}

	public Fcpoolblock(int primaryKey, Fcpoolinitiators fcpoolinitiators,
			String to, String from) {
		this.primaryKey = primaryKey;
		this.fcpoolinitiators = fcpoolinitiators;
		this.to = to;
		this.from = from;
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

	public String getTo() {
		return this.to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return this.from;
	}

	public void setFrom(String from) {
		this.from = from;
	}
}
