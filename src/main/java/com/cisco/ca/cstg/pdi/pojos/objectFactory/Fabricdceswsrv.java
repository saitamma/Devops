package com.cisco.ca.cstg.pdi.pojos.objectFactory;
// Generated Nov 11, 2014 10:41:23 AM by Hibernate Tools 4.0.0

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "fabricDceSwSrv")
@XmlAccessorType(XmlAccessType.FIELD)
public class Fabricdceswsrv implements java.io.Serializable {

	private static final long serialVersionUID = 4177032338180678452L;
	private int primaryKey;
	private Fabricdcesrv fabricdcesrv;
	
	@XmlAttribute(name="name")
	private String name;
	
	@XmlAttribute(name="id")
	private String id;
	
	@XmlElement(name="fabricDceSwSrvEp")
	private List<Fabricdceswsrvep> fabricdceswsrveps = new ArrayList<Fabricdceswsrvep>();
	
	@XmlElement(name="fabricDceSwSrvPc")
	private List<Fabricdceswsrvpc> fabricdceswsrvpcs = new ArrayList<Fabricdceswsrvpc>();
	
	@XmlElement(name="fabricSubGroup")
	private List<Fabricsubgroup> fabricsubgroups = new ArrayList<Fabricsubgroup>();

	public Fabricdceswsrv() {
	}

	public Fabricdceswsrv(int primaryKey, Fabricdcesrv fabricdcesrv) {
		this.primaryKey = primaryKey;
		this.fabricdcesrv = fabricdcesrv;
	}

	public Fabricdceswsrv(int primaryKey, Fabricdcesrv fabricdcesrv,
			String name, String id, List<Fabricdceswsrvep> fabricdceswsrveps, List<Fabricdceswsrvpc> fabricdceswsrvpcs, List<Fabricsubgroup> fabricsubgroups) {
		this.primaryKey = primaryKey;
		this.fabricdcesrv = fabricdcesrv;
		this.name = name;
		this.id = id;
		this.fabricdceswsrveps = fabricdceswsrveps;
		this.fabricdceswsrvpcs = fabricdceswsrvpcs;
		this.fabricsubgroups = fabricsubgroups;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Fabricdcesrv getFabricdcesrv() {
		return this.fabricdcesrv;
	}

	public void setFabricdcesrv(Fabricdcesrv fabricdcesrv) {
		this.fabricdcesrv = fabricdcesrv;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Fabricdceswsrvep> getFabricdceswsrveps() {
		return fabricdceswsrveps;
	}

	public void setFabricdceswsrveps(List<Fabricdceswsrvep> fabricdceswsrveps) {
		this.fabricdceswsrveps = fabricdceswsrveps;
	}

	public List<Fabricdceswsrvpc> getFabricdceswsrvpcs() {
		return fabricdceswsrvpcs;
	}

	public void setFabricdceswsrvpcs(List<Fabricdceswsrvpc> fabricdceswsrvpcs) {
		this.fabricdceswsrvpcs = fabricdceswsrvpcs;
	}

	public List<Fabricsubgroup> getFabricsubgroups() {
		return fabricsubgroups;
	}

	public void setFabricsubgroups(List<Fabricsubgroup> fabricsubgroups) {
		this.fabricsubgroups = fabricsubgroups;
	}

}
