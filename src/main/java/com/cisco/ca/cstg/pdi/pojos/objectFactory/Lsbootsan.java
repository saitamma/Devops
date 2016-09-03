package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "lsbootSan")
public class Lsbootsan implements java.io.Serializable {

	private static final long serialVersionUID = 2065956442341955188L;
	private int primaryKey;
	private Lsbootpolicy lsbootpolicy;
	
	@XmlAttribute
	private String order;
	
	@XmlElement
	private List<Lsbootsancatsanimage> lsbootSanCatSanImage = new ArrayList<Lsbootsancatsanimage>();

	public Lsbootsan() {
	}

	public Lsbootsan(int primaryKey, Lsbootpolicy lsbootpolicy) {
		this.primaryKey = primaryKey;
		this.lsbootpolicy = lsbootpolicy;
	}

	public Lsbootsan(int primaryKey, Lsbootpolicy lsbootpolicy, String order,
			List<Lsbootsancatsanimage> lsbootsancatsanimages) {
		this.primaryKey = primaryKey;
		this.lsbootpolicy = lsbootpolicy;
		this.order = order;
		this.lsbootSanCatSanImage = lsbootsancatsanimages;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Lsbootpolicy getLsbootpolicy() {
		return this.lsbootpolicy;
	}

	public void setLsbootpolicy(Lsbootpolicy lsbootpolicy) {
		this.lsbootpolicy = lsbootpolicy;
	}

	public String getOrder() {
		return this.order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public List<Lsbootsancatsanimage> getLsbootSanCatSanImage() {
		return lsbootSanCatSanImage;
	}

	public void setLsbootSanCatSanImage(
			List<Lsbootsancatsanimage> lsbootSanCatSanImage) {
		this.lsbootSanCatSanImage = lsbootSanCatSanImage;
	}

}
