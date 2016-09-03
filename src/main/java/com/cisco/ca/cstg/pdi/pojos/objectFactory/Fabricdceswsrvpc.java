package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "fabricDceSwSrvPc")
@XmlAccessorType(XmlAccessType.FIELD)
public class Fabricdceswsrvpc implements java.io.Serializable {

	private static final long serialVersionUID = -2846862339040961794L;
	private int primaryKey;
	@XmlAttribute(name="name")
	private String name;
	@XmlAttribute(name="descr")
	private String descr;
	@XmlAttribute(name="portId")
	private String portId;
	private Fabricdceswsrv fabricdceswsrv;
	@XmlElement(name="fabricDceSwSrvPcEp")
	private List<Fabricdceswsrvpcep> fabricdceswsrvpceps = new ArrayList<Fabricdceswsrvpcep>();

	public Fabricdceswsrvpc() {
	}

	public Fabricdceswsrvpc(String name, String descr, String portId, Fabricdceswsrv fabricdceswsrv,
			List<Fabricdceswsrvpcep> fabricdceswsrvpceps) {
		this.name = name;
		this.descr = descr;
		this.portId = portId;
		this.fabricdceswsrv = fabricdceswsrv;
		this.fabricdceswsrvpceps = fabricdceswsrvpceps;
	}

	public int getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getPortId() {
		return portId;
	}

	public void setPortId(String portId) {
		this.portId = portId;
	}

	public Fabricdceswsrv getFabricdceswsrv() {
		return fabricdceswsrv;
	}

	public void setFabricdceswsrv(Fabricdceswsrv fabricdceswsrv) {
		this.fabricdceswsrv = fabricdceswsrv;
	}

	public List<Fabricdceswsrvpcep> getFabricdceswsrvpceps() {
		return fabricdceswsrvpceps;
	}

	public void setFabricdceswsrvpceps(List<Fabricdceswsrvpcep> fabricdceswsrvpceps) {
		this.fabricdceswsrvpceps = fabricdceswsrvpceps;
	}

}
