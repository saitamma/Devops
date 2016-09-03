package com.cisco.ca.cstg.pdi.pojos;


import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "admin_callhome_profile")
public class AdminCallhomeProfile implements java.io.Serializable {

	private static final long serialVersionUID = -8292440265495777139L;
	private Integer id;
	private ProjectDetails projectDetails;
	private String name;
	private String description;
	private String level;
	private String format;
	private Integer maxMsgSize;
	private List<AdminCallhomeProfileAlertGroupMapping> adminCallhomeProfileAlertGroupMappings = new ArrayList<AdminCallhomeProfileAlertGroupMapping>();
	private List<AdminCallhomeProfileRecipientMapping> adminCallhomeProfileRecipientMappings = new ArrayList<AdminCallhomeProfileRecipientMapping>();

	public AdminCallhomeProfile() {
	}

	public AdminCallhomeProfile(Integer id) {
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

	@Column(name = "name", length = 16)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "level", length = 45)
	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Column(name = "format", length = 25)
	public String getFormat() {
		return this.format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	@Column(name = "max_msg_size")
	public Integer getMaxMsgSize() {
		return this.maxMsgSize;
	}

	public void setMaxMsgSize(Integer maxMsgSize) {
		this.maxMsgSize = maxMsgSize;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "adminCallhomeProfile")
	@Fetch(FetchMode.SELECT)
	public List<AdminCallhomeProfileAlertGroupMapping> getAdminCallhomeProfileAlertGroupMappings() {
		return this.adminCallhomeProfileAlertGroupMappings;
	}

	public void setAdminCallhomeProfileAlertGroupMappings(
			List<AdminCallhomeProfileAlertGroupMapping> adminCallhomeProfileAlertGroupMappings) {
		this.adminCallhomeProfileAlertGroupMappings = adminCallhomeProfileAlertGroupMappings;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "adminCallhomeProfile")
	@Fetch(FetchMode.SELECT)
	public List<AdminCallhomeProfileRecipientMapping> getAdminCallhomeProfileRecipientMappings() {
		return this.adminCallhomeProfileRecipientMappings;
	}

	public void setAdminCallhomeProfileRecipientMappings(
			List<AdminCallhomeProfileRecipientMapping> adminCallhomeProfileRecipientMappings) {
		this.adminCallhomeProfileRecipientMappings = adminCallhomeProfileRecipientMappings;
	}
}
