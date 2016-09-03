package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
// Generated Nov 11, 2014 10:41:23 AM by Hibernate Tools 4.0.0

@XmlType(name = "qosclassEthBE")
@XmlAccessorType(XmlAccessType.FIELD)
public class Qosclassethbe implements java.io.Serializable {

	private static final long serialVersionUID = 1774187969357375683L;
	private int primaryKey;
	private Qosclassdefinition qosclassdefinition;
	
	@XmlAttribute(name = "name")
	private String name;
	
	@XmlAttribute(name = "multicastOptimize")
	private String multicastOptimize;
	
	@XmlAttribute(name = "weight")
	private String weight;
	
	@XmlAttribute(name = "mtu")
	private String mtu;

	public Qosclassethbe() {
	}

	public Qosclassethbe(int primaryKey, Qosclassdefinition qosclassdefinition) {
		this.primaryKey = primaryKey;
		this.qosclassdefinition = qosclassdefinition;
	}

	public Qosclassethbe(int primaryKey, Qosclassdefinition qosclassdefinition,
			String name, String multicastOptimize, String weight, String mtu) {
		this.primaryKey = primaryKey;
		this.qosclassdefinition = qosclassdefinition;
		this.name = name;
		this.multicastOptimize = multicastOptimize;
		this.weight = weight;
		this.mtu = mtu;
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

	public String getMtu() {
		return this.mtu;
	}

	public void setMtu(String mtu) {
		this.mtu = mtu;
	}

}
