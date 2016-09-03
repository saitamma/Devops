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
@Table(name = "admin_tacacs_group_provider_mapping")
public class AdminTacacsGroupProviderMapping implements java.io.Serializable {

	private static final long serialVersionUID = 5464163231465559516L;
	private Integer id;
	private AdminTacacsProviderGroup adminTacacsProviderGroup;
	private AdminTacacsProvider adminTacacsProvider;
	private Integer providerOrder;

	public AdminTacacsGroupProviderMapping() {
	}

	public AdminTacacsGroupProviderMapping(Integer id) {
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
	@JoinColumn(name = "tacacs_group_id", nullable = false)
	public AdminTacacsProviderGroup getAdminTacacsProviderGroup() {
		return this.adminTacacsProviderGroup;
	}

	public void setAdminTacacsProviderGroup(
			AdminTacacsProviderGroup adminTacacsProviderGroup) {
		this.adminTacacsProviderGroup = adminTacacsProviderGroup;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tacacs_provider_id", nullable = false)
	@Fetch(FetchMode.SELECT)
	public AdminTacacsProvider getAdminTacacsProvider() {
		return this.adminTacacsProvider;
	}

	public void setAdminTacacsProvider(AdminTacacsProvider adminTacacsProvider) {
		this.adminTacacsProvider = adminTacacsProvider;
	}

	@Column(name = "provider_order")
	public Integer getProviderOrder() {
		return this.providerOrder;
	}

	public void setProviderOrder(Integer providerOrder) {
		this.providerOrder = providerOrder;
	}

}
