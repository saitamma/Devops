package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="aaaLocale")
public class AaaLocale implements java.io.Serializable {

	private static final long serialVersionUID = 8938334222759418343L;
	private Integer primaryKey;
	private AaaUserEp aaaUserEp;
	@XmlAttribute
	private String policyOwner;
	@XmlAttribute
	private String descr;
	@XmlAttribute
	private String name;
	@XmlElement(name="aaaOrg")
	private List<AaaOrg> aaaOrgs = new ArrayList<AaaOrg>();

	public AaaLocale() {
	}

	public AaaLocale(AaaUserEp aaaUserEp) {
		this.aaaUserEp = aaaUserEp;
	}

	public AaaLocale(AaaUserEp aaaUserEp, String policyOwner, String descr,
			String name, List<AaaOrg> aaaOrgs) {
		this.aaaUserEp = aaaUserEp;
		this.policyOwner = policyOwner;
		this.descr = descr;
		this.name = name;
		this.aaaOrgs = aaaOrgs;
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

	public List<AaaOrg> getAaaOrgs() {
		return aaaOrgs;
	}

	public void setAaaOrgs(List<AaaOrg> aaaOrgs) {
		this.aaaOrgs = aaaOrgs;
	}

}
