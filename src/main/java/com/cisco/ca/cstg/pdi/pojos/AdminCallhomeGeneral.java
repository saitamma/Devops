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
@Table(name = "admin_callhome_general")
public class AdminCallhomeGeneral implements java.io.Serializable {

	private static final long serialVersionUID = -7237729844872117067L;
	private Integer id;
	private ProjectDetails projectDetails;
	private String state;
	private String switchPriority;
	private String throttling;
	private String contact;
	private String phone;
	private String email;
	private String address;
	private String customerId;
	private String contractId;
	private String siteId;
	private String emailFrom;
	private String replyTo;
	private String host;
	private Integer port;

	public AdminCallhomeGeneral() {
	}
	
	public AdminCallhomeGeneral(Integer id) {
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

	@Column(name = "state", length = 16)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "switch_priority", length = 45)
	public String getSwitchPriority() {
		return this.switchPriority;
	}

	public void setSwitchPriority(String switchPriority) {
		this.switchPriority = switchPriority;
	}

	@Column(name = "throttling", length = 16)
	public String getThrottling() {
		return this.throttling;
	}

	public void setThrottling(String throttling) {
		this.throttling = throttling;
	}

	@Column(name = "contact")
	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Column(name = "phone", length = 16)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "email", length = 80)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "address")
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "customer_id", length = 510)
	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Column(name = "contract_id", length = 510)
	public String getContractId() {
		return this.contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	@Column(name = "site_id", length = 510)
	public String getSiteId() {
		return this.siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	@Column(name = "email_from", length = 80)
	public String getEmailFrom() {
		return this.emailFrom;
	}

	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	@Column(name = "reply_to", length = 80)
	public String getReplyTo() {
		return this.replyTo;
	}

	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}

	@Column(name = "host")
	public String getHost() {
		return this.host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	@Column(name = "port")
	public Integer getPort() {
		return this.port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

}
