package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "callhomeTestAlert")
public class Callhometestalert implements java.io.Serializable {

	private static final long serialVersionUID = 1808782021552467931L;
	private int primaryKey;
	private Callhomeep callhomeEp;
	@XmlAttribute
	private String description;
	@XmlAttribute
	private String group;
	@XmlAttribute
	private String level;
	@XmlAttribute
	private String messageSubtype;
	@XmlAttribute
	private String messageType;
	@XmlAttribute
	private String sendNow;

	public Callhometestalert() {
	}

	public Callhometestalert(Callhomeep callhomeEp) {
		this.callhomeEp = callhomeEp;
	}

	public Callhometestalert(Callhomeep callhomeEp, String description,
			String group, String level, String messageSubtype,
			String messageType, String sendNow) {
		this.callhomeEp = callhomeEp;
		this.description = description;
		this.group = group;
		this.level = level;
		this.messageSubtype = messageSubtype;
		this.messageType = messageType;
		this.sendNow = sendNow;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGroup() {
		return this.group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getMessageSubtype() {
		return this.messageSubtype;
	}

	public void setMessageSubtype(String messageSubtype) {
		this.messageSubtype = messageSubtype;
	}

	public String getMessageType() {
		return this.messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getSendNow() {
		return this.sendNow;
	}

	public void setSendNow(String sendNow) {
		this.sendNow = sendNow;
	}

}
