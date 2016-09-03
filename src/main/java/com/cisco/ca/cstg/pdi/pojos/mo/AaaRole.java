package com.cisco.ca.cstg.pdi.pojos.mo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="aaaRole")
public class AaaRole implements java.io.Serializable {

	private static final long serialVersionUID = 2008954203432342385L;
	private AdminLdapRole adminLdapRole;
	
	@XmlAttribute
	private String descr;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String[] priv;

	public AaaRole() {
	}

	public AaaRole(AdminLdapRole adminLdapRole, String descr, String name,
			String[] priv) {
		this.adminLdapRole = adminLdapRole;
		this.descr = descr;
		this.name = name;
		this.priv = priv.clone();
	}

	public AdminLdapRole getAdminLdapRole() {
		return adminLdapRole;
	}

	public void setAdminLdapRole(AdminLdapRole adminLdapRole) {
		this.adminLdapRole = adminLdapRole;
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

	public String[] getPriv() {
		return this.priv.clone();
	}

	public void setPriv(String[] strings) {
		this.priv = strings.clone();
	}

}
