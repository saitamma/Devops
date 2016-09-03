package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="aaaProviderRef")
public class Aaaproviderref implements java.io.Serializable {

	private static final long serialVersionUID = 5260853669105807725L;
	private int primaryKey;
	private Aaaprovidergroup aaaprovidergroup;
	@XmlAttribute
	private String descr;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String order;

	public Aaaproviderref() {
	}

	public Aaaproviderref(Aaaprovidergroup aaaprovidergroup) {
		this.aaaprovidergroup = aaaprovidergroup;
	}

	public Aaaproviderref(int primaryKey,Aaaprovidergroup aaaprovidergroup, String descr,
			String name, String order) {
		this.primaryKey = primaryKey;
		this.aaaprovidergroup = aaaprovidergroup;
		this.descr = descr;
		this.name = name;
		this.order = order;
	}


	public int getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Aaaprovidergroup getAaaprovidergroup() {
		return this.aaaprovidergroup;
	}

	public void setAaaprovidergroup(Aaaprovidergroup aaaprovidergroup) {
		this.aaaprovidergroup = aaaprovidergroup;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}



}
