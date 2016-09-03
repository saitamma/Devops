package com.cisco.ca.cstg.pdi.pojos.mo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="aaaLocale")
public class AaaLocale implements java.io.Serializable {

	private static final long serialVersionUID = 8938334222759418343L;
	private AdminLdapLocale adminLdapLocale;
	
	@XmlAttribute
	private String name;
	@XmlElement(name="aaaOrg")
	private List<AaaOrg> aaaOrgs = new ArrayList<AaaOrg>();

	public AaaLocale() {
	}

	public AaaLocale(AdminLdapLocale adminLdapLocale, String name, List<AaaOrg> aaaOrgs) {
		this.adminLdapLocale = adminLdapLocale;
		this.name = name;
		this.aaaOrgs = aaaOrgs;
	}

	public AdminLdapLocale getAdminLdapLocale() {
		return adminLdapLocale;
	}

	public void setAdminLdapLocale(AdminLdapLocale adminLdapLocale) {
		this.adminLdapLocale = adminLdapLocale;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AaaOrg> getAaaOrgs() {
		return aaaOrgs;
	}

	public void setAaaOrgs(List<AaaOrg> aaaOrgs) {
		this.aaaOrgs = aaaOrgs;
	}
}