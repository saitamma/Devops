package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "vnicEtherIf")
public class Vnicetherif implements java.io.Serializable {

	private static final long serialVersionUID = -6497860391906391175L;
	private int primaryKey;
	private Vnicether vnicEther;
	private Vniclanconntempl vnicLanConnTempl;
	
	@XmlAttribute
	private String defaultNet;
	
	@XmlAttribute
	private String name;

	public Vnicetherif() {
	}

	public Vnicetherif(int primaryKey, Vnicether vnicether,
			Vniclanconntempl vniclanconntempl) {
		this.primaryKey = primaryKey;
		this.vnicEther = vnicether;
		this.vnicLanConnTempl = vniclanconntempl;
	}

	public Vnicetherif(int primaryKey, Vnicether vnicether,
			Vniclanconntempl vniclanconntempl, String defaultNet, String name) {
		this.primaryKey = primaryKey;
		this.vnicEther = vnicether;
		this.vnicLanConnTempl = vniclanconntempl;
		this.defaultNet = defaultNet;
		this.name = name;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Vnicether getVnicether() {
		return this.vnicEther;
	}

	public void setVnicether(Vnicether vnicether) {
		this.vnicEther = vnicether;
	}

	public Vniclanconntempl getVniclanconntempl() {
		return this.vnicLanConnTempl;
	}

	public void setVniclanconntempl(Vniclanconntempl vniclanconntempl) {
		this.vnicLanConnTempl = vniclanconntempl;
	}

	public String getDefaultNet() {
		return this.defaultNet;
	}

	public void setDefaultNet(String defaultNet) {
		this.defaultNet = defaultNet;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
