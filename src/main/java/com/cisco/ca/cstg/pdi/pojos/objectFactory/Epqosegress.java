package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "epqosEgress")
public class Epqosegress implements java.io.Serializable {

	private static final long serialVersionUID = 8759040520914553451L;
	private int primaryKey;
	private Epqosdefinition epqosdefinition;
	
	@XmlAttribute
	private String name;
	
	@XmlAttribute
	private String rate;
	
	@XmlAttribute
	private String prio;
	
	@XmlAttribute
	private String hostControl;
	
	@XmlAttribute
	private String burst;

	public Epqosegress() {
	}

	public Epqosegress(int primaryKey, Epqosdefinition epqosdefinition) {
		this.primaryKey = primaryKey;
		this.epqosdefinition = epqosdefinition;
	}

	public Epqosegress(int primaryKey, Epqosdefinition epqosdefinition,
			String name, String rate, String prio, String hostControl,
			String burst) {
		this.primaryKey = primaryKey;
		this.epqosdefinition = epqosdefinition;
		this.name = name;
		this.rate = rate;
		this.prio = prio;
		this.hostControl = hostControl;
		this.burst = burst;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Epqosdefinition getEpqosdefinition() {
		return this.epqosdefinition;
	}

	public void setEpqosdefinition(Epqosdefinition epqosdefinition) {
		this.epqosdefinition = epqosdefinition;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRate() {
		return this.rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getPrio() {
		return this.prio;
	}

	public void setPrio(String prio) {
		this.prio = prio;
	}

	public String getHostControl() {
		return this.hostControl;
	}

	public void setHostControl(String hostControl) {
		this.hostControl = hostControl;
	}

	public String getBurst() {
		return this.burst;
	}

	public void setBurst(String burst) {
		this.burst = burst;
	}

}
