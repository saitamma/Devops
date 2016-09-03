package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
// Generated Nov 11, 2014 10:41:23 AM by Hibernate Tools 4.0.0

@XmlType(name = "qosclassEthClassified")
@XmlAccessorType(XmlAccessType.FIELD)
public class Qosclassethclassified implements java.io.Serializable {

	private static final long serialVersionUID = -4690338907682300701L;
	private int primaryKey;
	private Qosclassdefinition qosclassdefinition;
	
	@XmlAttribute(name="name")
	private String name;
	
	@XmlAttribute(name="multicastOptimize")
	private String multicastOptimize;
	
	@XmlAttribute(name="weight")
	private String weight;
	
	@XmlAttribute(name="priority")
	private String priority;
	
	@XmlAttribute(name="mtu")
	private String mtu;
	
	@XmlAttribute(name="drop")
	private String drop;
	
	@XmlAttribute(name="cos")
	private String cos;
	
	@XmlAttribute(name="adminState")
	private String adminState;

	public Qosclassethclassified() {
	}

	public Qosclassethclassified(int primaryKey,
			Qosclassdefinition qosclassdefinition) {
		this.primaryKey = primaryKey;
		this.qosclassdefinition = qosclassdefinition;
	}

	public Qosclassethclassified(int primaryKey,
			Qosclassdefinition qosclassdefinition, String name,
			String multicastOptimize, String weight, String priority,
			String mtu, String drop, String cos, String adminState) {
		this.primaryKey = primaryKey;
		this.qosclassdefinition = qosclassdefinition;
		this.name = name;
		this.multicastOptimize = multicastOptimize;
		this.weight = weight;
		this.priority = priority;
		this.mtu = mtu;
		this.drop = drop;
		this.cos = cos;
		this.adminState = adminState;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Qosclassdefinition getQosclassdefinition() {
		return this.qosclassdefinition;
	}

	public void setQosclassdefinition(Qosclassdefinition qosclassdefinition) {
		this.qosclassdefinition = qosclassdefinition;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMulticastOptimize() {
		return this.multicastOptimize;
	}

	public void setMulticastOptimize(String multicastOptimize) {
		this.multicastOptimize = multicastOptimize;
	}

	public String getWeight() {
		return this.weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getPriority() {
		return this.priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getMtu() {
		return this.mtu;
	}

	public void setMtu(String mtu) {
		this.mtu = mtu;
	}

	public String getDrop() {
		return this.drop;
	}

	public void setDrop(String drop) {
		this.drop = drop;
	}

	public String getCos() {
		return this.cos;
	}

	public void setCos(String cos) {
		this.cos = cos;
	}

	public String getAdminState() {
		return this.adminState;
	}

	public void setAdminState(String adminState) {
		this.adminState = adminState;
	}

}
