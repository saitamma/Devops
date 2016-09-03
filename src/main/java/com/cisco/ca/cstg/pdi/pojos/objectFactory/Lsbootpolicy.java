package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "lsbootPolicy")
public class Lsbootpolicy implements java.io.Serializable {

	private static final long serialVersionUID = 1535837522983250534L;
	private int primaryKey;
	private Orgorg orgorg;
	
	@XmlAttribute
	private String policyOwner;
	
	@XmlAttribute
	private String descr;
	
	@XmlAttribute
	private String rebootOnUpdate;
	
	@XmlAttribute
	private String purpose;
	
	@XmlAttribute
	private String enforceVnicName;
	
	@XmlAttribute
	private String bootMode;
	
	@XmlAttribute
	private String name;
	
	@XmlElement
	private List<Lsbootstorage> lsbootStorage = new ArrayList<Lsbootstorage>(); 
	
	@XmlElement
	private List<Lsbootbootsecurity> lsbootBootSecurity = new ArrayList<Lsbootbootsecurity>(); 
	
	@XmlElement
	private List<Lsbootlan> lsbootLan = new ArrayList<Lsbootlan>(); 
	
	@XmlElement
	private List<Lsbootsan> lsbootSan = new ArrayList<Lsbootsan>();

	public Lsbootpolicy() {
	}

	public Lsbootpolicy(int primaryKey, Orgorg orgorg) {
		this.primaryKey = primaryKey;
		this.orgorg = orgorg;
	}

	public Lsbootpolicy(int primaryKey, Orgorg orgorg, String policyOwner,
			String descr, String rebootOnUpdate, String purpose,
			String enforceVnicName, String bootMode, String name,
			List<Lsbootstorage> lsbootStorage,
			List<Lsbootbootsecurity> lsbootBootSecurity,
			List<Lsbootlan> lsbootLan, List<Lsbootsan> lsbootSan) {
		super();
		this.primaryKey = primaryKey;
		this.orgorg = orgorg;
		this.policyOwner = policyOwner;
		this.descr = descr;
		this.rebootOnUpdate = rebootOnUpdate;
		this.purpose = purpose;
		this.enforceVnicName = enforceVnicName;
		this.bootMode = bootMode;
		this.name = name;
		this.lsbootStorage = lsbootStorage;
		this.lsbootBootSecurity = lsbootBootSecurity;
		this.lsbootLan = lsbootLan;
		this.lsbootSan = lsbootSan;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Orgorg getOrgorg() {
		return this.orgorg;
	}

	public void setOrgorg(Orgorg orgorg) {
		this.orgorg = orgorg;
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

	public String getRebootOnUpdate() {
		return this.rebootOnUpdate;
	}

	public void setRebootOnUpdate(String rebootOnUpdate) {
		this.rebootOnUpdate = rebootOnUpdate;
	}

	public String getPurpose() {
		return this.purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getEnforceVnicName() {
		return this.enforceVnicName;
	}

	public void setEnforceVnicName(String enforceVnicName) {
		this.enforceVnicName = enforceVnicName;
	}

	public String getBootMode() {
		return this.bootMode;
	}

	public void setBootMode(String bootMode) {
		this.bootMode = bootMode;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Lsbootstorage> getLsbootStorage() {
		return lsbootStorage;
	}

	public void setLsbootStorage(List<Lsbootstorage> lsbootStorage) {
		this.lsbootStorage = lsbootStorage;
	}

	public List<Lsbootbootsecurity> getLsbootBootSecurity() {
		return lsbootBootSecurity;
	}

	public void setLsbootBootSecurity(List<Lsbootbootsecurity> lsbootBootSecurity) {
		this.lsbootBootSecurity = lsbootBootSecurity;
	}

	public List<Lsbootlan> getLsbootLan() {
		return lsbootLan;
	}

	public void setLsbootLan(List<Lsbootlan> lsbootLan) {
		this.lsbootLan = lsbootLan;
	}

	public List<Lsbootsan> getLsbootSan() {
		return lsbootSan;
	}

	public void setLsbootSan(List<Lsbootsan> lsbootSan) {
		this.lsbootSan = lsbootSan;
	}

}
