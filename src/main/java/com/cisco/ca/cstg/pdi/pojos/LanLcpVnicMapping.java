package com.cisco.ca.cstg.pdi.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * LanLcpVnicMapping generated by hbm2java
 */
@Entity
@Table(name = "lan_lcp_vnic_mapping")
public class LanLcpVnicMapping implements java.io.Serializable {
	
	private static final long serialVersionUID = -4976928105967114529L;
	private Integer id;
	private LanVnic lanVnic;
	private LanConnectivityPolicy lanConnectivityPolicy;

	public LanLcpVnicMapping() {
	}
	public LanLcpVnicMapping(LanVnic lanVnic,LanConnectivityPolicy lanConnectivityPolicy) {
		this.lanVnic = lanVnic;
		this.lanConnectivityPolicy = lanConnectivityPolicy;
	}
	public LanLcpVnicMapping(Integer id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "vnic_id", nullable = false)
	public LanVnic getLanVnic() {
		return this.lanVnic;
	}

	public void setLanVnic(LanVnic lanVnic) {
		this.lanVnic = lanVnic;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lcp_id", nullable = false)
	public LanConnectivityPolicy getLanConnectivityPolicy() {
		return this.lanConnectivityPolicy;
	}

	public void setLanConnectivityPolicy(
			LanConnectivityPolicy lanConnectivityPolicy) {
		this.lanConnectivityPolicy = lanConnectivityPolicy;
	}

}
