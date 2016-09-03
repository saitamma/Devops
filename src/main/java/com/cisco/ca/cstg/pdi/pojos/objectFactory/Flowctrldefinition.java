package com.cisco.ca.cstg.pdi.pojos.objectFactory;
// Generated Nov 11, 2014 10:41:23 AM by Hibernate Tools 4.0.0

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "flowctrlDefinition")
@XmlAccessorType(XmlAccessType.FIELD)
public class Flowctrldefinition implements java.io.Serializable {

	private static final long serialVersionUID = 5673720029599339047L;
	private int primaryKey;
	private Fabriclancloud fabriclancloud;
	
	@XmlAttribute(name="policyOwner")
	private String policyOwner;
	
	@XmlAttribute(name="descr")
	private String descr;
	
	private List<Flowctrlitem> flowctrlItem  = new ArrayList<Flowctrlitem>();

	public Flowctrldefinition() {
	}

	public Flowctrldefinition(int primaryKey, Fabriclancloud fabriclancloud) {
		this.primaryKey = primaryKey;
		this.fabriclancloud = fabriclancloud;
	}

	public Flowctrldefinition(int primaryKey, Fabriclancloud fabriclancloud,
			String policyOwner, String descr, List<Flowctrlitem> flowctrlItem) {
		this.primaryKey = primaryKey;
		this.fabriclancloud = fabriclancloud;
		this.policyOwner = policyOwner;
		this.descr = descr;
		this.flowctrlItem = flowctrlItem;
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

	public List<Flowctrlitem> getFlowctrlItem() {
		return flowctrlItem;
	}

	public void setFlowctrlItem(List<Flowctrlitem> flowctrlItem) {
		this.flowctrlItem = flowctrlItem;
	}

}
