package com.cisco.ca.cstg.pdi.pojos.objectFactory;
// Generated Nov 25, 2014 7:10:32 PM by Hibernate Tools 4.0.0

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="aaaLdapEp")
public class Aaaldapep implements java.io.Serializable {

	private static final long serialVersionUID = -8179981808221541008L;
	
	private Topsystem topSystem;
	
	private int primaryKey;
	@XmlAttribute
	private String attribute;
	@XmlAttribute
	private String basedn;
	@XmlAttribute
	private String descr;
	@XmlAttribute
	private String filter;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String policyOwner;
	@XmlAttribute
	private String retries;
	@XmlAttribute
	private String timeout;
	
	@XmlElement(name="aaaProviderGroup")
	private List<Aaaprovidergroup> aaaprovidergroups = new ArrayList<Aaaprovidergroup>();
	@XmlElement(name="aaaLdapProvider")
	private List<Aaaldapprovider> aaaldapproviders = new ArrayList<Aaaldapprovider>();
	@XmlElement(name="aaaLdapGroupRule")
	private List<Aaaldapgrouprule> aaaldapgrouprules = new ArrayList<Aaaldapgrouprule>();
	@XmlElement(name="aaaLdapGroup")
	private List<AaaLdapGroup> aaaLdapGroups = new ArrayList<AaaLdapGroup>();

	public Aaaldapep() {
	}

	public Aaaldapep(Topsystem topSystem,int primaryKey,String attribute, String basedn, String descr,
			String filter, String name, String policyOwner, String retries,
			String timeout, List<Aaaprovidergroup> aaaprovidergroups,
			List<Aaaldapprovider> aaaldapproviders, List<Aaaldapgrouprule> aaaldapgrouprules,List<AaaLdapGroup> aaaLdapGroups) {
		this.topSystem = topSystem;
		this.primaryKey = primaryKey;
		this.attribute = attribute;
		this.basedn = basedn;
		this.descr = descr;
		this.filter = filter;
		this.name = name;
		this.policyOwner = policyOwner;
		this.retries = retries;
		this.timeout = timeout;
		this.aaaprovidergroups = aaaprovidergroups;
		this.aaaldapproviders = aaaldapproviders;
		this.aaaldapgrouprules = aaaldapgrouprules;
		this.aaaLdapGroups = aaaLdapGroups;
	}

	public Topsystem getTopSystem() {
		return topSystem;
	}

	public void setTopSystem(Topsystem topSystem) {
		this.topSystem = topSystem;
	}

	public int getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getAttribute() {
		return this.attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getBasedn() {
		return this.basedn;
	}

	public void setBasedn(String basedn) {
		this.basedn = basedn;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getFilter() {
		return this.filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
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

	public String getRetries() {
		return this.retries;
	}

	public void setRetries(String retries) {
		this.retries = retries;
	}

	public String getTimeout() {
		return this.timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	public List<Aaaprovidergroup> getAaaprovidergroups() {
		return aaaprovidergroups;
	}

	public void setAaaprovidergroups(List<Aaaprovidergroup> aaaprovidergroups) {
		this.aaaprovidergroups = aaaprovidergroups;
	}

	public List<Aaaldapprovider> getAaaldapproviders() {
		return aaaldapproviders;
	}

	public void setAaaldapproviders(List<Aaaldapprovider> aaaldapproviders) {
		this.aaaldapproviders = aaaldapproviders;
	}

	public List<Aaaldapgrouprule> getAaaldapgrouprules() {
		return aaaldapgrouprules;
	}

	public void setAaaldapgrouprules(List<Aaaldapgrouprule> aaaldapgrouprules) {
		this.aaaldapgrouprules = aaaldapgrouprules;
	}

	public List<AaaLdapGroup> getAaaLdapGroups() {
		return aaaLdapGroups;
	}

	public void setAaaLdapGroups(List<AaaLdapGroup> aaaLdapGroups) {
		this.aaaLdapGroups = aaaLdapGroups;
	}

}
