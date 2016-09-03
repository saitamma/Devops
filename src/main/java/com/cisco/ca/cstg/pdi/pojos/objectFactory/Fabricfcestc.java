package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

// Generated Nov 11, 2014 10:41:23 AM by Hibernate Tools 4.0.0

/**
 * Fabricfcestc generated by hbm2java
 */

@XmlRootElement(name = "fabricFcEstc")
@XmlAccessorType(XmlAccessType.FIELD)
public class Fabricfcestc implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8837655974403558827L;
	private int primaryKey;
	private Fabricfcestccloud fabricfcestccloud;
	
	@XmlAttribute(name = "name")
	private String name;
	
	@XmlAttribute(name = "id")
	private String id;

	public Fabricfcestc() {
	}

	public Fabricfcestc(int primaryKey, Fabricfcestccloud fabricfcestccloud) {
		this.primaryKey = primaryKey;
		this.fabricfcestccloud = fabricfcestccloud;
	}

	public Fabricfcestc(int primaryKey, Fabricfcestccloud fabricfcestccloud,
			String name, String id) {
		this.primaryKey = primaryKey;
		this.fabricfcestccloud = fabricfcestccloud;
		this.name = name;
		this.id = id;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Fabricfcestccloud getFabricfcestccloud() {
		return this.fabricfcestccloud;
	}

	public void setFabricfcestccloud(Fabricfcestccloud fabricfcestccloud) {
		this.fabricfcestccloud = fabricfcestccloud;
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
