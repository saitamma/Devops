package com.cisco.ca.cstg.pdi.pojos.objectFactory;
// Generated Nov 11, 2014 10:41:23 AM by Hibernate Tools 4.0.0

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "fabricEthLanPc")
@XmlAccessorType(XmlAccessType.FIELD)
public class Fabricethlanpc implements java.io.Serializable {

	private static final long serialVersionUID = -2867998106060241443L;
	private int primaryKey;
	private Fabricethlan fabricethlan;
	
	@XmlAttribute(name = "name")
	private String name;
	
	@XmlAttribute(name = "descr")
	private String descr;
	
	@XmlAttribute(name = "adminState")
	private String adminState;
	
	@XmlAttribute(name = "operSpeed")
	private String operSpeed;
	
	@XmlAttribute(name = "flowCtrlPolicy")
	private String flowCtrlPolicy;
	
	@XmlAttribute(name = "adminSpeed")
	private String adminSpeed;
	
	@XmlAttribute(name = "portId")
	private String portId;
	
	@XmlElement(name="fabricEthLanPcEp")
	private List<Fabricethlanpcep> fabricethlanpcep   = new ArrayList<Fabricethlanpcep>();

	public Fabricethlanpc() {
	}

	public Fabricethlanpc(int primaryKey, Fabricethlan fabricethlan) {
		this.primaryKey = primaryKey;
		this.fabricethlan = fabricethlan;
	}

	public Fabricethlanpc(int primaryKey, Fabricethlan fabricethlan,
			String name, String descr, String adminState, String operSpeed,
			String flowCtrlPolicy, String adminSpeed, String portId,
			List<Fabricethlanpcep> fabricethlanpcep) {
		this.primaryKey = primaryKey;
		this.fabricethlan = fabricethlan;
		this.name = name;
		this.descr = descr;
		this.adminState = adminState;
		this.operSpeed = operSpeed;
		this.flowCtrlPolicy = flowCtrlPolicy;
		this.adminSpeed = adminSpeed;
		this.portId = portId;
		this.fabricethlanpcep = fabricethlanpcep;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Fabricethlan getFabricethlan() {
		return this.fabricethlan;
	}

	public void setFabricethlan(Fabricethlan fabricethlan) {
		this.fabricethlan = fabricethlan;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getAdminState() {
		return this.adminState;
	}

	public void setAdminState(String adminState) {
		this.adminState = adminState;
	}

	public String getOperSpeed() {
		return this.operSpeed;
	}

	public void setOperSpeed(String operSpeed) {
		this.operSpeed = operSpeed;
	}

	public String getFlowCtrlPolicy() {
		return this.flowCtrlPolicy;
	}

	public void setFlowCtrlPolicy(String flowCtrlPolicy) {
		this.flowCtrlPolicy = flowCtrlPolicy;
	}

	public String getAdminSpeed() {
		return this.adminSpeed;
	}

	public void setAdminSpeed(String adminSpeed) {
		this.adminSpeed = adminSpeed;
	}

	public String getPortId() {
		return this.portId;
	}

	public void setPortId(String portId) {
		this.portId = portId;
	}

	public List<Fabricethlanpcep> getFabricethlanpcep() {
		return this.fabricethlanpcep;
	}

	public void setFabricethlanpcep(List<Fabricethlanpcep> fabricethlanpcep) {
		this.fabricethlanpcep = fabricethlanpcep;
	}

}
