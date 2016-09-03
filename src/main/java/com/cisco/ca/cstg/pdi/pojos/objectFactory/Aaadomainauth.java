package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="aaaDomainAuth")
public class Aaadomainauth implements java.io.Serializable {

	private static final long serialVersionUID = 1512185809725714104L;
	
	private Aaadomain aaaDomain;
	
	private int primaryKey;
	
	@XmlAttribute(name="name")
	private String name;
	
	@XmlAttribute(name="descr")
	private String descr;
	
	@XmlAttribute(name="use2Factor")
	private String use2Factor;
	
	@XmlAttribute(name="providerGroup")
	private String providerGroup;
	
	@XmlAttribute(name="realm")
	private String realm;

	public Aaadomainauth() {
	}

	public Aaadomainauth(Aaadomain aaaDomain, int primaryKey, String name,
			String descr, String use2Factor, String providerGroup, String realm) {
		super();
		this.aaaDomain = aaaDomain;
		this.primaryKey = primaryKey;
		this.name = name;
		this.descr = descr;
		this.use2Factor = use2Factor;
		this.providerGroup = providerGroup;
		this.realm = realm;
	}



	public int getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Aaadomain getAaaDomain() {
		return aaaDomain;
	}

	public void setAaaDomain(Aaadomain aaaDomain) {
		this.aaaDomain = aaaDomain;
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

	public String getUse2Factor() {
		return use2Factor;
	}

	public void setUse2Factor(String use2Factor) {
		this.use2Factor = use2Factor;
	}

	public String getProviderGroup() {
		return providerGroup;
	}

	public void setProviderGroup(String providerGroup) {
		this.providerGroup = providerGroup;
	}

	public String getRealm() {
		return realm;
	}

	public void setRealm(String realm) {
		this.realm = realm;
	}

	

}
