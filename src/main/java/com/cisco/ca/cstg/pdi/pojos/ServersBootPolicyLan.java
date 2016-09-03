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
@Table(name = "servers_boot_policy_lan")
public class ServersBootPolicyLan implements java.io.Serializable {

	private static final long serialVersionUID = -203251667599825477L;
	
	private Integer id;
	private LanVnic lanVnic;
	private ProjectDetails projectDetails;
	private ServersBootPolicy serversBootPolicy;
	private String name;
	private String description;
	private Integer order;
	private String type;

	public ServersBootPolicyLan() {
	}
	
	public ServersBootPolicyLan(Integer id) {		
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
	@Fetch(value = FetchMode.SELECT)
	public LanVnic getLanVnic() {
		return this.lanVnic;
	}

	public void setLanVnic(LanVnic lanVnic) {
		this.lanVnic = lanVnic;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	public ProjectDetails getProjectDetails() {
		return this.projectDetails;
	}

	public void setProjectDetails(ProjectDetails projectDetails) {
		this.projectDetails = projectDetails;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "boot_policy_id", nullable = false)
	public ServersBootPolicy getServersBootPolicy() {
		return this.serversBootPolicy;
	}

	public void setServersBootPolicy(ServersBootPolicy serversBootPolicy) {
		this.serversBootPolicy = serversBootPolicy;
	}

	@Column(name = "name", length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 45)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "lan_order")
	public Integer getOrder() {
		return this.order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	@Column(name = "boot_policy_type", length = 45)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
		
}
