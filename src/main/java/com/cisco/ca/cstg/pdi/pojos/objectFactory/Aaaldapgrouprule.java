package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="aaaLdapGroupRule")
public class Aaaldapgrouprule implements java.io.Serializable {

	private static final long serialVersionUID = -9158155888907028829L;
	private int primaryKey;
	private Aaaldapep aaaldapep;
	private Aaaldapprovider aaaldapprovider;
	@XmlAttribute
	private String authorization;
	@XmlAttribute
	private String descr;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String targetAttr;
	@XmlAttribute
	private String traversal;
	@XmlAttribute
	private String usePrimaryGroup;

	public Aaaldapgrouprule() {
	}

	public Aaaldapgrouprule(Aaaldapep aaaldapep, Aaaldapprovider aaaldapprovider) {
		this.aaaldapep = aaaldapep;
		this.aaaldapprovider = aaaldapprovider;
	}

	public Aaaldapgrouprule(int primaryKey,Aaaldapep aaaldapep,
			Aaaldapprovider aaaldapprovider, String authorization,
			String descr, String name, String targetAttr, String traversal,
			String usePrimaryGroup) {
		this.primaryKey = primaryKey;
		this.aaaldapep = aaaldapep;
		this.aaaldapprovider = aaaldapprovider;
		this.authorization = authorization;
		this.descr = descr;
		this.name = name;
		this.targetAttr = targetAttr;
		this.traversal = traversal;
		this.usePrimaryGroup = usePrimaryGroup;
	}


	public int getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Aaaldapep getAaaldapep() {
		return this.aaaldapep;
	}

	public void setAaaldapep(Aaaldapep aaaldapep) {
		this.aaaldapep = aaaldapep;
	}

	public Aaaldapprovider getAaaldapprovider() {
		return this.aaaldapprovider;
	}

	public void setAaaldapprovider(Aaaldapprovider aaaldapprovider) {
		this.aaaldapprovider = aaaldapprovider;
	}

	public String getAuthorization() {
		return authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
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

	public String getTargetAttr() {
		return this.targetAttr;
	}

	public void setTargetAttr(String targetAttr) {
		this.targetAttr = targetAttr;
	}

	public String getTraversal() {
		return this.traversal;
	}

	public void setTraversal(String traversal) {
		this.traversal = traversal;
	}

	public String getUsePrimaryGroup() {
		return this.usePrimaryGroup;
	}

	public void setUsePrimaryGroup(String usePrimaryGroup) {
		this.usePrimaryGroup = usePrimaryGroup;
	}

}
