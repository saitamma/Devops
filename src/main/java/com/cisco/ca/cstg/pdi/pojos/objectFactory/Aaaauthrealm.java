package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="aaaAuthRealm")
public class Aaaauthrealm implements java.io.Serializable {

	private static final long serialVersionUID = -5094587059371988559L;

	private Topsystem topSystem;
	
	private int primaryKey;
	
	@XmlAttribute(name="policyOwner")
	private String policyOwner;
	
	@XmlAttribute(name="descr")
	private String descr;
	
	@XmlAttribute(name="name")
	private String name;
	
	@XmlAttribute(name="defRolePolicy")
	private String defRolePolicy;
	
	@XmlAttribute(name="defLogin")
	private String defLogin;
	
	@XmlAttribute(name="conLogin")
	private String conLogin;
	
	private List<Aaadomain> aaaDomain = new ArrayList<Aaadomain>();

	public Aaaauthrealm() {
	}



	public Aaaauthrealm(Topsystem topSystem, int primaryKey,
			String policyOwner, String descr, String name,
			String defRolePolicy, String defLogin, String conLogin,
			List<Aaadomain> aaaDomain) {
		super();
		this.topSystem = topSystem;
		this.primaryKey = primaryKey;
		this.policyOwner = policyOwner;
		this.descr = descr;
		this.name = name;
		this.defRolePolicy = defRolePolicy;
		this.defLogin = defLogin;
		this.conLogin = conLogin;
		this.aaaDomain = aaaDomain;
	}



	public int getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Topsystem getTopSystem() {
		return topSystem;
	}

	public void setTopSystem(Topsystem topSystem) {
		this.topSystem = topSystem;
	}

	public String getPolicyOwner() {
		return policyOwner;
	}

	public void setPolicyOwner(String policyOwner) {
		this.policyOwner = policyOwner;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDefRolePolicy() {
		return defRolePolicy;
	}

	public void setDefRolePolicy(String defRolePolicy) {
		this.defRolePolicy = defRolePolicy;
	}

	public String getDefLogin() {
		return defLogin;
	}

	public void setDefLogin(String defLogin) {
		this.defLogin = defLogin;
	}

	public String getConLogin() {
		return conLogin;
	}

	public void setConLogin(String conLogin) {
		this.conLogin = conLogin;
	}

	public List<Aaadomain> getAaaDomain() {
		return aaaDomain;
	}

	public void setAaaDomain(List<Aaadomain> aaaDomain) {
		this.aaaDomain = aaaDomain;
	}

	

}
