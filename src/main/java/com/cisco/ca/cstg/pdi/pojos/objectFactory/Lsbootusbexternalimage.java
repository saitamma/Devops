package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lsbootUsbExternalImage")
public class Lsbootusbexternalimage implements java.io.Serializable {

	private static final long serialVersionUID = -8360587135820872631L;
	private int primaryKey;
	private Lsbootlocalstorage lsbootlocalstorage;
	
	@XmlAttribute
	private String order;

	public Lsbootusbexternalimage() {
	}

	public Lsbootusbexternalimage(int primaryKey,
			Lsbootlocalstorage lsbootlocalstorage) {
		this.primaryKey = primaryKey;
		this.lsbootlocalstorage = lsbootlocalstorage;
	}

	public Lsbootusbexternalimage(int primaryKey,
			Lsbootlocalstorage lsbootlocalstorage, String order) {
		this.primaryKey = primaryKey;
		this.lsbootlocalstorage = lsbootlocalstorage;
		this.order = order;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Lsbootlocalstorage getLsbootlocalstorage() {
		return this.lsbootlocalstorage;
	}

	public void setLsbootlocalstorage(Lsbootlocalstorage lsbootlocalstorage) {
		this.lsbootlocalstorage = lsbootlocalstorage;
	}

	public String getOrder() {
		return this.order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}
