package com.cisco.ca.cstg.pdi.pojos.mo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="aaaUserLocale")
public class AaaUserLocale implements java.io.Serializable {

	private static final long serialVersionUID = 1901589668605881146L;
	
	private AaaLdapGroup aaaLdapGroup;
	
	@XmlAttribute
	private String name;

	public AaaUserLocale() {
	}

	public AaaUserLocale(AaaLdapGroup aaaLdapGroup, String name) {
		this.aaaLdapGroup = aaaLdapGroup;
		this.name = name;
	}

	public AaaLdapGroup getAaaLdapGroup() {
		return this.aaaLdapGroup;
	}

	public void setAaaLdapGroup(AaaLdapGroup aaaLdapGroup) {
		this.aaaLdapGroup = aaaLdapGroup;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
