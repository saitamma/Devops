package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ippoolBlock")
public class Ippoolblock implements java.io.Serializable {

	private static final long serialVersionUID = -7790924042691671688L;
	private int primaryKey;
	private Ippoolpool ippoolpool;
	
	@XmlAttribute
	private String to;
	
	@XmlAttribute
	private String subnet;
	
	@XmlAttribute
	private String primDns;
	
	@XmlAttribute
	private String from;
	
	@XmlAttribute
	private String defGw;

	public Ippoolblock() {
	}

	public Ippoolblock(int primaryKey, Ippoolpool ippoolpool) {
		this.primaryKey = primaryKey;
		this.ippoolpool = ippoolpool;
	}

	public Ippoolblock(int primaryKey, Ippoolpool ippoolpool, String to,
			String subnet, String primDns, String from, String defGw) {
		this.primaryKey = primaryKey;
		this.ippoolpool = ippoolpool;
		this.to = to;
		this.subnet = subnet;
		this.primDns = primDns;
		this.from = from;
		this.defGw = defGw;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Ippoolpool getIppoolpool() {
		return this.ippoolpool;
	}

	public void setIppoolpool(Ippoolpool ippoolpool) {
		this.ippoolpool = ippoolpool;
	}

	public String getTo() {
		return this.to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubnet() {
		return this.subnet;
	}

	public void setSubnet(String subnet) {
		this.subnet = subnet;
	}

	public String getPrimDns() {
		return this.primDns;
	}

	public void setPrimDns(String primDns) {
		this.primDns = primDns;
	}

	public String getFrom() {
		return this.from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getDefGw() {
		return this.defGw;
	}

	public void setDefGw(String defGw) {
		this.defGw = defGw;
	}

}
