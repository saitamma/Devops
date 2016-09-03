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
@Table(name = "servers_spq_slot_mapping")
public class ServersSPQSlotMapping implements java.io.Serializable {

	private static final long serialVersionUID = -4588654926769166892L;
	
	private Integer id;
	private ServersServerPoolQualifier serversServerPoolQualifier;
	private Integer minId;
	private Integer maxId;

	public ServersSPQSlotMapping() {
	}
	
	public ServersSPQSlotMapping(int id) {
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "spq_id", nullable = false)
	public ServersServerPoolQualifier getServersServerPoolQualifier() {
		return this.serversServerPoolQualifier;
	}

	public void setServersServerPoolQualifier(
			ServersServerPoolQualifier serversServerPoolQualifier) {
		this.serversServerPoolQualifier = serversServerPoolQualifier;
	}

	@Column(name = "min_id")
	public Integer getMinId() {
		return this.minId;
	}

	public void setMinId(Integer minId) {
		this.minId = minId;
	}

	@Column(name = "max_id")
	public Integer getMaxId() {
		return this.maxId;
	}

	public void setMaxId(Integer maxId) {
		this.maxId = maxId;
	}

}
