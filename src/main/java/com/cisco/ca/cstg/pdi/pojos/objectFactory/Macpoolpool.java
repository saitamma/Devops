package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="macpoolPool")
public class Macpoolpool implements java.io.Serializable {

	private static final long serialVersionUID = -341381045925019709L;

	private int primaryKey;
	
	private Orgorg orgOrg;
	
	@XmlAttribute
	private String policyOwner;
	
	@XmlAttribute
	private String descr;
	
	@XmlAttribute
	private String name;
	
	@XmlAttribute
	private String assignmentOrder;
	
	@XmlAttribute
	private String text;
	
	@XmlElement
	private List<Macpoolblock> macpoolBlock = new ArrayList<Macpoolblock>();

	public Macpoolpool() {
	}

	public Macpoolpool(int primaryKey, Orgorg orgorg) {
		this.primaryKey = primaryKey;
		this.orgOrg = orgorg;
	}

	public Macpoolpool(String policyOwner, String descr, String name,
			String assignmentOrder, String text, List<Macpoolblock> macpoolBlock) {
		super();
		this.policyOwner = policyOwner;
		this.descr = descr;
		this.name = name;
		this.assignmentOrder = assignmentOrder;
		this.text = text;
		this.macpoolBlock = macpoolBlock;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Orgorg getOrgorg() {
		return this.orgOrg;
	}

	public void setOrgorg(Orgorg orgorg) {
		this.orgOrg = orgorg;
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

	public String getAssignmentOrder() {
		return this.assignmentOrder;
	}

	public void setAssignmentOrder(String assignmentOrder) {
		this.assignmentOrder = assignmentOrder;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Macpoolblock> getMacpoolBlock() {
		return macpoolBlock;
	}

	public void setMacpoolBlock(List<Macpoolblock> macpoolBlock) {
		this.macpoolBlock = macpoolBlock;
	}
}
