package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "vnicFcIf")
public class Vnicfcif implements java.io.Serializable {

	private static final long serialVersionUID = 496477282756544029L;
	
	private int primaryKey;
	private Vnicsanconntempl vnicSanConnTempl;
	private Vnicfc vnicfc;
	
	@XmlAttribute
	private String name;
	
	@XmlAttribute
	private String rn;

	public Vnicfcif() {
	}

	public Vnicfcif(int primaryKey, Vnicsanconntempl vnicsanconntempl,
			Vnicfc vnicfc) {
		this.primaryKey = primaryKey;
		this.vnicSanConnTempl = vnicsanconntempl;
		this.vnicfc = vnicfc;
	}

	public Vnicfcif(int primaryKey, Vnicsanconntempl vnicsanconntempl,
			Vnicfc vnicfc, String name, String rn) {
		this.primaryKey = primaryKey;
		this.vnicSanConnTempl = vnicsanconntempl;
		this.vnicfc = vnicfc;
		this.name = name;
		this.rn = rn;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Vnicsanconntempl getVnicsanconntempl() {
		return this.vnicSanConnTempl;
	}

	public void setVnicsanconntempl(Vnicsanconntempl vnicsanconntempl) {
		this.vnicSanConnTempl = vnicsanconntempl;
	}

	public Vnicfc getVnicfc() {
		return this.vnicfc;
	}

	public void setVnicfc(Vnicfc vnicfc) {
		this.vnicfc = vnicfc;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRn() {
		return this.rn;
	}

	public void setRn(String rn) {
		this.rn = rn;
	}

}
