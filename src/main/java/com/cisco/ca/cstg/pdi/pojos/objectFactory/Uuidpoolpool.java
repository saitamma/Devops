package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="uuidpoolPool")
public class Uuidpoolpool implements java.io.Serializable {

	private static final long serialVersionUID = 6886038883104010097L;
	
	private int primaryKey;
	
	private Orgorg orgorg;
	
	@XmlAttribute
	private String policyOwner;
	
	@XmlAttribute
	private String descr;
	
	@XmlAttribute
	private String prefix;
	
	@XmlAttribute
	private String name;
	
	@XmlAttribute
	private String assignmentOrder;
	
	@XmlAttribute
	private String text;
	
	@XmlElement
	private List<Uuidpoolblock> uuidpoolBlock = new ArrayList<Uuidpoolblock>();

	public Uuidpoolpool() {
	}

	public Uuidpoolpool(int primaryKey, Orgorg orgorg) {
		this.primaryKey = primaryKey;
		this.orgorg = orgorg;
	}
	
	public Uuidpoolpool(String policyOwner, String descr, String prefix,
			String name, String assignmentOrder, String text,
			List<Uuidpoolblock> uuidpoolBlock) {
		super();
		this.policyOwner = policyOwner;
		this.descr = descr;
		this.prefix = prefix;
		this.name = name;
		this.assignmentOrder = assignmentOrder;
		this.text = text;
		this.uuidpoolBlock = uuidpoolBlock;
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

	public String getPrefix() {
		return this.prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
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

	public List<Uuidpoolblock> getUuidpoolBlock() {
		return uuidpoolBlock;
	}

	public void setUuidpoolBlock(List<Uuidpoolblock> uuidpoolBlock) {
		this.uuidpoolBlock = uuidpoolBlock;
	}
}
