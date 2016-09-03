package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="aaaRole")
public class AaaRole implements java.io.Serializable {

	private static final long serialVersionUID = 2008954203432342385L;
	private Integer primaryKey;
	private AaaUserEp aaaUserEp;
	@XmlAttribute
	private String policyOwner;
	@XmlAttribute
	private String descr;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String priv;

	public AaaRole() {
	}

	public AaaRole(AaaUserEp aaaUserEp) {
		this.aaaUserEp = aaaUserEp;
	}

	public AaaRole(AaaUserEp aaaUserEp, String policyOwner, String descr,
			String name, String priv) {
		this.aaaUserEp = aaaUserEp;
		this.policyOwner = policyOwner;
		this.descr = descr;
		this.name = name;
		this.priv = priv;
	}

	public Integer getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(Integer primaryKey) {
		this.primaryKey = primaryKey;
	}

	public AaaUserEp getAaaUserEp() {
		return this.aaaUserEp;
	}

	public void setAaaUserEp(AaaUserEp aaaUserEp) {
		this.aaaUserEp = aaaUserEp;
	}

	public String getPolicyOwner() {
		return this.policyOwner;
	}

	public void setPolicyOwner(String policyOwner) {
		this.policyOwner = policyOwner;
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

	public String getPriv() {
		return this.priv;
	}

	public void setPriv(String priv) {
		this.priv = priv;
	}

}
