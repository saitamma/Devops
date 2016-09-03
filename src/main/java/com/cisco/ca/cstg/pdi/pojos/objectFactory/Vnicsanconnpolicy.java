package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "vnicSanConnPolicy")
public class Vnicsanconnpolicy implements java.io.Serializable {

	private static final long serialVersionUID = 7219648295089825216L;
	private int primaryKey;
	private Orgorg orgOrg;
	
	@XmlAttribute
	private String policyOwner;
	
	@XmlAttribute
	private String descr;
	
	@XmlAttribute
	private String name;
	
	@XmlElement
	private List<Vnicfc> vnicFc = new ArrayList<Vnicfc>();
	
	@XmlElement
	private List<Vnicfcnode> vnicFcNode = new ArrayList<Vnicfcnode>();

	public Vnicsanconnpolicy() {
	}

	public Vnicsanconnpolicy(int primaryKey, Orgorg orgorg) {
		this.primaryKey = primaryKey;
		this.orgOrg = orgorg;
	}

	public Vnicsanconnpolicy(int primaryKey, Orgorg orgorg, String policyOwner,
			String descr, String name, List<Vnicfc> vnicfcs, List<Vnicfcnode> vnicfcnodes) {
		this.primaryKey = primaryKey;
		this.orgOrg = orgorg;
		this.policyOwner = policyOwner;
		this.descr = descr;
		this.name = name;
		this.vnicFc = vnicfcs;
		this.vnicFcNode = vnicfcnodes;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
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

	public Orgorg getOrgOrg() {
		return orgOrg;
	}

	public void setOrgOrg(Orgorg orgOrg) {
		this.orgOrg = orgOrg;
	}

	public List<Vnicfc> getVnicFc() {
		return vnicFc;
	}

	public void setVnicFc(List<Vnicfc> vnicFc) {
		this.vnicFc = vnicFc;
	}

	public List<Vnicfcnode> getVnicFcNode() {
		return vnicFcNode;
	}

	public void setVnicFcNode(List<Vnicfcnode> vnicFcNode) {
		this.vnicFcNode = vnicFcNode;
	}
}
