package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="aaaTacacsPlusEp")
public class AaaTacacsPlusEp implements java.io.Serializable {

	private static final long serialVersionUID = -8410284783531196678L;
	private Integer primaryKey;
	private Topsystem topSystem;
	@XmlAttribute
	private String policyOwner;
	@XmlAttribute
	private String descr;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String retries;
	@XmlAttribute
	private String timeout;
	
	@XmlElement(name="aaaTacacsPlusProvider")
	private List<AaaTacacsPlusProvider> aaaTacacsPlusProviders = new ArrayList<AaaTacacsPlusProvider>();
	@XmlElement(name="aaaProviderGroup")
	private List<Aaaprovidergroup> aaaprovidergroups = new ArrayList<Aaaprovidergroup>();

	public AaaTacacsPlusEp() {
	}

	public AaaTacacsPlusEp(Topsystem topSystem) {
		this.topSystem = topSystem;
	}
	
	public AaaTacacsPlusEp(Topsystem topSystem,String policyOwner, String descr, String name,
			String retries, String timeout,	List<AaaTacacsPlusProvider> aaaTacacsPlusProviders,List<Aaaprovidergroup> aaaprovidergroups) {
		this.topSystem = topSystem;
		this.policyOwner = policyOwner;
		this.descr = descr;
		this.name = name;
		this.retries = retries;
		this.timeout = timeout;
		this.aaaTacacsPlusProviders = aaaTacacsPlusProviders;
		this.aaaprovidergroups = aaaprovidergroups;
	}

	public Integer getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(Integer primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Topsystem getTopSystem() {
		return topSystem;
	}

	public void setTopSystem(Topsystem topSystem) {
		this.topSystem = topSystem;
	}

	public String getPolicyOwner() {
		return this.policyOwner;
	}

	public void setPolicyOwner(String policyOwner) {
		this.policyOwner = policyOwner;
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

	public List<AaaTacacsPlusProvider> getAaaTacacsPlusProviders() {
		return aaaTacacsPlusProviders;
	}

	public void setAaaTacacsPlusProviders(
			List<AaaTacacsPlusProvider> aaaTacacsPlusProviders) {
		this.aaaTacacsPlusProviders = aaaTacacsPlusProviders;
	}

	public List<Aaaprovidergroup> getAaaprovidergroups() {
		return aaaprovidergroups;
	}

	public void setAaaprovidergroups(List<Aaaprovidergroup> aaaprovidergroups) {
		this.aaaprovidergroups = aaaprovidergroups;
	}


}
