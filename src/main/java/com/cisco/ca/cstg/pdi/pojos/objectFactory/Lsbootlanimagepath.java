package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "lsbootLanImagePath")
public class Lsbootlanimagepath implements java.io.Serializable {

	private static final long serialVersionUID = 7117189349225538551L;
	private int primaryKey;
	private Lsbootlan lsbootlan;
	
	@XmlAttribute
	private String vnicName;
	
	@XmlAttribute
	private String type;
	
	@XmlAttribute
	private String provSrvPolicyName;
	
	@XmlAttribute
	private String imgSecPolicyName;
	
	@XmlAttribute
	private String imgPolicyName;
	
	@XmlAttribute
	private String iSCSIVnicName;
	
	@XmlAttribute
	private String bootIpPolicyName;

	public Lsbootlanimagepath() {
	}

	public Lsbootlanimagepath(int primaryKey, Lsbootlan lsbootlan) {
		this.primaryKey = primaryKey;
		this.lsbootlan = lsbootlan;
	}

	public Lsbootlanimagepath(int primaryKey, Lsbootlan lsbootlan,
			String vnicName, String type, String provSrvPolicyName,
			String imgSecPolicyName, String imgPolicyName,
			String iscsivnicName, String bootIpPolicyName) {
		this.primaryKey = primaryKey;
		this.lsbootlan = lsbootlan;
		this.vnicName = vnicName;
		this.type = type;
		this.provSrvPolicyName = provSrvPolicyName;
		this.imgSecPolicyName = imgSecPolicyName;
		this.imgPolicyName = imgPolicyName;
		this.iSCSIVnicName = iscsivnicName;
		this.bootIpPolicyName = bootIpPolicyName;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Lsbootlan getLsbootlan() {
		return this.lsbootlan;
	}

	public void setLsbootlan(Lsbootlan lsbootlan) {
		this.lsbootlan = lsbootlan;
	}

	public String getVnicName() {
		return this.vnicName;
	}

	public void setVnicName(String vnicName) {
		this.vnicName = vnicName;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProvSrvPolicyName() {
		return this.provSrvPolicyName;
	}

	public void setProvSrvPolicyName(String provSrvPolicyName) {
		this.provSrvPolicyName = provSrvPolicyName;
	}

	public String getImgSecPolicyName() {
		return this.imgSecPolicyName;
	}

	public void setImgSecPolicyName(String imgSecPolicyName) {
		this.imgSecPolicyName = imgSecPolicyName;
	}

	public String getImgPolicyName() {
		return this.imgPolicyName;
	}

	public void setImgPolicyName(String imgPolicyName) {
		this.imgPolicyName = imgPolicyName;
	}

	public String getiSCSIVnicName() {
		return iSCSIVnicName;
	}

	public void setiSCSIVnicName(String iSCSIVnicName) {
		this.iSCSIVnicName = iSCSIVnicName;
	}

	public String getBootIpPolicyName() {
		return this.bootIpPolicyName;
	}

	public void setBootIpPolicyName(String bootIpPolicyName) {
		this.bootIpPolicyName = bootIpPolicyName;
	}

}
