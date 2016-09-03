package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dpsecMac")
public class Dpsecmac implements java.io.Serializable {

	private static final long serialVersionUID = -5578538019698330201L;
	private int primaryKey;

	private Nwctrldefinition nwctrlDefinition;
	
	@XmlAttribute
	private String name;
	
	@XmlAttribute
	private String policyOwner;
	
	@XmlAttribute
	private String descr;
	
	@XmlAttribute
	private String forge;

	public Dpsecmac() {
	}

	public Dpsecmac(int primaryKey, Nwctrldefinition nwctrldefinition) {
		this.primaryKey = primaryKey;
		this.nwctrlDefinition = nwctrldefinition;
	}

	public Dpsecmac(int primaryKey, Nwctrldefinition nwctrldefinition,
			String name, String policyOwner, String descr, String forge) {
		this.primaryKey = primaryKey;
		this.nwctrlDefinition = nwctrldefinition;
		this.name = name;
		this.policyOwner = policyOwner;
		this.descr = descr;
		this.forge = forge;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Nwctrldefinition getNwctrldefinition() {
		return this.nwctrlDefinition;
	}

	public void setNwctrldefinition(Nwctrldefinition nwctrldefinition) {
		this.nwctrlDefinition = nwctrldefinition;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPolicyOwner() {
		return this.policyOwner;
	}

	public void setPolicyOwner(String policyOwner) {
		this.policyOwner = policyOwner;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getForge() {
		return this.forge;
	}

	public void setForge(String forge) {
		this.forge = forge;
	}

}
