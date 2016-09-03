package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lsbootPolicy")
public class Lsbootbootsecurity implements java.io.Serializable {

	private static final long serialVersionUID = -4678408173166545661L;
	private int primaryKey;
	private Lsbootpolicy lsbootpolicy;
	
	@XmlAttribute
	private String rn;
	
	@XmlAttribute
	private String childAction;
	
	@XmlAttribute
	private String secureBoot;

	public Lsbootbootsecurity() {
	}

	public Lsbootbootsecurity(int primaryKey, Lsbootpolicy lsbootpolicy) {
		this.primaryKey = primaryKey;
		this.lsbootpolicy = lsbootpolicy;
	}

	public Lsbootbootsecurity(int primaryKey, Lsbootpolicy lsbootpolicy,
			String rn, String childAction, String secureBoot) {
		this.primaryKey = primaryKey;
		this.lsbootpolicy = lsbootpolicy;
		this.rn = rn;
		this.childAction = childAction;
		this.secureBoot = secureBoot;
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

	public String getRn() {
		return this.rn;
	}

	public void setRn(String rn) {
		this.rn = rn;
	}

	public String getChildAction() {
		return this.childAction;
	}

	public void setChildAction(String childAction) {
		this.childAction = childAction;
	}

	public String getSecureBoot() {
		return this.secureBoot;
	}

	public void setSecureBoot(String secureBoot) {
		this.secureBoot = secureBoot;
	}

}
