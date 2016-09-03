package com.cisco.ca.cstg.pdi.pojos.objectFactory;
// Generated Nov 11, 2014 10:41:23 AM by Hibernate Tools 4.0.0

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "fabricEp")
@XmlAccessorType(XmlAccessType.FIELD)
public class Fabricep implements java.io.Serializable {

	private static final long serialVersionUID = -2157502056707652194L;
	private int primaryKey;
	private Toproot toproot;
	
	private List<Fabricfcestccloud> fabricFcEstcCloud = new ArrayList<Fabricfcestccloud>();
	private List<Fabricdcesrv> fabricDceSrv = new ArrayList<Fabricdcesrv>();
	private List<Fabriclancloud> fabricLanCloud = new ArrayList<Fabriclancloud>();
	private List<Fabricsancloud> fabricSanCloud = new ArrayList<Fabricsancloud>();
	private List<Fabricethestccloud> fabricEthEstcCloud = new ArrayList<Fabricethestccloud>();

	public Fabricep() {
	}

	public Fabricep(int primaryKey, Toproot toproot) {
		this.primaryKey = primaryKey;
		this.toproot = toproot;
	}

	public Fabricep(int primaryKey, Toproot toproot, List<Fabricfcestccloud> fabricFcEstcCloud,
			List<Fabricdcesrv> fabricDceSrv, List<Fabriclancloud> fabricLanCloud, List<Fabricsancloud> fabricSanCloud,
			List<Fabricethestccloud> fabricEthEstcCloud) {
		this.primaryKey = primaryKey;
		this.toproot = toproot;
		this.fabricFcEstcCloud = fabricFcEstcCloud;
		this.fabricDceSrv = fabricDceSrv;
		this.fabricLanCloud = fabricLanCloud;
		this.fabricSanCloud = fabricSanCloud;
		this.fabricEthEstcCloud = fabricEthEstcCloud;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Toproot getToproot() {
		return this.toproot;
	}

	public void setToproot(Toproot toproot) {
		this.toproot = toproot;
	}

	public List<Fabricfcestccloud> getFabricFcEstcCloud() {
		return fabricFcEstcCloud;
	}

	public void setFabricFcEstcCloud(List<Fabricfcestccloud> fabricFcEstcCloud) {
		this.fabricFcEstcCloud = fabricFcEstcCloud;
	}

	public List<Fabricdcesrv> getFabricDceSrv() {
		return fabricDceSrv;
	}

	public void setFabricDceSrv(List<Fabricdcesrv> fabricDceSrv) {
		this.fabricDceSrv = fabricDceSrv;
	}

	public List<Fabriclancloud> getFabricLanCloud() {
		return fabricLanCloud;
	}

	public void setFabricLanCloud(List<Fabriclancloud> fabricLanCloud) {
		this.fabricLanCloud = fabricLanCloud;
	}

	public List<Fabricsancloud> getFabricSanCloud() {
		return fabricSanCloud;
	}

	public void setFabricSanCloud(List<Fabricsancloud> fabricSanCloud) {
		this.fabricSanCloud = fabricSanCloud;
	}

	public List<Fabricethestccloud> getFabricEthEstcCloud() {
		return fabricEthEstcCloud;
	}

	public void setFabricEthEstcCloud(List<Fabricethestccloud> fabricEthEstcCloud) {
		this.fabricEthEstcCloud = fabricEthEstcCloud;
	}

}
