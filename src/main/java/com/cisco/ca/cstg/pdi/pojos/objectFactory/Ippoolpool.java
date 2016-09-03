package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ippoolPool")
public class Ippoolpool implements java.io.Serializable {

	private static final long serialVersionUID = 8130461634403714911L;
	private int primaryKey;
	private Orgorg orgorg;
	
	@XmlAttribute
	private String policyOwner;
	
	@XmlAttribute
	private String descr;
	
	@XmlAttribute
	private String supportsDhcp;
	
	@XmlAttribute
	private String name;
	
	@XmlAttribute
	private String isNetBiosenabled;
	
	@XmlAttribute
	private String guid;
	
	@XmlAttribute
	private String extManaged;
	
	@XmlAttribute
	private String assignmentOrder;
	
	@XmlElement
	private List<Ippoolblock> ippoolBlock = new ArrayList<Ippoolblock>();

	public Ippoolpool() {
	}

	public Ippoolpool(int primaryKey, Orgorg orgorg) {
		this.primaryKey = primaryKey;
		this.orgorg = orgorg;
	}

	public Ippoolpool(int primaryKey, Orgorg orgorg, String policyOwner,
			String descr, String supportsDhcp, String name,
			String isNetBiosenabled, String guid, String extManaged,
			String assignmentOrder, List<Ippoolblock> ippoolblocks) {
		this.primaryKey = primaryKey;
		this.orgorg = orgorg;
		this.policyOwner = policyOwner;
		this.descr = descr;
		this.supportsDhcp = supportsDhcp;
		this.name = name;
		this.isNetBiosenabled = isNetBiosenabled;
		this.guid = guid;
		this.extManaged = extManaged;
		this.assignmentOrder = assignmentOrder;
		this.ippoolBlock = ippoolblocks;
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

	public String getSupportsDhcp() {
		return this.supportsDhcp;
	}

	public void setSupportsDhcp(String supportsDhcp) {
		this.supportsDhcp = supportsDhcp;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsNetBiosenabled() {
		return this.isNetBiosenabled;
	}

	public void setIsNetBiosenabled(String isNetBiosenabled) {
		this.isNetBiosenabled = isNetBiosenabled;
	}

	public String getGuid() {
		return this.guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getExtManaged() {
		return this.extManaged;
	}

	public void setExtManaged(String extManaged) {
		this.extManaged = extManaged;
	}

	public String getAssignmentOrder() {
		return this.assignmentOrder;
	}

	public void setAssignmentOrder(String assignmentOrder) {
		this.assignmentOrder = assignmentOrder;
	}

	public List<Ippoolblock> getIppoolBlock() {
		return ippoolBlock;
	}

	public void setIppoolBlock(List<Ippoolblock> ippoolBlock) {
		this.ippoolBlock = ippoolBlock;
	}
}
