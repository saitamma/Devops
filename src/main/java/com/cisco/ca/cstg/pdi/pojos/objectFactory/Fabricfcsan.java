package com.cisco.ca.cstg.pdi.pojos.objectFactory;
// Generated Nov 11, 2014 10:41:23 AM by Hibernate Tools 4.0.0

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "fabricFcSan")
@XmlAccessorType(XmlAccessType.FIELD)
public class Fabricfcsan implements java.io.Serializable {

	private static final long serialVersionUID = 9012802029131180090L;
	private int primaryKey;
	private Fabricsancloud fabricsancloud;
	
	@XmlAttribute(name="name")
	private String name;
	
	@XmlAttribute(name="uplinkTrunking")
	private String uplinkTrunking;
	
	@XmlAttribute(name="id")
	private String id;
	
	private List<Fabricfcsanep> fabricFcSanEp  = new ArrayList<Fabricfcsanep>();
	private List<Fabricvsan> fabricVsan = new ArrayList<Fabricvsan>();
	public Fabricfcsan() {
	}

	public Fabricfcsan(int primaryKey, Fabricsancloud fabricsancloud) {
		this.primaryKey = primaryKey;
		this.fabricsancloud = fabricsancloud;
	}

	public Fabricfcsan(int primaryKey, Fabricsancloud fabricsancloud,
			String name, String uplinkTrunking, String id, List<Fabricfcsanep> fabricFcSanEp,
			List<Fabricvsan> fabricVsan) {
		this.primaryKey = primaryKey;
		this.fabricsancloud = fabricsancloud;
		this.name = name;
		this.uplinkTrunking = uplinkTrunking;
		this.id = id;
		this.fabricFcSanEp = fabricFcSanEp;
		this.fabricVsan = fabricVsan;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Fabricsancloud getFabricsancloud() {
		return this.fabricsancloud;
	}

	public void setFabricsancloud(Fabricsancloud fabricsancloud) {
		this.fabricsancloud = fabricsancloud;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUplinkTrunking() {
		return this.uplinkTrunking;
	}

	public void setUplinkTrunking(String uplinkTrunking) {
		this.uplinkTrunking = uplinkTrunking;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Fabricfcsanep> getFabricFcSanEp() {
		return fabricFcSanEp;
	}

	public void setFabricFcSanEp(List<Fabricfcsanep> fabricFcSanEp) {
		this.fabricFcSanEp = fabricFcSanEp;
	}

	public List<Fabricvsan> getFabricVsan() {
		return fabricVsan;
	}

	public void setFabricVsan(List<Fabricvsan> fabricVsan) {
		this.fabricVsan = fabricVsan;
	}


}
