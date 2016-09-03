package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="aaaTacacsPlusProvider")
public class AaaTacacsPlusProvider implements java.io.Serializable {

	private static final long serialVersionUID = -7971214838811820841L;
	private Integer primaryKey;
	private AaaTacacsPlusEp aaaTacacsPlusEp;
	@XmlAttribute
	private String descr;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String encKey;
	@XmlAttribute(name="key")
	private String keyKey;
	@XmlAttribute(name="order")
	private String orderOrder;
	@XmlAttribute
	private String port;
	@XmlAttribute
	private String retries;
	@XmlAttribute
	private String timeout;

	public AaaTacacsPlusProvider() {
	}

	public AaaTacacsPlusProvider(AaaTacacsPlusEp aaaTacacsPlusEp) {
		this.aaaTacacsPlusEp = aaaTacacsPlusEp;
	}

	public AaaTacacsPlusProvider(AaaTacacsPlusEp aaaTacacsPlusEp, String descr,
			String name, String encKey, String keyKey, String orderOrder,
			String port, String retries, String timeout) {
		this.aaaTacacsPlusEp = aaaTacacsPlusEp;
		this.descr = descr;
		this.name = name;
		this.encKey = encKey;
		this.keyKey = keyKey;
		this.orderOrder = orderOrder;
		this.port = port;
		this.retries = retries;
		this.timeout = timeout;
	}

	public Integer getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(Integer primaryKey) {
		this.primaryKey = primaryKey;
	}

	public AaaTacacsPlusEp getAaaTacacsPlusEp() {
		return this.aaaTacacsPlusEp;
	}

	public void setAaaTacacsPlusEp(AaaTacacsPlusEp aaaTacacsPlusEp) {
		this.aaaTacacsPlusEp = aaaTacacsPlusEp;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEncKey() {
		return this.encKey;
	}

	public void setEncKey(String encKey) {
		this.encKey = encKey;
	}

	public String getKeyKey() {
		return this.keyKey;
	}

	public void setKeyKey(String keyKey) {
		this.keyKey = keyKey;
	}

	public String getOrderOrder() {
		return this.orderOrder;
	}

	public void setOrderOrder(String orderOrder) {
		this.orderOrder = orderOrder;
	}

	public String getPort() {
		return this.port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getRetries() {
		return this.retries;
	}

	public void setRetries(String retries) {
		this.retries = retries;
	}

	public String getTimeout() {
		return this.timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

}
