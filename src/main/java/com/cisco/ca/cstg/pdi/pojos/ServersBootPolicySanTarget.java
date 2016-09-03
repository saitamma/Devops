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
@Table(name = "servers_boot_policy_san_target")
public class ServersBootPolicySanTarget implements java.io.Serializable {

	private static final long serialVersionUID = -2082707730867591786L;
	private Integer id;
	private ServersBootPolicy serversBootPolicy;
	private ServersBootPolicySan serversBootPolicySan;
	private String name;
	private String type;
	private Integer lunId;
	private String wwpnAddress;

	public ServersBootPolicySanTarget() {
	}

	public ServersBootPolicySanTarget(Integer id) {
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
	@JoinColumn(name = "boot_policy_id", nullable = false)
	public ServersBootPolicy getServersBootPolicy() {
		return this.serversBootPolicy;
	}

	public void setServersBootPolicy(ServersBootPolicy serversBootPolicy) {
		this.serversBootPolicy = serversBootPolicy;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "boot_policy_san_id", nullable = false)
	public ServersBootPolicySan getServersBootPolicySan() {
		return this.serversBootPolicySan;
	}

	public void setServersBootPolicySan(
			ServersBootPolicySan serversBootPolicySan) {
		this.serversBootPolicySan = serversBootPolicySan;
	}

	@Column(name = "name", length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "target_type", length = 45)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "lun_id")
	public Integer getLunId() {
		return this.lunId;
	}

	public void setLunId(Integer lunId) {
		this.lunId = lunId;
	}

	@Column(name = "wwpn_address", length = 45)
	public String getWwpnAddress() {
		return this.wwpnAddress;
	}

	public void setWwpnAddress(String wwpnAddress) {
		this.wwpnAddress = wwpnAddress;
	}

}
