package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "adaptorEthOffloadProfile")
public class Adaptorethoffloadprofile implements java.io.Serializable {

	private static final long serialVersionUID = -7885342561280576750L;
	private int primaryKey;
	private Adaptorhostethifprofile adaptorhostethifprofile;
	@XmlAttribute
	private String largeReceive;
	@XmlAttribute
	private String tcpRxChecksum;
	@XmlAttribute
	private String tcpSegment;
	@XmlAttribute
	private String tcpTxChecksum;

	public Adaptorethoffloadprofile() {
	}

	public Adaptorethoffloadprofile(
			Adaptorhostethifprofile adaptorhostethifprofile) {
		this.adaptorhostethifprofile = adaptorhostethifprofile;
	}

	public Adaptorethoffloadprofile(
			Adaptorhostethifprofile adaptorhostethifprofile,
			String largeReceive, String tcpRxChecksum, String tcpSegment,
			String tcpTxChecksum) {
		this.adaptorhostethifprofile = adaptorhostethifprofile;
		this.largeReceive = largeReceive;
		this.tcpRxChecksum = tcpRxChecksum;
		this.tcpSegment = tcpSegment;
		this.tcpTxChecksum = tcpTxChecksum;
	}

	public int getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Adaptorhostethifprofile getAdaptorhostethifprofile() {
		return this.adaptorhostethifprofile;
	}

	public void setAdaptorhostethifprofile(
			Adaptorhostethifprofile adaptorhostethifprofile) {
		this.adaptorhostethifprofile = adaptorhostethifprofile;
	}

	public String getLargeReceive() {
		return this.largeReceive;
	}

	public void setLargeReceive(String largeReceive) {
		this.largeReceive = largeReceive;
	}

	public String getTcpRxChecksum() {
		return this.tcpRxChecksum;
	}

	public void setTcpRxChecksum(String tcpRxChecksum) {
		this.tcpRxChecksum = tcpRxChecksum;
	}

	public String getTcpSegment() {
		return this.tcpSegment;
	}

	public void setTcpSegment(String tcpSegment) {
		this.tcpSegment = tcpSegment;
	}

	public String getTcpTxChecksum() {
		return this.tcpTxChecksum;
	}

	public void setTcpTxChecksum(String tcpTxChecksum) {
		this.tcpTxChecksum = tcpTxChecksum;
	}

}
