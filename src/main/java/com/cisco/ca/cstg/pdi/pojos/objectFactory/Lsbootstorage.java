package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "lsbootStorage")
public class Lsbootstorage implements java.io.Serializable {

	private static final long serialVersionUID = 3328124731080107113L;
	private int primaryKey;
	private Lsbootpolicy lsbootpolicy;
	
	@XmlAttribute
	private String order;
	
	@XmlElement
	private List<Lsbootlocalstorage> lsbootLocalStorage = new ArrayList<Lsbootlocalstorage>();

	public Lsbootstorage() {
	}

	public Lsbootstorage(int primaryKey, Lsbootpolicy lsbootpolicy) {
		this.primaryKey = primaryKey;
		this.lsbootpolicy = lsbootpolicy;
	}

	public Lsbootstorage(int primaryKey, Lsbootpolicy lsbootpolicy,
			String order, List<Lsbootlocalstorage> lsbootlocalstorages) {
		this.primaryKey = primaryKey;
		this.lsbootpolicy = lsbootpolicy;
		this.order = order;
		this.lsbootLocalStorage = lsbootlocalstorages;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Lsbootpolicy getLsbootpolicy() {
		return this.lsbootpolicy;
	}

	public void setLsbootpolicy(Lsbootpolicy lsbootpolicy) {
		this.lsbootpolicy = lsbootpolicy;
	}

	public String getOrder() {
		return this.order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public List<Lsbootlocalstorage> getLsbootLocalStorage() {
		return lsbootLocalStorage;
	}

	public void setLsbootLocalStorage(List<Lsbootlocalstorage> lsbootLocalStorage) {
		this.lsbootLocalStorage = lsbootLocalStorage;
	}
}
