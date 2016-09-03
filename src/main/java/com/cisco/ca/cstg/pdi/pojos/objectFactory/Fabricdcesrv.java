package com.cisco.ca.cstg.pdi.pojos.objectFactory;
// Generated Nov 11, 2014 10:41:23 AM by Hibernate Tools 4.0.0

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "fabricDceSrv")
@XmlAccessorType(XmlAccessType.FIELD)
public class Fabricdcesrv implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4533478335329496174L;
	private int primaryKey;
	private Fabricep fabricep;
	
	@XmlAttribute(name = "name")
	private String name;
	
	@XmlAttribute(name = "id")
	private String id;
	
	@XmlElement(name="fabricDceSwSrv")
	private List<Fabricdceswsrv> fabricdceswsrvs = new ArrayList<Fabricdceswsrv>();
	
	@XmlElement(name="statsThresholdPolicy")
	private List<Statsthresholdpolicy> statsthresholdpolicies = new ArrayList<Statsthresholdpolicy>();

	public Fabricdcesrv() {
	}

	public Fabricdcesrv(int primaryKey, Fabricep fabricep) {
		this.primaryKey = primaryKey;
		this.fabricep = fabricep;
	}

	public Fabricdcesrv(int primaryKey, Fabricep fabricep, String name,
			String id, List<Fabricdceswsrv> fabricdceswsrvs, List<Statsthresholdpolicy> statsthresholdpolicies) {
		this.primaryKey = primaryKey;
		this.fabricep = fabricep;
		this.name = name;
		this.id = id;
		this.fabricdceswsrvs = fabricdceswsrvs;
		this.statsthresholdpolicies = statsthresholdpolicies;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Fabricep getFabricep() {
		return this.fabricep;
	}

	public void setFabricep(Fabricep fabricep) {
		this.fabricep = fabricep;
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

	public List<Fabricdceswsrv> getFabricdceswsrvs() {
		return fabricdceswsrvs;
	}

	public void setFabricdceswsrvs(List<Fabricdceswsrv> fabricdceswsrvs) {
		this.fabricdceswsrvs = fabricdceswsrvs;
	}

	public List<Statsthresholdpolicy> getStatsthresholdpolicies() {
		return statsthresholdpolicies;
	}

	public void setStatsthresholdpolicies(
			List<Statsthresholdpolicy> statsthresholdpolicies) {
		this.statsthresholdpolicies = statsthresholdpolicies;
	}

}
