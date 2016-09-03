package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "computeChassisQual")
public class Computechassisqual implements java.io.Serializable {

	private static final long serialVersionUID = 8528686176785543025L;
	private int primaryKey;
	private Computequal computequal;
	
	@XmlAttribute
	private String minId;
	
	@XmlAttribute
	private String maxId;
	
	@XmlElement
	private List<Computeslotqual> computeSlotQual  = new ArrayList<Computeslotqual>();

	public Computechassisqual() {
	}

	public Computechassisqual(int primaryKey, Computequal computequal) {
		this.primaryKey = primaryKey;
		this.computequal = computequal;
	}

	public Computechassisqual(int primaryKey, Computequal computequal,
			String minId, String maxId, List<Computeslotqual> computeslotquals) {
		this.primaryKey = primaryKey;
		this.computequal = computequal;
		this.minId = minId;
		this.maxId = maxId;
		this.computeSlotQual = computeslotquals;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Computequal getComputequal() {
		return this.computequal;
	}

	public void setComputequal(Computequal computequal) {
		this.computequal = computequal;
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

	public List<Computeslotqual> getComputeSlotQual() {
		return computeSlotQual;
	}

	public void setComputeSlotQual(List<Computeslotqual> computeSlotQual) {
		this.computeSlotQual = computeSlotQual;
	}
}
