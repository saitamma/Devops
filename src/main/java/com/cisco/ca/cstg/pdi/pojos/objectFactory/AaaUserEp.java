package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="aaaUserEp")
public class AaaUserEp implements java.io.Serializable {

	private static final long serialVersionUID = -1002087339006164027L;
	private Integer primaryKey;
	private Topsystem topSystem;
	@XmlAttribute
	private String policyOwner;
	@XmlAttribute
	private String descr;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String pwdStrengthCheck;
	
	@XmlElement(name="aaaRole")
	private List<AaaRole> aaaRoles = new ArrayList<AaaRole>();
	@XmlElement(name="aaaLocale")
	private List<AaaLocale> aaaLocales = new ArrayList<AaaLocale>();

	public AaaUserEp() {
	}

	public AaaUserEp(Topsystem topSystem) {
		this.topSystem = topSystem;
	}
	
	public AaaUserEp(Topsystem topSystem,String policyOwner, String descr, String name,
			String pwdStrengthCheck,List<AaaRole> aaaRoles,List<AaaLocale> aaaLocales) {
		this.policyOwner = policyOwner;
		this.descr = descr;
		this.name = name;
		this.pwdStrengthCheck = pwdStrengthCheck;
		this.aaaRoles = aaaRoles;
		this.aaaLocales = aaaLocales;
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

	public String getPwdStrengthCheck() {
		return this.pwdStrengthCheck;
	}

	public void setPwdStrengthCheck(String pwdStrengthCheck) {
		this.pwdStrengthCheck = pwdStrengthCheck;
	}

	public List<AaaRole> getAaaRoles() {
		return aaaRoles;
	}

	public void setAaaRoles(List<AaaRole> aaaRoles) {
		this.aaaRoles = aaaRoles;
	}

	public List<AaaLocale> getAaaLocales() {
		return aaaLocales;
	}

	public void setAaaLocales(List<AaaLocale> aaaLocales) {
		this.aaaLocales = aaaLocales;
	}

	
}
