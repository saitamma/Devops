package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "lsbootSanCatSanImage")
public class Lsbootsancatsanimage implements java.io.Serializable {

	private static final long serialVersionUID = 3405544746584988109L;
	private int primaryKey;
	private Lsbootsan lsbootsan;
	
	@XmlAttribute
	private String vnicName;
	
	@XmlAttribute
	private String type;
	
	@XmlElement
	private List<Lsbootsancatsanimagepath> lsbootSanCatSanImagePath = new ArrayList<Lsbootsancatsanimagepath>();

	public Lsbootsancatsanimage() {
	}

	public Lsbootsancatsanimage(int primaryKey, Lsbootsan lsbootsan) {
		this.primaryKey = primaryKey;
		this.lsbootsan = lsbootsan;
	}

	public Lsbootsancatsanimage(int primaryKey, Lsbootsan lsbootsan,
			String vnicName, String type, List<Lsbootsancatsanimagepath> lsbootsancatsanimagepaths) {
		this.primaryKey = primaryKey;
		this.lsbootsan = lsbootsan;
		this.vnicName = vnicName;
		this.type = type;
		this.lsbootSanCatSanImagePath = lsbootsancatsanimagepaths;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Lsbootsan getLsbootsan() {
		return this.lsbootsan;
	}

	public void setLsbootsan(Lsbootsan lsbootsan) {
		this.lsbootsan = lsbootsan;
	}

	public String getVnicName() {
		return this.vnicName;
	}

	public void setVnicName(String vnicName) {
		this.vnicName = vnicName;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Lsbootsancatsanimagepath> getLsbootSanCatSanImagePath() {
		return lsbootSanCatSanImagePath;
	}

	public void setLsbootSanCatSanImagePath(
			List<Lsbootsancatsanimagepath> lsbootSanCatSanImagePath) {
		this.lsbootSanCatSanImagePath = lsbootSanCatSanImagePath;
	}
}
