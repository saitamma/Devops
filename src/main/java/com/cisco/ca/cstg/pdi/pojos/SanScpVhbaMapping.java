package com.cisco.ca.cstg.pdi.pojos;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "san_scp_vhba_mapping")
public class SanScpVhbaMapping implements java.io.Serializable {

	private static final long serialVersionUID = 5367892168074108555L;
	
	private Integer id;
	private SanVhba sanVhba;
	private SanConnectivityPolicy sanConnectivityPolicy;

	public SanScpVhbaMapping() {
	}
	
	public SanScpVhbaMapping(int id) {
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
	@JoinColumn(name = "vhba_id", nullable = false)
	public SanVhba getSanVhba() {
		return this.sanVhba;
	}

	public void setSanVhba(SanVhba sanVhba) {
		this.sanVhba = sanVhba;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "scp_id", nullable = false)
	public SanConnectivityPolicy getSanConnectivityPolicy() {
		return this.sanConnectivityPolicy;
	}

	public void setSanConnectivityPolicy(
			SanConnectivityPolicy sanConnectivityPolicy) {
		this.sanConnectivityPolicy = sanConnectivityPolicy;
	}

}
