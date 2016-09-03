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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
@Table(name = "servers_spt_vnic_mapping")
public class ServersSPTVnicMapping implements java.io.Serializable {

	private static final long serialVersionUID = 7543688281363263652L;
	
	private Integer id;
	private LanVnic lanVnic;
	private ServersServiceProfileTemplate serversServiceProfileTemplate;

	public ServersSPTVnicMapping() {
	}
	
	public ServersSPTVnicMapping(int id) {
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
	@JoinColumn(name = "vnic_id")
	@Fetch(FetchMode.SELECT)
	public LanVnic getLanVnic() {
		return this.lanVnic;
	}

	public void setLanVnic(LanVnic lanVnic) {
		this.lanVnic = lanVnic;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "spt_id", nullable = false)
	public ServersServiceProfileTemplate getServersServiceProfileTemplate() {
		return this.serversServiceProfileTemplate;
	}

	public void setServersServiceProfileTemplate(
			ServersServiceProfileTemplate serversServiceProfileTemplate) {
		this.serversServiceProfileTemplate = serversServiceProfileTemplate;
	}

}
