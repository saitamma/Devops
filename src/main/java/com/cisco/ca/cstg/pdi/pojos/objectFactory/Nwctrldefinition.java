package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "nwctrlDefinition")
public class Nwctrldefinition implements java.io.Serializable {

	private static final long serialVersionUID = -1377762594594753687L;
	private int primaryKey;
	private Orgorg orgOrg;
	private Fabricethestccloud fabricEthEstcCloud;
	
	@XmlAttribute
	private String policyOwner;
	
	@XmlAttribute
	private String descr;
	
	@XmlAttribute
	private String uplinkFailAction;
	
	@XmlAttribute
	private String name;
	
	@XmlAttribute
	private String macRegisterMode;
	
	@XmlAttribute
	private String cdp;
	
	@XmlElement
	private List<Dpsecmac> dpsecMac = new ArrayList<Dpsecmac>();

	public Nwctrldefinition() {
	}

	public Nwctrldefinition(int primaryKey, Orgorg orgorg,
			Fabricethestccloud fabricethestccloud) {
		this.primaryKey = primaryKey;
		this.orgOrg = orgorg;
		this.fabricEthEstcCloud = fabricethestccloud;
	}

	public Nwctrldefinition(int primaryKey, Orgorg orgorg,
			Fabricethestccloud fabricethestccloud, String policyOwner,
			String descr, String uplinkFailAction, String name,
			String macRegisterMode, String cdp, List<Dpsecmac> dpsecmacs) {
		this.primaryKey = primaryKey;
		this.orgOrg = orgorg;
		this.fabricEthEstcCloud = fabricethestccloud;
		this.policyOwner = policyOwner;
		this.descr = descr;
		this.uplinkFailAction = uplinkFailAction;
		this.name = name;
		this.macRegisterMode = macRegisterMode;
		this.cdp = cdp;
		this.dpsecMac = dpsecmacs;
	}

	public int getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Orgorg getOrgOrg() {
		return orgOrg;
	}

	public void setOrgOrg(Orgorg orgOrg) {
		this.orgOrg = orgOrg;
	}

	public Fabricethestccloud getFabricEthEstcCloud() {
		return fabricEthEstcCloud;
	}

	public void setFabricEthEstcCloud(Fabricethestccloud fabricEthEstcCloud) {
		this.fabricEthEstcCloud = fabricEthEstcCloud;
	}

	public String getPolicyOwner() {
		return policyOwner;
	}

	public void setPolicyOwner(String policyOwner) {
		this.policyOwner = policyOwner;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getUplinkFailAction() {
		return uplinkFailAction;
	}

	public void setUplinkFailAction(String uplinkFailAction) {
		this.uplinkFailAction = uplinkFailAction;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMacRegisterMode() {
		return macRegisterMode;
	}

	public void setMacRegisterMode(String macRegisterMode) {
		this.macRegisterMode = macRegisterMode;
	}

	public String getCdp() {
		return cdp;
	}

	public void setCdp(String cdp) {
		this.cdp = cdp;
	}

	public List<Dpsecmac> getDpsecMac() {
		return dpsecMac;
	}

	public void setDpsecMac(List<Dpsecmac> dpsecMac) {
		this.dpsecMac = dpsecMac;
	}
}
