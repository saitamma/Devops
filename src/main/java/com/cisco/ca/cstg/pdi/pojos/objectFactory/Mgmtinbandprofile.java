package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
// Generated Nov 11, 2014 10:41:23 AM by Hibernate Tools 4.0.0
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "mgmtInbandProfile")
@XmlAccessorType(XmlAccessType.FIELD)
public class Mgmtinbandprofile implements java.io.Serializable {

	private static final long serialVersionUID = -1082869304715905849L;
	private int primaryKey;
	private Fabriclancloud fabriclancloud;
	
	@XmlAttribute(name = "poolName")
	private String poolName;
	
	@XmlAttribute(name = "name")
	private String name;
	
	@XmlAttribute(name = "defaultVlanName")
	private String defaultVlanName;

	public Mgmtinbandprofile() {
	}

	public Mgmtinbandprofile(int primaryKey, Fabriclancloud fabriclancloud) {
		this.primaryKey = primaryKey;
		this.fabriclancloud = fabriclancloud;
	}

	public Mgmtinbandprofile(int primaryKey, Fabriclancloud fabriclancloud,
			String poolName, String name, String defaultVlanName) {
		this.primaryKey = primaryKey;
		this.fabriclancloud = fabriclancloud;
		this.poolName = poolName;
		this.name = name;
		this.defaultVlanName = defaultVlanName;
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

	public String getPoolName() {
		return this.poolName;
	}

	public void setPoolName(String poolName) {
		this.poolName = poolName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDefaultVlanName() {
		return this.defaultVlanName;
	}

	public void setDefaultVlanName(String defaultVlanName) {
		this.defaultVlanName = defaultVlanName;
	}

}
