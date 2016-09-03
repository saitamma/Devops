package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="uuidpoolBlock")
public class Uuidpoolblock implements java.io.Serializable {

	private static final long serialVersionUID = -6071114597779641939L;

	private int primaryKey;
	
	private Uuidpoolpool uuidpoolPool;
	
	@XmlAttribute(name="to")
	private String to;
	
	@XmlAttribute(name="from")
	private String from;

	public Uuidpoolblock() {
	}

	public Uuidpoolblock(int primaryKey, Uuidpoolpool uuidpoolpool) {
		this.primaryKey = primaryKey;
		this.uuidpoolPool = uuidpoolpool;
	}

	public Uuidpoolblock(int primaryKey, Uuidpoolpool uuidpoolpool, String to,
			String from) {
		this.primaryKey = primaryKey;
		this.uuidpoolPool = uuidpoolpool;
		this.to = to;
		this.from = from;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Uuidpoolpool getUuidpoolpool() {
		return this.uuidpoolPool;
	}

	public void setUuidpoolpool(Uuidpoolpool uuidpoolpool) {
		this.uuidpoolPool = uuidpoolpool;
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
