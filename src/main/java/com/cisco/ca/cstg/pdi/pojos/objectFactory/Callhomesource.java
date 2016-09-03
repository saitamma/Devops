package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "callhomeSource")
public class Callhomesource implements java.io.Serializable {

	private static final long serialVersionUID = 1625031647500022084L;
	private int primaryKey;
	private Callhomeep callhomeEp;
	@XmlAttribute
	private String addr;
	@XmlAttribute
	private String contact;
	@XmlAttribute
	private String contract;
	@XmlAttribute
	private String customer;
	@XmlAttribute
	private String email;
	@XmlAttribute
	private String from;
	@XmlAttribute
	private String phone;
	@XmlAttribute
	private String replyTo;
	@XmlAttribute
	private String site;
	@XmlAttribute
	private String urgency;

	public Callhomesource() {
	}

	public Callhomesource(Callhomeep callhomeEp) {
		this.callhomeEp = callhomeEp;
	}

	public Callhomesource(Callhomeep callhomeEp, String addr, String contact,
			String contract, String customer, String email, String from,
			String phone, String replyTo, String site, String urgency) {
		this.callhomeEp = callhomeEp;
		this.addr = addr;
		this.contact = contact;
		this.contract = contract;
		this.customer = customer;
		this.email = email;
		this.from = from;
		this.phone = phone;
		this.replyTo = replyTo;
		this.site = site;
		this.urgency = urgency;
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

	public String getAddr() {
		return this.addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContract() {
		return this.contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public String getCustomer() {
		return this.customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFrom() {
		return this.from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getReplyTo() {
		return this.replyTo;
	}

	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}

	public String getSite() {
		return this.site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getUrgency() {
		return this.urgency;
	}

	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}

}
