package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
// Generated Nov 11, 2014 10:41:23 AM by Hibernate Tools 4.0.0

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="macpoolBlock")
public class Macpoolblock implements java.io.Serializable {

	private static final long serialVersionUID = 2378654778083015436L;
	private int primaryKey;
	private Macpoolpool macpoolPool;
	
	@XmlAttribute
	private String to;
	
	@XmlAttribute
	private String from;

	public Macpoolblock() {
	}

	public Macpoolblock(int primaryKey, Macpoolpool macpoolpool) {
		this.primaryKey = primaryKey;
		this.macpoolPool = macpoolpool;
	}

	public Macpoolblock(int primaryKey, Macpoolpool macpoolpool, String to,
			String from) {
		this.primaryKey = primaryKey;
		this.macpoolPool = macpoolpool;
		this.to = to;
		this.from = from;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Macpoolpool getMacpoolpool() {
		return this.macpoolPool;
	}

	public void setMacpoolpool(Macpoolpool macpoolpool) {
		this.macpoolPool = macpoolpool;
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
