package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="aaaUserLocale")
public class AaaUserLocale implements java.io.Serializable {

	private static final long serialVersionUID = 1901589668605881146L;
	private Integer primaryKey;
	private AaaLdapGroup aaaLdapGroup;
	@XmlAttribute
	private String descr;
	@XmlAttribute
	private String name;

	public AaaUserLocale() {
	}

	public AaaUserLocale(AaaLdapGroup aaaLdapGroup) {
		this.aaaLdapGroup = aaaLdapGroup;
	}

	public AaaUserLocale(AaaLdapGroup aaaLdapGroup, String descr, String name) {
		this.aaaLdapGroup = aaaLdapGroup;
		this.descr = descr;
		this.name = name;
	}

	public Integer getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(Integer primaryKey) {
		this.primaryKey = primaryKey;
	}

	public AaaLdapGroup getAaaLdapGroup() {
		return this.aaaLdapGroup;
	}

	public void setAaaLdapGroup(AaaLdapGroup aaaLdapGroup) {
		this.aaaLdapGroup = aaaLdapGroup;
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

}
