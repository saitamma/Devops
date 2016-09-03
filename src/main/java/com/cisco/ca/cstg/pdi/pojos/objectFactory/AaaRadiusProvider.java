package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="aaaRadiusProvider")
public class AaaRadiusProvider implements java.io.Serializable {

	private static final long serialVersionUID = -7402077734552457712L;
	private Integer primaryKey;
	private AaaRadiusEp aaaRadiusEp;
	@XmlAttribute
	private String authPort;
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
	private String retries;
	@XmlAttribute
	private String timeout;

	public AaaRadiusProvider() {
	}

	public AaaRadiusProvider(AaaRadiusEp aaaRadiusEp) {
		this.aaaRadiusEp = aaaRadiusEp;
	}

	public AaaRadiusProvider(AaaRadiusEp aaaRadiusEp, String authPort,
			String descr, String name, String encKey, String keyKey,
			String orderOrder, String retries, String timeout) {
		this.aaaRadiusEp = aaaRadiusEp;
		this.authPort = authPort;
		this.descr = descr;
		this.name = name;
		this.encKey = encKey;
		this.keyKey = keyKey;
		this.orderOrder = orderOrder;
		this.retries = retries;
		this.timeout = timeout;
	}

	public Integer getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(Integer primaryKey) {
		this.primaryKey = primaryKey;
	}

	public AaaRadiusEp getAaaRadiusEp() {
		return this.aaaRadiusEp;
	}

	public void setAaaRadiusEp(AaaRadiusEp aaaRadiusEp) {
		this.aaaRadiusEp = aaaRadiusEp;
	}

	public String getAuthPort() {
		return this.authPort;
	}

	public void setAuthPort(String authPort) {
		this.authPort = authPort;
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
