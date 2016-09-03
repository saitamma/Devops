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
@Table(name = "lan_vnict_vlan_mapping")
public class LanVnicTemplateVlanMapping implements java.io.Serializable {

	private static final long serialVersionUID = 7592395936161203932L;
	private Integer id;
	private LanVlan lanVlan;
	private LanVnicTemplate lanVnicTemplate;

	public LanVnicTemplateVlanMapping() {
	}

	public LanVnicTemplateVlanMapping(Integer id) {		
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
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name = "vlan_id")
	public LanVlan getLanVlan() {
		return this.lanVlan;
	}

	public void setLanVlan(LanVlan lanVlan) {
		this.lanVlan = lanVlan;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vnict_id")
	public LanVnicTemplate getLanVnicTemplate() {
		return this.lanVnicTemplate;
	}

	public void setLanVnicTemplate(LanVnicTemplate lanVnicTemplate) {
		this.lanVnicTemplate = lanVnicTemplate;
	}

}
