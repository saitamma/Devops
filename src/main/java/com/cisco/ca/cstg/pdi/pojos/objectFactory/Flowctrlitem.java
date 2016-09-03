package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
// Generated Nov 11, 2014 10:41:23 AM by Hibernate Tools 4.0.0

@XmlType(name = "flowctrlItem")
@XmlAccessorType(XmlAccessType.FIELD)
public class Flowctrlitem implements java.io.Serializable {

	private static final long serialVersionUID = 7798562328216931942L;
	private int primaryKey;
	private Flowctrldefinition flowctrldefinition;
	
	@XmlAttribute(name="snd")
	private String snd;
	
	@XmlAttribute(name="rcv")
	private String rcv;
	
	@XmlAttribute(name="prio")
	private String prio;
	
	@XmlAttribute(name="name")
	private String name;

	public Flowctrlitem() {
	}

	public Flowctrlitem(int primaryKey, Flowctrldefinition flowctrldefinition) {
		this.primaryKey = primaryKey;
		this.flowctrldefinition = flowctrldefinition;
	}

	public Flowctrlitem(int primaryKey, Flowctrldefinition flowctrldefinition,
			String snd, String rcv, String prio, String name) {
		this.primaryKey = primaryKey;
		this.flowctrldefinition = flowctrldefinition;
		this.snd = snd;
		this.rcv = rcv;
		this.prio = prio;
		this.name = name;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Flowctrldefinition getFlowctrldefinition() {
		return this.flowctrldefinition;
	}

	public void setFlowctrldefinition(Flowctrldefinition flowctrldefinition) {
		this.flowctrldefinition = flowctrldefinition;
	}

	public String getSnd() {
		return this.snd;
	}

	public void setSnd(String snd) {
		this.snd = snd;
	}

	public String getRcv() {
		return this.rcv;
	}

	public void setRcv(String rcv) {
		this.rcv = rcv;
	}

	public String getPrio() {
		return this.prio;
	}

	public void setPrio(String prio) {
		this.prio = prio;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
