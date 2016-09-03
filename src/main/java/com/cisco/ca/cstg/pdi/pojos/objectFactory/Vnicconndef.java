package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "vnicConnDef")
public class Vnicconndef implements java.io.Serializable {

	private static final long serialVersionUID = -1726223356983594149L;
	private int primaryKey;
	private Lsserver lsserver;
	
	@XmlAttribute
	private String lanConnPolicyName;
	
	@XmlAttribute
	private String sanConnPolicyName;

	public Vnicconndef() {
	}

	public Vnicconndef(int primaryKey, Lsserver lsserver) {
		this.primaryKey = primaryKey;
		this.lsserver = lsserver;
	}

	public Vnicconndef(int primaryKey, Lsserver lsserver,
			String lanConnPolicyName, String sanConnPolicyName) {
		this.primaryKey = primaryKey;
		this.lsserver = lsserver;
		this.lanConnPolicyName = lanConnPolicyName;
		this.sanConnPolicyName = sanConnPolicyName;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Lsserver getLsserver() {
		return this.lsserver;
	}

	public void setLsserver(Lsserver lsserver) {
		this.lsserver = lsserver;
	}

	public String getLanConnPolicyName() {
		return this.lanConnPolicyName;
	}

	public void setLanConnPolicyName(String lanConnPolicyName) {
		this.lanConnPolicyName = lanConnPolicyName;
	}

	public String getSanConnPolicyName() {
		return this.sanConnPolicyName;
	}

	public void setSanConnPolicyName(String sanConnPolicyName) {
		this.sanConnPolicyName = sanConnPolicyName;
	}

}
