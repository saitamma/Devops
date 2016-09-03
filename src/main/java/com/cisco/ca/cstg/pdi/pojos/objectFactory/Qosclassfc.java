package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
// Generated Nov 11, 2014 10:41:23 AM by Hibernate Tools 4.0.0

@XmlType(name = "qosclassFc")
@XmlAccessorType(XmlAccessType.FIELD)
public class Qosclassfc implements java.io.Serializable {

	private static final long serialVersionUID = 8566860431116566061L;
	private int primaryKey;
	private Qosclassdefinition qosclassdefinition;
	
	@XmlAttribute(name= "name")
	private String name;
	
	@XmlAttribute(name= "weight")
	private String weight;
	
	@XmlAttribute(name= "cos")
	private String cos;

	public Qosclassfc() {
	}

	public Qosclassfc(int primaryKey, Qosclassdefinition qosclassdefinition) {
		this.primaryKey = primaryKey;
		this.qosclassdefinition = qosclassdefinition;
	}

	public Qosclassfc(int primaryKey, Qosclassdefinition qosclassdefinition,
			String name, String weight, String cos) {
		this.primaryKey = primaryKey;
		this.qosclassdefinition = qosclassdefinition;
		this.name = name;
		this.weight = weight;
		this.cos = cos;
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

	public String getWeight() {
		return this.weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getCos() {
		return this.cos;
	}

	public void setCos(String cos) {
		this.cos = cos;
	}

}
