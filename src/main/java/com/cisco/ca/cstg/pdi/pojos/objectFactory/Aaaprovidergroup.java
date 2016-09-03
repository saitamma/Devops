package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="aaaProviderGroup")
public class Aaaprovidergroup implements java.io.Serializable {

	private static final long serialVersionUID = -5801551989958369510L;
	private int primaryKey;
	private Aaaldapep aaaldapep;
	private AaaRadiusEp aaaRadiusEp;
	private AaaTacacsPlusEp aaaTacacsPlusEp;
	@XmlAttribute
	private String descr;
	@XmlAttribute
	private String name;
	@XmlElement(name="aaaProviderRef")
	private List<Aaaproviderref> aaaproviderrefs = new ArrayList<Aaaproviderref>();

	public Aaaprovidergroup() {
	}

	public Aaaprovidergroup(Aaaldapep aaaldapep) {
		this.aaaldapep = aaaldapep;
	}

	public Aaaprovidergroup(int primaryKey,Aaaldapep aaaldapep, AaaRadiusEp aaaRadiusEp,
			AaaTacacsPlusEp aaaTacacsPlusEp,String descr, String name,
			List<Aaaproviderref> aaaproviderrefs) {
		this.primaryKey = primaryKey;
		this.aaaldapep = aaaldapep;
		this.aaaRadiusEp = aaaRadiusEp;
		this.aaaTacacsPlusEp = aaaTacacsPlusEp;
		this.descr = descr;
		this.name = name;
		this.aaaproviderrefs = aaaproviderrefs;
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

	public AaaRadiusEp getAaaRadiusEp() {
		return aaaRadiusEp;
	}

	public void setAaaRadiusEp(AaaRadiusEp aaaRadiusEp) {
		this.aaaRadiusEp = aaaRadiusEp;
	}

	public AaaTacacsPlusEp getAaaTacacsPlusEp() {
		return aaaTacacsPlusEp;
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

	public List<Aaaproviderref> getAaaproviderrefs() {
		return aaaproviderrefs;
	}

	public void setAaaproviderrefs(List<Aaaproviderref> aaaproviderrefs) {
		this.aaaproviderrefs = aaaproviderrefs;
	}

}
