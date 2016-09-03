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
@Table(name = "admin_callhome_system_inventory")
public class AdminCallhomeSystemInventory implements java.io.Serializable {

	private static final long serialVersionUID = 4469926145175113658L;
	private Integer id;
	private ProjectDetails projectDetails;
	private String sendPeriodically;
	private Integer sendIntervalDays;
	private Integer sendIntervalHours;
	private Integer sendIntervalMinutes;

	public AdminCallhomeSystemInventory() {
	}
	
	public AdminCallhomeSystemInventory(Integer id) {
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

	@Column(name = "send_periodically", length = 16)
	public String getSendPeriodically() {
		return this.sendPeriodically;
	}

	public void setSendPeriodically(String sendPeriodically) {
		this.sendPeriodically = sendPeriodically;
	}

	@Column(name = "send_interval_days")
	public Integer getSendIntervalDays() {
		return this.sendIntervalDays;
	}

	public void setSendIntervalDays(Integer sendIntervalDays) {
		this.sendIntervalDays = sendIntervalDays;
	}

	@Column(name = "send_interval_hours")
	public Integer getSendIntervalHours() {
		return this.sendIntervalHours;
	}

	public void setSendIntervalHours(Integer sendIntervalHours) {
		this.sendIntervalHours = sendIntervalHours;
	}

	@Column(name = "send_interval_minutes")
	public Integer getSendIntervalMinutes() {
		return this.sendIntervalMinutes;
	}

	public void setSendIntervalMinutes(Integer sendIntervalMinutes) {
		this.sendIntervalMinutes = sendIntervalMinutes;
	}

}
