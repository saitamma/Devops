package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
// Generated Nov 11, 2014 10:41:23 AM by Hibernate Tools 4.0.0

@XmlType(name = "fabricEthEstc")
@XmlAccessorType(XmlAccessType.FIELD)
public class Fabricethestc implements java.io.Serializable {

	private static final long serialVersionUID = 5294324069228646318L;
	private int primaryKey;
	private Fabricethestccloud fabricethestccloud;
	
	@XmlAttribute(name="name")
	private String name;
	
	@XmlAttribute(name="id")
	private String id;

	public Fabricethestc() {
	}

	public Fabricethestc(int primaryKey, Fabricethestccloud fabricethestccloud) {
		this.primaryKey = primaryKey;
		this.fabricethestccloud = fabricethestccloud;
	}

	public Fabricethestc(int primaryKey, Fabricethestccloud fabricethestccloud,
			String name, String id) {
		this.primaryKey = primaryKey;
		this.fabricethestccloud = fabricethestccloud;
		this.name = name;
		this.id = id;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Fabricethestccloud getFabricethestccloud() {
		return this.fabricethestccloud;
	}

	public void setFabricethestccloud(Fabricethestccloud fabricethestccloud) {
		this.fabricethestccloud = fabricethestccloud;
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

}
