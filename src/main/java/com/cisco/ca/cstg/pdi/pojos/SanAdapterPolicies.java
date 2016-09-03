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

@Entity
@Table(name = "san_adapter_policies")
public class SanAdapterPolicies implements java.io.Serializable {

	private static final long serialVersionUID = 6859775770845165753L;
	
	private Integer id;
	private ProjectDetails projectDetails;
	private Organizations organizations;
	private String name;
	private String description;
	private Integer transmitQueues;
	private Integer transmitQueuesRingSize;
	private Integer receiveQueues;
	private Integer receiveQueuesRingSize;
	private Integer scsiIoQueues;
	private Integer scsiIoQueuesRingSize;
	private String fcpErrorRecovery;
	private Integer flogiRetries;
	private Integer flogiTimeout;
	private Integer plogiRetries;
	private Integer plogiTimeout;
	private Integer portDownTimeout;
	private Integer portDownIoRetry;
	private Integer linkDownTimeout;
	private Integer ioThrottleCount;
	private Integer lunsPerTarget;
	private String interruptMode;
	private Boolean sapDefault;
	private List<SanVhba> sanVhbas = new ArrayList<SanVhba>();

	public SanAdapterPolicies() {
	}
	
	public SanAdapterPolicies(int id) {
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
	@JoinColumn(name = "organization")
	public Organizations getOrganizations() {
		return this.organizations;
	}

	public void setOrganizations(Organizations organizations) {
		this.organizations = organizations;
	}

	@Column(name = "name", length = 16)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 256)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "transmit_queues")
	public Integer getTransmitQueues() {
		return this.transmitQueues;
	}

	public void setTransmitQueues(Integer transmitQueues) {
		this.transmitQueues = transmitQueues;
	}

	@Column(name = "transmit_queues_ring_size")
	public Integer getTransmitQueuesRingSize() {
		return this.transmitQueuesRingSize;
	}

	public void setTransmitQueuesRingSize(Integer transmitQueuesRingSize) {
		this.transmitQueuesRingSize = transmitQueuesRingSize;
	}

	@Column(name = "receive_queues")
	public Integer getReceiveQueues() {
		return this.receiveQueues;
	}

	public void setReceiveQueues(Integer receiveQueues) {
		this.receiveQueues = receiveQueues;
	}

	@Column(name = "receive_queues_ring_size")
	public Integer getReceiveQueuesRingSize() {
		return this.receiveQueuesRingSize;
	}

	public void setReceiveQueuesRingSize(Integer receiveQueuesRingSize) {
		this.receiveQueuesRingSize = receiveQueuesRingSize;
	}

	@Column(name = "scsi_io_queues")
	public Integer getScsiIoQueues() {
		return this.scsiIoQueues;
	}

	public void setScsiIoQueues(Integer scsiIoQueues) {
		this.scsiIoQueues = scsiIoQueues;
	}

	@Column(name = "scsi_io_queues_ring_size")
	public Integer getScsiIoQueuesRingSize() {
		return this.scsiIoQueuesRingSize;
	}

	public void setScsiIoQueuesRingSize(Integer scsiIoQueuesRingSize) {
		this.scsiIoQueuesRingSize = scsiIoQueuesRingSize;
	}

	@Column(name = "fcp_error_recovery", length = 45)
	public String getFcpErrorRecovery() {
		return this.fcpErrorRecovery;
	}

	public void setFcpErrorRecovery(String fcpErrorRecovery) {
		this.fcpErrorRecovery = fcpErrorRecovery;
	}

	@Column(name = "flogi_retries")
	public Integer getFlogiRetries() {
		return this.flogiRetries;
	}

	public void setFlogiRetries(Integer flogiRetries) {
		this.flogiRetries = flogiRetries;
	}

	@Column(name = "flogi_timeout")
	public Integer getFlogiTimeout() {
		return this.flogiTimeout;
	}

	public void setFlogiTimeout(Integer flogiTimeout) {
		this.flogiTimeout = flogiTimeout;
	}

	@Column(name = "plogi__retries")
	public Integer getPlogiRetries() {
		return this.plogiRetries;
	}

	public void setPlogiRetries(Integer plogiRetries) {
		this.plogiRetries = plogiRetries;
	}

	@Column(name = "plogi_timeout")
	public Integer getPlogiTimeout() {
		return this.plogiTimeout;
	}

	public void setPlogiTimeout(Integer plogiTimeout) {
		this.plogiTimeout = plogiTimeout;
	}

	@Column(name = "port_down_timeout")
	public Integer getPortDownTimeout() {
		return this.portDownTimeout;
	}

	public void setPortDownTimeout(Integer portDownTimeout) {
		this.portDownTimeout = portDownTimeout;
	}

	@Column(name = "port_down_io_retry")
	public Integer getPortDownIoRetry() {
		return this.portDownIoRetry;
	}

	public void setPortDownIoRetry(Integer portDownIoRetry) {
		this.portDownIoRetry = portDownIoRetry;
	}

	@Column(name = "link_down_timeout")
	public Integer getLinkDownTimeout() {
		return this.linkDownTimeout;
	}

	public void setLinkDownTimeout(Integer linkDownTimeout) {
		this.linkDownTimeout = linkDownTimeout;
	}

	@Column(name = "io_throttle_count")
	public Integer getIoThrottleCount() {
		return this.ioThrottleCount;
	}

	public void setIoThrottleCount(Integer ioThrottleCount) {
		this.ioThrottleCount = ioThrottleCount;
	}

	@Column(name = "luns_per_target")
	public Integer getLunsPerTarget() {
		return this.lunsPerTarget;
	}

	public void setLunsPerTarget(Integer lunsPerTarget) {
		this.lunsPerTarget = lunsPerTarget;
	}

	@Column(name = "interrupt_mode", length = 45)
	public String getInterruptMode() {
		return this.interruptMode;
	}

	public void setInterruptMode(String interruptMode) {
		this.interruptMode = interruptMode;
	}

	@Column(name = "sap_default")
	public Boolean getSapDefault() {
		return this.sapDefault;
	}

	public void setSapDefault(Boolean sapDefault) {
		this.sapDefault = sapDefault;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sanAdapterPolicies")
	public List<SanVhba> getSanVhbas() {
		return this.sanVhbas;
	}

	public void setSanVhbas(List<SanVhba> sanVhbas) {
		this.sanVhbas = sanVhbas;
	}

}
