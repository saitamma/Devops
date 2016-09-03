package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="aaaOrg")
public class AaaOrg implements java.io.Serializable {

	private static final long serialVersionUID = 2176901093553813070L;
	private Integer primaryKey;
	private AaaLocale aaaLocale;
	@XmlAttribute
	private String descr;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String orgDn;

	public AaaOrg() {
	}

	public AaaOrg(AaaLocale aaaLocale) {
		this.aaaLocale = aaaLocale;
	}

	public AaaOrg(AaaLocale aaaLocale, String descr, String name, String orgDn) {
		this.aaaLocale = aaaLocale;
		this.descr = descr;
		this.name = name;
		this.orgDn = orgDn;
	}

	public Integer getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(Integer primaryKey) {
		this.primaryKey = primaryKey;
	}

	public AaaLocale getAaaLocale() {
		return this.aaaLocale;
	}

	public void setAaaLocale(AaaLocale aaaLocale) {
		this.aaaLocale = aaaLocale;
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

	public String getOrgDn() {
		return this.orgDn;
	}

	public void setOrgDn(String orgDn) {
		this.orgDn = orgDn;
	}

}
