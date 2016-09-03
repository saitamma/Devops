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
@Table(name = "admin_radius_group_provider_mapping")
public class AdminRadiusGroupProviderMapping implements java.io.Serializable {

	private static final long serialVersionUID = -2180529665175474631L;
	private Integer id;
	private AdminRadiusProviderGroup adminRadiusProviderGroup;
	private AdminRadiusProvider adminRadiusProvider;
	private Integer radiusProviderOrder;

	public AdminRadiusGroupProviderMapping() {
	}

	public AdminRadiusGroupProviderMapping(Integer id) {
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
	@JoinColumn(name = "radius_group_id", nullable = false)
	public AdminRadiusProviderGroup getAdminRadiusProviderGroup() {
		return this.adminRadiusProviderGroup;
	}

	public void setAdminRadiusProviderGroup(
			AdminRadiusProviderGroup adminRadiusProviderGroup) {
		this.adminRadiusProviderGroup = adminRadiusProviderGroup;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "radius_provider_id", nullable = false)
	@Fetch(FetchMode.SELECT)
	public AdminRadiusProvider getAdminRadiusProvider() {
		return this.adminRadiusProvider;
	}

	public void setAdminRadiusProvider(AdminRadiusProvider adminRadiusProvider) {
		this.adminRadiusProvider = adminRadiusProvider;
	}

	@Column(name = "radius_provider_order")
	public Integer getRadiusProviderOrder() {
		return this.radiusProviderOrder;
	}

	public void setRadiusProviderOrder(Integer radiusProviderOrder) {
		this.radiusProviderOrder = radiusProviderOrder;
	}

}
