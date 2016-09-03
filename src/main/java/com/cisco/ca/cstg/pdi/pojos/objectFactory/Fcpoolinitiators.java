package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "fcpoolInitiators")
public class Fcpoolinitiators implements java.io.Serializable {

	private static final long serialVersionUID = 5366762196678866495L;
	private int primaryKey;
	private Orgorg orgorg;
	
	@XmlAttribute
	private String policyOwner;
	
	@XmlAttribute
	private String descr;
	
	@XmlAttribute
	private String purpose;
	
	@XmlAttribute
	private String name;
	
	@XmlAttribute
	private String maxPortsPerNode;
	
	@XmlAttribute
	private String assignmentOrder;
	
	@XmlAttribute
	private String text;
	
	@XmlElement
	private List<Fcpoolinitiator> fcpoolInitiator  = new ArrayList<Fcpoolinitiator>();
	
	@XmlElement
	private List<Fcpoolblock> fcpoolBlock  = new ArrayList<Fcpoolblock>();

	public Fcpoolinitiators() {
	}

	public Fcpoolinitiators(int primaryKey, Orgorg orgorg) {
		this.primaryKey = primaryKey;
		this.orgorg = orgorg;
	}

	public Fcpoolinitiators(int primaryKey, Orgorg orgorg, String policyOwner,
			String descr, String purpose, String name, String maxPortsPerNode,
			String assignmentOrder, String text, List<Fcpoolinitiator> fcpoolinitiators,
			List<Fcpoolblock> fcpoolblocks) {
		this.primaryKey = primaryKey;
		this.orgorg = orgorg;
		this.policyOwner = policyOwner;
		this.descr = descr;
		this.purpose = purpose;
		this.name = name;
		this.maxPortsPerNode = maxPortsPerNode;
		this.assignmentOrder = assignmentOrder;
		this.text = text;
		this.fcpoolInitiator = fcpoolinitiators;
		this.fcpoolBlock = fcpoolblocks;
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

	public String getPurpose() {
		return this.purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMaxPortsPerNode() {
		return this.maxPortsPerNode;
	}

	public void setMaxPortsPerNode(String maxPortsPerNode) {
		this.maxPortsPerNode = maxPortsPerNode;
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

	public List<Fcpoolinitiator> getFcpoolInitiator() {
		return fcpoolInitiator;
	}

	public void setFcpoolInitiator(List<Fcpoolinitiator> fcpoolInitiator) {
		this.fcpoolInitiator = fcpoolInitiator;
	}

	public List<Fcpoolblock> getFcpoolBlock() {
		return fcpoolBlock;
	}

	public void setFcpoolBlock(List<Fcpoolblock> fcpoolBlock) {
		this.fcpoolBlock = fcpoolBlock;
	}
}
