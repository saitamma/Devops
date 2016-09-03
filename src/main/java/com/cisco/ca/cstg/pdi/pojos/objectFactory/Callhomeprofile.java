package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "callhomeProfile")
public class Callhomeprofile implements java.io.Serializable {

	private static final long serialVersionUID = 4044506046762809902L;
	private int primaryKey;
	private Callhomeep callhomeEp;
	@XmlAttribute
	private String alertGroups;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String descr;
	@XmlAttribute
	private String format;
	@XmlAttribute
	private String level;
	@XmlAttribute
	private String maxSize;
	
	private List<Callhomedest> callhomeDest = new ArrayList<Callhomedest>();

	public Callhomeprofile() {
	}

	public Callhomeprofile(Callhomeep callhomeEp) {
		this.callhomeEp = callhomeEp;
	}

	public Callhomeprofile(Callhomeep callhomeEp, String alertGroups,
			String name, String descr, String format, String level,
			String maxSize, List<Callhomedest> callhomeDest) {
		this.callhomeEp = callhomeEp;
		this.alertGroups = alertGroups;
		this.name = name;
		this.descr = descr;
		this.format = format;
		this.level = level;
		this.maxSize = maxSize;
		this.callhomeDest = callhomeDest;
	}

	public int getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Callhomeep getCallhomeEp() {
		return callhomeEp;
	}

	public void setCallhomeEp(Callhomeep callhomeEp) {
		this.callhomeEp = callhomeEp;
	}

	public String getAlertGroups() {
		return this.alertGroups;
	}

	public void setAlertGroups(String alertGroups) {
		this.alertGroups = alertGroups;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getFormat() {
		return this.format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getMaxSize() {
		return this.maxSize;
	}

	public void setMaxSize(String maxSize) {
		this.maxSize = maxSize;
	}

	public List<Callhomedest> getCallhomeDest() {
		return callhomeDest;
	}

	public void setCallhomeDest(List<Callhomedest> callhomeDest) {
		this.callhomeDest = callhomeDest;
	}

}
