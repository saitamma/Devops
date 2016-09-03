package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "vnicFcNode")
public class Vnicfcnode implements java.io.Serializable {

	private static final long serialVersionUID = -479013935776197699L;
	private int primaryKey;
	private Lsserver lsserver;
	private Vnicsanconnpolicy vnicsanconnpolicy;
	
	@XmlAttribute
	private String identPoolName;
	
	@XmlAttribute
	private String addr;

	public Vnicfcnode() {
	}

	public Vnicfcnode(int primaryKey, Lsserver lsserver,
			Vnicsanconnpolicy vnicsanconnpolicy) {
		this.primaryKey = primaryKey;
		this.lsserver = lsserver;
		this.vnicsanconnpolicy = vnicsanconnpolicy;
	}

	public Vnicfcnode(int primaryKey, Lsserver lsserver,
			Vnicsanconnpolicy vnicsanconnpolicy, String identPoolName,
			String addr) {
		this.primaryKey = primaryKey;
		this.lsserver = lsserver;
		this.vnicsanconnpolicy = vnicsanconnpolicy;
		this.identPoolName = identPoolName;
		this.addr = addr;
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

	public Vnicsanconnpolicy getVnicsanconnpolicy() {
		return this.vnicsanconnpolicy;
	}

	public void setVnicsanconnpolicy(Vnicsanconnpolicy vnicsanconnpolicy) {
		this.vnicsanconnpolicy = vnicsanconnpolicy;
	}

	public String getIdentPoolName() {
		return this.identPoolName;
	}

	public void setIdentPoolName(String identPoolName) {
		this.identPoolName = identPoolName;
	}

	public String getAddr() {
		return this.addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

}
