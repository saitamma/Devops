package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="aaaDomain")
public class Aaadomain implements java.io.Serializable {

	private static final long serialVersionUID = 2096942612799791679L;
	
	private Aaaauthrealm aaaAuthRealm;
	
	private int primaryKey;
	
	@XmlAttribute(name="descr")
	private String descr;
	
	@XmlAttribute(name="sessionTimeout")
	private String sessionTimeout;
	
	@XmlAttribute(name="refreshPeriod")
	private String refreshPeriod;
	
	@XmlAttribute(name="name")
	private String name;
	
	private List<Aaadomainauth> aaaDomainAuth = new ArrayList<Aaadomainauth>();

	public Aaadomain() {
	}



	public Aaadomain(Aaaauthrealm aaaAuthRealm, int primaryKey, String descr,
			String sessionTimeout, String refreshPeriod, String name,
			List<Aaadomainauth> aaaDomainAuth) {
		super();
		this.aaaAuthRealm = aaaAuthRealm;
		this.primaryKey = primaryKey;
		this.descr = descr;
		this.sessionTimeout = sessionTimeout;
		this.refreshPeriod = refreshPeriod;
		this.name = name;
		this.aaaDomainAuth = aaaDomainAuth;
	}



	public int getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Aaaauthrealm getAaaAuthRealm() {
		return aaaAuthRealm;
	}

	public void setAaaAuthRealm(Aaaauthrealm aaaAuthRealm) {
		this.aaaAuthRealm = aaaAuthRealm;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getSessionTimeout() {
		return sessionTimeout;
	}

	public void setSessionTimeout(String sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}

	public String getRefreshPeriod() {
		return refreshPeriod;
	}

	public void setRefreshPeriod(String refreshPeriod) {
		this.refreshPeriod = refreshPeriod;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Aaadomainauth> getAaaDomainAuth() {
		return aaaDomainAuth;
	}

	public void setAaaDomainAuth(List<Aaadomainauth> aaaDomainAuth) {
		this.aaaDomainAuth = aaaDomainAuth;
	}

	

}
