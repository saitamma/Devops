package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "computeSlotQual")
public class Computeslotqual implements java.io.Serializable {

	private static final long serialVersionUID = 3963770098918723456L;
	private int primaryKey;
	private Computechassisqual computechassisqual;
	
	@XmlAttribute
	private String minId;
	
	@XmlAttribute
	private String maxId;

	public Computeslotqual() {
	}

	public Computeslotqual(int primaryKey, Computechassisqual computechassisqual) {
		this.primaryKey = primaryKey;
		this.computechassisqual = computechassisqual;
	}

	public Computeslotqual(int primaryKey,
			Computechassisqual computechassisqual, String minId, String maxId) {
		this.primaryKey = primaryKey;
		this.computechassisqual = computechassisqual;
		this.minId = minId;
		this.maxId = maxId;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Computechassisqual getComputechassisqual() {
		return this.computechassisqual;
	}

	public void setComputechassisqual(Computechassisqual computechassisqual) {
		this.computechassisqual = computechassisqual;
	}

	public String getMinId() {
		return this.minId;
	}

	public void setMinId(String minId) {
		this.minId = minId;
	}

	public String getMaxId() {
		return this.maxId;
	}

	public void setMaxId(String maxId) {
		this.maxId = maxId;
	}

}
