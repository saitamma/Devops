package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lsVersionBeh")
public class Lsversionbeh implements java.io.Serializable {

	private static final long serialVersionUID = -2960806903348927679L;
	private int primaryKey;
	private Lsserver lsserver;
	
	@XmlAttribute
	private String vnicOrder;
	
	@XmlAttribute
	private String vnicMap;
	
	@XmlAttribute
	private String vconMap;
	
	@XmlAttribute
	private String pciEnum;

	public Lsversionbeh() {
	}

	public Lsversionbeh(int primaryKey, Lsserver lsserver) {
		this.primaryKey = primaryKey;
		this.lsserver = lsserver;
	}

	public Lsversionbeh(int primaryKey, Lsserver lsserver, String vnicOrder,
			String vnicMap, String vconMap, String pciEnum) {
		this.primaryKey = primaryKey;
		this.lsserver = lsserver;
		this.vnicOrder = vnicOrder;
		this.vnicMap = vnicMap;
		this.vconMap = vconMap;
		this.pciEnum = pciEnum;
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

	public String getVnicOrder() {
		return this.vnicOrder;
	}

	public void setVnicOrder(String vnicOrder) {
		this.vnicOrder = vnicOrder;
	}

	public String getVnicMap() {
		return this.vnicMap;
	}

	public void setVnicMap(String vnicMap) {
		this.vnicMap = vnicMap;
	}

	public String getVconMap() {
		return this.vconMap;
	}

	public void setVconMap(String vconMap) {
		this.vconMap = vconMap;
	}

	public String getPciEnum() {
		return this.pciEnum;
	}

	public void setPciEnum(String pciEnum) {
		this.pciEnum = pciEnum;
	}

}
