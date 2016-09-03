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
@Table(name = "servers_boot_policy_local_disc")
public class ServersBootPolicyLocalDisc implements java.io.Serializable {

	private static final long serialVersionUID = -4284829978436898431L;
	
	private Integer id;
	private ProjectDetails projectDetails;
	private ServersBootPolicy serversBootPolicy;
	private String device;
	private Integer bootOrder;

	public ServersBootPolicyLocalDisc() {
	}

	
	public ServersBootPolicyLocalDisc(Integer id) {
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

	@Column(name = "device", length = 45)
	public String getDevice() {
		return this.device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	@Column(name = "boot_order")
	public Integer getBootOrder() {
		return this.bootOrder;
	}

	public void setBootOrder(Integer bootOrder) {
		this.bootOrder = bootOrder;
	}

}
