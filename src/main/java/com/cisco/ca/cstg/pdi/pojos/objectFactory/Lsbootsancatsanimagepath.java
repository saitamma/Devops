package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lsbootSanCatSanImagePath")
public class Lsbootsancatsanimagepath implements java.io.Serializable {

	private static final long serialVersionUID = 948076032894840198L;
	private int primaryKey;
	private Lsbootsancatsanimage lsbootsancatsanimage;
	
	@XmlAttribute
	private String wwn;
	
	@XmlAttribute
	private String type;
	
	@XmlAttribute
	private String lun;

	public Lsbootsancatsanimagepath() {
	}

	public Lsbootsancatsanimagepath(int primaryKey,
			Lsbootsancatsanimage lsbootsancatsanimage) {
		this.primaryKey = primaryKey;
		this.lsbootsancatsanimage = lsbootsancatsanimage;
	}

	public Lsbootsancatsanimagepath(int primaryKey,
			Lsbootsancatsanimage lsbootsancatsanimage, String wwn, String type,
			String lun) {
		this.primaryKey = primaryKey;
		this.lsbootsancatsanimage = lsbootsancatsanimage;
		this.wwn = wwn;
		this.type = type;
		this.lun = lun;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Lsbootsancatsanimage getLsbootsancatsanimage() {
		return this.lsbootsancatsanimage;
	}

	public void setLsbootsancatsanimage(
			Lsbootsancatsanimage lsbootsancatsanimage) {
		this.lsbootsancatsanimage = lsbootsancatsanimage;
	}

	public String getWwn() {
		return this.wwn;
	}

	public void setWwn(String wwn) {
		this.wwn = wwn;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLun() {
		return this.lun;
	}

	public void setLun(String lun) {
		this.lun = lun;
	}

}
