package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "vnicLanConnTempl")
public class Vniclanconntempl implements java.io.Serializable {

	private static final long serialVersionUID = 8946082035820474660L;
	private int primaryKey;
	
	private Orgorg orgOrg;
	
	@XmlAttribute
	private String policyOwner;
	
	@XmlAttribute
	private String descr;
	
	@XmlAttribute
	private String target;
	
	@XmlAttribute
	private String statsPolicyName;
	
	@XmlAttribute
	private String pinToGroupName;
	
	@XmlAttribute
	private String templType;
	
	@XmlAttribute
	private String switchId;
	
	@XmlAttribute
	private String qosPolicyName;
	
	@XmlAttribute
	private String nwCtrlPolicyName;
	
	@XmlAttribute
	private String name;

	@XmlAttribute
	private String mtu;
	
	@XmlAttribute
	private String identPoolName;
	
	@XmlElement
	private List<Vnicetherif> vnicEtherIf = new ArrayList<Vnicetherif>();

	public Vniclanconntempl() {
	}

	public Vniclanconntempl(int primaryKey, Orgorg orgorg) {
		this.primaryKey = primaryKey;
		this.orgOrg = orgorg;
	}

	public Vniclanconntempl(int primaryKey, Orgorg orgorg, String policyOwner,
			String descr, String target, String statsPolicyName,
			String pinToGroupName, String templType, String switchId,
			String qosPolicyName, String nwCtrlPolicyName, String name,
			String mtu, String identPoolName, List<Vnicetherif> vnicetherifs) {
		this.primaryKey = primaryKey;
		this.orgOrg = orgorg;
		this.policyOwner = policyOwner;
		this.descr = descr;
		this.target = target;
		this.statsPolicyName = statsPolicyName;
		this.pinToGroupName = pinToGroupName;
		this.templType = templType;
		this.switchId = switchId;
		this.qosPolicyName = qosPolicyName;
		this.nwCtrlPolicyName = nwCtrlPolicyName;
		this.name = name;
		this.mtu = mtu;
		this.identPoolName = identPoolName;
		this.vnicEtherIf = vnicetherifs;
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

	public String getTarget() {
		return this.target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getStatsPolicyName() {
		return this.statsPolicyName;
	}

	public void setStatsPolicyName(String statsPolicyName) {
		this.statsPolicyName = statsPolicyName;
	}

	public String getPinToGroupName() {
		return this.pinToGroupName;
	}

	public void setPinToGroupName(String pinToGroupName) {
		this.pinToGroupName = pinToGroupName;
	}

	public String getTemplType() {
		return this.templType;
	}

	public void setTemplType(String templType) {
		this.templType = templType;
	}

	public String getSwitchId() {
		return this.switchId;
	}

	public void setSwitchId(String switchId) {
		this.switchId = switchId;
	}

	public String getQosPolicyName() {
		return this.qosPolicyName;
	}

	public void setQosPolicyName(String qosPolicyName) {
		this.qosPolicyName = qosPolicyName;
	}

	public String getNwCtrlPolicyName() {
		return this.nwCtrlPolicyName;
	}

	public void setNwCtrlPolicyName(String nwCtrlPolicyName) {
		this.nwCtrlPolicyName = nwCtrlPolicyName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMtu() {
		return this.mtu;
	}

	public void setMtu(String mtu) {
		this.mtu = mtu;
	}

	public String getIdentPoolName() {
		return this.identPoolName;
	}

	public void setIdentPoolName(String identPoolName) {
		this.identPoolName = identPoolName;
	}

	public Orgorg getOrgOrg() {
		return orgOrg;
	}

	public void setOrgOrg(Orgorg orgOrg) {
		this.orgOrg = orgOrg;
	}

	public List<Vnicetherif> getVnicEtherIf() {
		return vnicEtherIf;
	}

	public void setVnicEtherIf(List<Vnicetherif> vnicEtherIf) {
		this.vnicEtherIf = vnicEtherIf;
	}
}
