package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fabricVCon")
public class Fabricvcon implements java.io.Serializable {

	private static final long serialVersionUID = -1200739099901618051L;
	private int primaryKey;
	private Lsserver lsserver;
	
	@XmlAttribute
	private String transport;
	
	@XmlAttribute
	private String share;
	
	@XmlAttribute
	private String select;
	
	@XmlAttribute
	private String placement;
	
	@XmlAttribute
	private String instType;
	
	@XmlAttribute
	private String id;
	
	@XmlAttribute
	private String fabric;

	public Fabricvcon() {
	}

	public Fabricvcon(int primaryKey, Lsserver lsserver) {
		this.primaryKey = primaryKey;
		this.lsserver = lsserver;
	}

	public Fabricvcon(int primaryKey, Lsserver lsserver, String transport,
			String share, String select, String placement, String instType,
			String id, String fabric) {
		this.primaryKey = primaryKey;
		this.lsserver = lsserver;
		this.transport = transport;
		this.share = share;
		this.select = select;
		this.placement = placement;
		this.instType = instType;
		this.id = id;
		this.fabric = fabric;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Lsserver getLsserver() {
		return this.lsserver;
	}

	public void setLsserver(Lsserver lsserver) {
		this.lsserver = lsserver;
	}

	public String getTransport() {
		return this.transport;
	}

	public void setTransport(String transport) {
		this.transport = transport;
	}

	public String getShare() {
		return this.share;
	}

	public void setShare(String share) {
		this.share = share;
	}

	public String getSelect() {
		return this.select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public String getPlacement() {
		return this.placement;
	}

	public void setPlacement(String placement) {
		this.placement = placement;
	}

	public String getInstType() {
		return this.instType;
	}

	public void setInstType(String instType) {
		this.instType = instType;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFabric() {
		return this.fabric;
	}

	public void setFabric(String fabric) {
		this.fabric = fabric;
	}

}
