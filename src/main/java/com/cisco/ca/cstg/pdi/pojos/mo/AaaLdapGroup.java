package com.cisco.ca.cstg.pdi.pojos.mo;

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
	
	private AdminLdapGroupMap adminLdapGroupMap;
	@XmlAttribute
	private String name;
	
	@XmlElement(name="aaaUserLocale")
	private List<AaaUserLocale> aaaUserLocales = new ArrayList<AaaUserLocale>();
	@XmlElement(name="aaaUserRole")
	private List<AaaUserRole> aaaUserRoles = new ArrayList<AaaUserRole>();

	public AaaLdapGroup() {
	}

	public AaaLdapGroup(AdminLdapGroupMap adminLdapGroupMap, String name,
			List<AaaUserLocale> aaaUserLocales, List<AaaUserRole> aaaUserRoles) {
		this.adminLdapGroupMap = adminLdapGroupMap;
		this.name = name;
		this.aaaUserLocales = aaaUserLocales;
		this.aaaUserRoles = aaaUserRoles;
	}

	
	public AdminLdapGroupMap getAdminLdapGroupMap() {
		return adminLdapGroupMap;
	}

	public void setAdminLdapGroupMap(AdminLdapGroupMap adminLdapGroupMap) {
		this.adminLdapGroupMap = adminLdapGroupMap;
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
