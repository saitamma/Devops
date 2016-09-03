package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="topSystem")
public class Topsystem implements java.io.Serializable {

	private static final long serialVersionUID = -6210264040807725252L;
	
	private Toproot topRoot;
	
	private int primaryKey;
	
	@XmlAttribute(name="site")
	private String site;
	
	@XmlAttribute(name="owner")
	private String owner;
	
	@XmlAttribute(name="name")
	private String name;
	
	@XmlAttribute(name="descr")
	private String descr;
	
	private List<Commsvcep> commSvcEp = new ArrayList<Commsvcep>();
	private List<Aaaauthrealm> aaaAuthRealm = new ArrayList<Aaaauthrealm>();
	private List<Aaaldapep> aaaLdapEp =  new ArrayList<Aaaldapep>();
	private List<AaaUserEp> aaaUserEp = new ArrayList<AaaUserEp>();
	private List<AaaRadiusEp> aaaRadiusEp = new ArrayList<AaaRadiusEp>();
	private List<AaaTacacsPlusEp> aaaTacacsPlusEp = new ArrayList<AaaTacacsPlusEp>();

	public Topsystem() {
	}

	public Topsystem(Toproot topRoot, int primaryKey, String site,
			String owner, String name, String descr, List<Commsvcep> commSvcEp,
			List<Aaaauthrealm> aaaAuthRealm,List<Aaaldapep> aaaLdapEp,List<AaaUserEp> aaaUserEp,
			List<AaaRadiusEp> aaaRadiusEp, List<AaaTacacsPlusEp> aaaTacacsPlusEp) {
		super();
		this.topRoot = topRoot;
		this.primaryKey = primaryKey;
		this.site = site;
		this.owner = owner;
		this.name = name;
		this.descr = descr;
		this.commSvcEp = commSvcEp;
		this.aaaAuthRealm = aaaAuthRealm;
		this.aaaLdapEp = aaaLdapEp;
		this.aaaUserEp = aaaUserEp;
		this.aaaRadiusEp = aaaRadiusEp;
		this.aaaTacacsPlusEp = aaaTacacsPlusEp;
	}

	public int getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Toproot getTopRoot() {
		return topRoot;
	}

	public void setTopRoot(Toproot topRoot) {
		this.topRoot = topRoot;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public List<Commsvcep> getCommSvcEp() {
		return commSvcEp;
	}

	public void setCommSvcEp(List<Commsvcep> commSvcEp) {
		this.commSvcEp = commSvcEp;
	}

	public List<Aaaauthrealm> getAaaAuthRealm() {
		return aaaAuthRealm;
	}

	public void setAaaAuthRealm(List<Aaaauthrealm> aaaAuthRealm) {
		this.aaaAuthRealm = aaaAuthRealm;
	}

	public List<Aaaldapep> getAaaLdapEp() {
		return aaaLdapEp;
	}

	public void setAaaLdapEp(List<Aaaldapep> aaaLdapEp) {
		this.aaaLdapEp = aaaLdapEp;
	}

	public List<AaaUserEp> getAaaUserEp() {
		return aaaUserEp;
	}

	public void setAaaUserEp(List<AaaUserEp> aaaUserEp) {
		this.aaaUserEp = aaaUserEp;
	}

	public List<AaaRadiusEp> getAaaRadiusEp() {
		return aaaRadiusEp;
	}

	public void setAaaRadiusEp(List<AaaRadiusEp> aaaRadiusEp) {
		this.aaaRadiusEp = aaaRadiusEp;
	}

	public List<AaaTacacsPlusEp> getAaaTacacsPlusEp() {
		return aaaTacacsPlusEp;
	}

	public void setAaaTacacsPlusEp(List<AaaTacacsPlusEp> aaaTacacsPlusEp) {
		this.aaaTacacsPlusEp = aaaTacacsPlusEp;
	}

}
