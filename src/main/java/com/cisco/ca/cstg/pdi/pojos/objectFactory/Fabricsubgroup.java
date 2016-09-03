package com.cisco.ca.cstg.pdi.pojos.objectFactory;

// Generated Feb 24, 2016 5:16:04 PM by Hibernate Tools 4.0.0

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "fabricSubGroup")
@XmlAccessorType(XmlAccessType.FIELD)
public class Fabricsubgroup implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int primaryKey;
	private Fabricdceswsrv fabricdceswsrv;
	@XmlAttribute(name = "name")
	private String name;
	@XmlAttribute(name = "slotId")
	private String slotId;
	@XmlAttribute(name = "aggrPortId")
	private String aggrPortId;

	@XmlElement(name = "fabricDceSwSrvEp")
	private List<Fabricdceswsrvep> fabricdceswsrveps = new ArrayList<Fabricdceswsrvep>();

	public Fabricsubgroup() {
	}

	public Fabricsubgroup(Fabricdceswsrv fabricdceswsrv) {
		this.fabricdceswsrv = fabricdceswsrv;
	}

	public Fabricsubgroup(Fabricdceswsrv fabricdceswsrv, String name,
			String slotId, String aggrPortId,
			List<Fabricdceswsrvep> fabricdceswsrveps) {
		this.fabricdceswsrv = fabricdceswsrv;
		this.name = name;
		this.slotId = slotId;
		this.aggrPortId = aggrPortId;
		this.fabricdceswsrveps = fabricdceswsrveps;
	}

	public int getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Fabricdceswsrv getFabricdceswsrv() {
		return this.fabricdceswsrv;
	}

	public void setFabricdceswsrv(Fabricdceswsrv fabricdceswsrv) {
		this.fabricdceswsrv = fabricdceswsrv;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSlotId() {
		return this.slotId;
	}

	public void setSlotId(String slotId) {
		this.slotId = slotId;
	}

	public String getAggrPortId() {
		return this.aggrPortId;
	}

	public void setAggrPortId(String aggrPortId) {
		this.aggrPortId = aggrPortId;
	}

	public List<Fabricdceswsrvep> getFabricdceswsrveps() {
		return fabricdceswsrveps;
	}

	public void setFabricdceswsrveps(List<Fabricdceswsrvep> fabricdceswsrveps) {
		this.fabricdceswsrveps = fabricdceswsrveps;
	}

}
