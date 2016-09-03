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
@Table(name = "admin_callhome_profile_alert_group_mapping")
public class AdminCallhomeProfileAlertGroupMapping implements
		java.io.Serializable {

	private static final long serialVersionUID = 7107297995636636565L;
	private Integer id;
	private AdminCallhomeAlertGroup adminCallhomeAlertGroup;
	private AdminCallhomeProfile adminCallhomeProfile;

	public AdminCallhomeProfileAlertGroupMapping() {
	}

	public AdminCallhomeProfileAlertGroupMapping(Integer id) {
		this.id = id;
	}

	public AdminCallhomeProfileAlertGroupMapping(Integer id,
			AdminCallhomeAlertGroup adminCallhomeAlertGroup,
			AdminCallhomeProfile adminCallhomeProfile) {
		this.id = id;
		this.adminCallhomeAlertGroup = adminCallhomeAlertGroup;
		this.adminCallhomeProfile = adminCallhomeProfile;
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
	@JoinColumn(name = "alert_group_id")
	public AdminCallhomeAlertGroup getAdminCallhomeAlertGroup() {
		return this.adminCallhomeAlertGroup;
	}

	public void setAdminCallhomeAlertGroup(
			AdminCallhomeAlertGroup adminCallhomeAlertGroup) {
		this.adminCallhomeAlertGroup = adminCallhomeAlertGroup;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profile_id")
	public AdminCallhomeProfile getAdminCallhomeProfile() {
		return this.adminCallhomeProfile;
	}

	public void setAdminCallhomeProfile(
			AdminCallhomeProfile adminCallhomeProfile) {
		this.adminCallhomeProfile = adminCallhomeProfile;
	}

}
