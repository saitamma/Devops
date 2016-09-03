package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lsbootDefaultLocalImage")
public class LsbootDefaultLocalImage implements java.io.Serializable  {
	
	private static final long serialVersionUID = -6768905211636388965L;
	private int primaryKey;
	private Lsbootlocalstorage lsbootlocalstorage;
	@XmlAttribute
	private String order;
	
	public LsbootDefaultLocalImage() {
	}
	public LsbootDefaultLocalImage(int primaryKey,
			Lsbootlocalstorage lsbootlocalstorage) {
		this.primaryKey = primaryKey;
		this.lsbootlocalstorage = lsbootlocalstorage;
	}

	public LsbootDefaultLocalImage(int primaryKey,
			Lsbootlocalstorage lsbootlocalstorage, String order) {
		this.primaryKey = primaryKey;
		this.lsbootlocalstorage = lsbootlocalstorage;
		this.order = order;
	}
	public int getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}
	public Lsbootlocalstorage getLsbootlocalstorage() {
		return lsbootlocalstorage;
	}
	public void setLsbootlocalstorage(Lsbootlocalstorage lsbootlocalstorage) {
		this.lsbootlocalstorage = lsbootlocalstorage;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}

}
