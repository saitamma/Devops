package com.cisco.ca.cstg.pdi.pojos.objectFactory;
// Generated Nov 11, 2014 10:41:23 AM by Hibernate Tools 4.0.0

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "qosclassDefinition")
@XmlAccessorType(XmlAccessType.FIELD)
public class Qosclassdefinition implements java.io.Serializable {

	private static final long serialVersionUID = 6903305712888623693L;
	private int primaryKey;
	private Fabriclancloud fabriclancloud;
	
	@XmlAttribute(name="policyOwner")
	private String policyOwner;
	
	@XmlAttribute(name="descr")
	private String descr;
	
	private List<Qosclassethbe> qosclassEthBE = new ArrayList<Qosclassethbe>();
	
	private List<Qosclassethclassified> qosclassEthClassified = new ArrayList<Qosclassethclassified>();
	
	private List<Qosclassfc> qosclassFc = new ArrayList<Qosclassfc>();

	public Qosclassdefinition() {
	}

	public Qosclassdefinition(int primaryKey, Fabriclancloud fabriclancloud) {
		this.primaryKey = primaryKey;
		this.fabriclancloud = fabriclancloud;
	}

	public Qosclassdefinition(int primaryKey, Fabriclancloud fabriclancloud,
			String policyOwner, String descr, List<Qosclassethbe> qosclassEthBE,
			List<Qosclassethclassified> qosclassEthClassified, List<Qosclassfc> qosclassFc) {
		this.primaryKey = primaryKey;
		this.fabriclancloud = fabriclancloud;
		this.policyOwner = policyOwner;
		this.descr = descr;
		this.qosclassEthBE = qosclassEthBE;
		this.qosclassEthClassified = qosclassEthClassified;
		this.qosclassFc = qosclassFc;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Fabriclancloud getFabriclancloud() {
		return this.fabriclancloud;
	}

	public void setFabriclancloud(Fabriclancloud fabriclancloud) {
		this.fabriclancloud = fabriclancloud;
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

	public List<Qosclassethbe> getQosclassEthBE() {
		return qosclassEthBE;
	}

	public void setQosclassEthBE(List<Qosclassethbe> qosclassEthBE) {
		this.qosclassEthBE = qosclassEthBE;
	}

	public List<Qosclassethclassified> getQosclassEthClassified() {
		return qosclassEthClassified;
	}

	public void setQosclassEthClassified(
			List<Qosclassethclassified> qosclassEthClassified) {
		this.qosclassEthClassified = qosclassEthClassified;
	}

	public List<Qosclassfc> getQosclassFc() {
		return qosclassFc;
	}

	public void setQosclassFc(List<Qosclassfc> qosclassFc) {
		this.qosclassFc = qosclassFc;
	}

}
