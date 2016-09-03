package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="aaaLdapGroup")
public class AaaLdapGroup implements java.io.Serializable {

	private static final long serialVersionUID = 1685459495518200077L;
	private Integer primaryKey;
	private Aaaldapep aaaldapep;
	@XmlAttribute
	private String descr;
	@XmlAttribute
	private String name;
	
	@XmlElement(name="aaaUserLocale")
	private List<AaaUserLocale> aaaUserLocales = new ArrayList<AaaUserLocale>();
	@XmlElement(name="aaaUserRole")
	private List<AaaUserRole> aaaUserRoles = new ArrayList<AaaUserRole>();

	public AaaLdapGroup() {
	}

	public AaaLdapGroup(Aaaldapep aaaldapep) {
		this.aaaldapep = aaaldapep;
	}
	
	public AaaLdapGroup(Aaaldapep aaaldapep,String descr, String name,
			List<AaaUserLocale> aaaUserLocales, List<AaaUserRole> aaaUserRoles) {
		this.aaaldapep = aaaldapep;
		this.descr = descr;
		this.name = name;
		this.aaaUserLocales = aaaUserLocales;
		this.aaaUserRoles = aaaUserRoles;
	}

	public Integer getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(Integer primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Aaaldapep getAaaldapep() {
		return aaaldapep;
	}

	public void setAaaldapep(Aaaldapep aaaldapep) {
		this.aaaldapep = aaaldapep;
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

	public List<AaaUserLocale> getAaaUserLocales() {
		return aaaUserLocales;
	}

	public void setAaaUserLocales(List<AaaUserLocale> aaaUserLocales) {
		this.aaaUserLocales = aaaUserLocales;
	}

	public List<AaaUserRole> getAaaUserRoles() {
		return aaaUserRoles;
	}

	public void setAaaUserRoles(List<AaaUserRole> aaaUserRoles) {
		this.aaaUserRoles = aaaUserRoles;
	}

}
