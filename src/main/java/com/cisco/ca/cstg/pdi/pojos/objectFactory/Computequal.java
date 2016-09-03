package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "computeQual")
public class Computequal implements java.io.Serializable {

	private static final long serialVersionUID = 2014573987160308575L;
	private int primaryKey;
	private Orgorg orgorg;
	
	@XmlAttribute
	private String policyOwner;
	
	@XmlAttribute
	private String descr;
	
	@XmlAttribute
	private String name;
	
	@XmlElement
	private List<Computechassisqual> computeChassisQual = new ArrayList<Computechassisqual>();

	public Computequal() {
	}

	public Computequal(int primaryKey, Orgorg orgorg) {
		this.primaryKey = primaryKey;
		this.orgorg = orgorg;
	}

	public Computequal(int primaryKey, Orgorg orgorg, String policyOwner,
			String descr, String name, List<Computechassisqual> computechassisquals) {
		this.primaryKey = primaryKey;
		this.orgorg = orgorg;
		this.policyOwner = policyOwner;
		this.descr = descr;
		this.name = name;
		this.computeChassisQual = computechassisquals;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Computechassisqual> getComputeChassisQual() {
		return computeChassisQual;
	}

	public void setComputeChassisQual(List<Computechassisqual> computeChassisQual) {
		this.computeChassisQual = computeChassisQual;
	}
}
