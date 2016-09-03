package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="aaaLdapProvider")
public class Aaaldapprovider implements java.io.Serializable {

	private static final long serialVersionUID = -4824036812850060242L;
	private int primaryKey;
	private Aaaldapep aaaldapep;
	@XmlAttribute
	private String attribute;
	@XmlAttribute
	private String basedn;
	@XmlAttribute
	private String descr;
	@XmlAttribute(name="enableSSL")
	private String enableSsl;
	@XmlAttribute
	private String encKey;
	@XmlAttribute
	private String filter;
	@XmlAttribute
	private String key;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String order;
	@XmlAttribute
	private String port;
	@XmlAttribute
	private String retries;
	@XmlAttribute
	private String rootdn;
	@XmlAttribute
	private String timeout;
	@XmlAttribute
	private String vendor;
	
	@XmlElement(name="aaaLdapGroupRule")
	private List<Aaaldapgrouprule> aaaldapgrouprules = new ArrayList<Aaaldapgrouprule>();

	public Aaaldapprovider() {
	}

	public Aaaldapprovider(Aaaldapep aaaldapep) {
		this.aaaldapep = aaaldapep;
	}

	public Aaaldapprovider(int primaryKe,Aaaldapep aaaldapep, String attribute,
			String basedn, String descr, String enableSsl, String encKey,
			String filter, String key, String name, String order,
			String port, String retries, String rootdn, String timeout,
			String vendor, List<Aaaldapgrouprule> aaaldapgrouprules) {
		this.primaryKey = primaryKe;
		this.aaaldapep = aaaldapep;
		this.attribute = attribute;
		this.basedn = basedn;
		this.descr = descr;
		this.enableSsl = enableSsl;
		this.encKey = encKey;
		this.filter = filter;
		this.key = key;
		this.name = name;
		this.order = order;
		this.port = port;
		this.retries = retries;
		this.rootdn = rootdn;
		this.timeout = timeout;
		this.vendor = vendor;
		this.aaaldapgrouprules = aaaldapgrouprules;
	}


	public int getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Aaaldapep getAaaldapep() {
		return this.aaaldapep;
	}

	public void setAaaldapep(Aaaldapep aaaldapep) {
		this.aaaldapep = aaaldapep;
	}

	public String getAttribute() {
		return this.attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getBasedn() {
		return this.basedn;
	}

	public void setBasedn(String basedn) {
		this.basedn = basedn;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getEnableSsl() {
		return this.enableSsl;
	}

	public void setEnableSsl(String enableSsl) {
		this.enableSsl = enableSsl;
	}

	public String getEncKey() {
		return this.encKey;
	}

	public void setEncKey(String encKey) {
		this.encKey = encKey;
	}

	public String getFilter() {
		return this.filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
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

	public String getRootdn() {
		return this.rootdn;
	}

	public void setRootdn(String rootdn) {
		this.rootdn = rootdn;
	}

	public String getTimeout() {
		return this.timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	public String getVendor() {
		return this.vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public List<Aaaldapgrouprule> getAaaldapgrouprules() {
		return aaaldapgrouprules;
	}

	public void setAaaldapgrouprules(List<Aaaldapgrouprule> aaaldapgrouprules) {
		this.aaaldapgrouprules = aaaldapgrouprules;
	}

}
