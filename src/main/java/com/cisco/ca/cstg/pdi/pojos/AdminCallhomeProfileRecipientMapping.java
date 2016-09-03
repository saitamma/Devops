package com.cisco.ca.cstg.pdi.pojos;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "admin_callhome_profile_recipient_mapping")
public class AdminCallhomeProfileRecipientMapping implements
		java.io.Serializable {

	private static final long serialVersionUID = 9156202926931285254L;
	private Integer id;
	private AdminCallhomeProfile adminCallhomeProfile;
	private String email;

	public AdminCallhomeProfileRecipientMapping() {
	}

	public AdminCallhomeProfileRecipientMapping(Integer id) {
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
	@JoinColumn(name = "profile_id")
	public AdminCallhomeProfile getAdminCallhomeProfile() {
		return this.adminCallhomeProfile;
	}

	public void setAdminCallhomeProfile(
			AdminCallhomeProfile adminCallhomeProfile) {
		this.adminCallhomeProfile = adminCallhomeProfile;
	}

	@Column(name = "email", length = 80)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
