package com.cisco.ca.cstg.pdi.pojos;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * LanEthernetAdapterPolicies generated by hbm2java
 */
@Entity
@Table(name = "lan_ethernet_adapter_policies")
public class LanEthernetAdapterPolicies implements java.io.Serializable {

	private static final long serialVersionUID = 4044959939406098543L;
	private Integer id;
	private ProjectDetails projectDetails;
	private Organizations organizations;
	private String name;
	private String description;
	private Integer transmitQueues;
	private Integer transmitQueuesRingSize;
	private Integer receiveQueues;
	private Integer receiveQueuesRingSize;
	private Integer completionQueues;
	private Integer completionQueuesInterrupts;
	private String transmitChecksumOffload;
	private String receiveChecksumOffload;
	private String tcpSegmentationOffload;
	private String tcpLargeReceiveOffload;
	private String receiveSideScaling;
	private String acceleratedReceiveFlowSteering;
	private Integer failbackTimeout;
	private String interruptMode;
	private String interruptCoalescingType;
	private Integer interruptTimer;
	private Boolean leapDefault;
	private List<LanVnic> lanVnics = new ArrayList<LanVnic>(0);

	public LanEthernetAdapterPolicies() {
	}
	
	public LanEthernetAdapterPolicies(Integer id) {
		this.id = id;
	}

	public LanEthernetAdapterPolicies(ProjectDetails projectDetails,
			Organizations organizations, String name, String description,
			Integer transmitQueues, Integer transmitQueuesRingSize,
			Integer receiveQueues, Integer receiveQueuesRingSize,
			Integer completionQueues, Integer completionQueuesInterrupts,
			String transmitChecksumOffload, String receiveChecksumOffload,
			String tcpSegmentationOffload, String tcpLargeReceiveOffload,
			String receiveSideScaling, String acceleratedReceiveFlowSteering,
			Integer failbackTimeout, String interruptMode,
			String interruptCoalescingType, Integer interruptTimer,
			Boolean leapDefault, List<LanVnic> lanVnics) {
		this.projectDetails = projectDetails;
		this.organizations = organizations;
		this.name = name;
		this.description = description;
		this.transmitQueues = transmitQueues;
		this.transmitQueuesRingSize = transmitQueuesRingSize;
		this.receiveQueues = receiveQueues;
		this.receiveQueuesRingSize = receiveQueuesRingSize;
		this.completionQueues = completionQueues;
		this.completionQueuesInterrupts = completionQueuesInterrupts;
		this.transmitChecksumOffload = transmitChecksumOffload;
		this.receiveChecksumOffload = receiveChecksumOffload;
		this.tcpSegmentationOffload = tcpSegmentationOffload;
		this.tcpLargeReceiveOffload = tcpLargeReceiveOffload;
		this.receiveSideScaling = receiveSideScaling;
		this.acceleratedReceiveFlowSteering = acceleratedReceiveFlowSteering;
		this.failbackTimeout = failbackTimeout;
		this.interruptMode = interruptMode;
		this.interruptCoalescingType = interruptCoalescingType;
		this.interruptTimer = interruptTimer;
		this.leapDefault = leapDefault;
		this.lanVnics = lanVnics;
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

	@Column(name = "completion_queues")
	public Integer getCompletionQueues() {
		return this.completionQueues;
	}

	public void setCompletionQueues(Integer completionQueues) {
		this.completionQueues = completionQueues;
	}

	@Column(name = "completion_queues_interrupts")
	public Integer getCompletionQueuesInterrupts() {
		return this.completionQueuesInterrupts;
	}

	public void setCompletionQueuesInterrupts(Integer completionQueuesInterrupts) {
		this.completionQueuesInterrupts = completionQueuesInterrupts;
	}

	@Column(name = "transmit_checksum_offload", length = 16)
	public String getTransmitChecksumOffload() {
		return this.transmitChecksumOffload;
	}

	public void setTransmitChecksumOffload(String transmitChecksumOffload) {
		this.transmitChecksumOffload = transmitChecksumOffload;
	}

	@Column(name = "receive_checksum_offload", length = 16)
	public String getReceiveChecksumOffload() {
		return this.receiveChecksumOffload;
	}

	public void setReceiveChecksumOffload(String receiveChecksumOffload) {
		this.receiveChecksumOffload = receiveChecksumOffload;
	}

	@Column(name = "tcp_segmentation_offload", length = 16)
	public String getTcpSegmentationOffload() {
		return this.tcpSegmentationOffload;
	}

	public void setTcpSegmentationOffload(String tcpSegmentationOffload) {
		this.tcpSegmentationOffload = tcpSegmentationOffload;
	}

	@Column(name = "tcp_large_receive_offload", length = 16)
	public String getTcpLargeReceiveOffload() {
		return this.tcpLargeReceiveOffload;
	}

	public void setTcpLargeReceiveOffload(String tcpLargeReceiveOffload) {
		this.tcpLargeReceiveOffload = tcpLargeReceiveOffload;
	}

	@Column(name = "receive_side_scaling", length = 16)
	public String getReceiveSideScaling() {
		return this.receiveSideScaling;
	}

	public void setReceiveSideScaling(String receiveSideScaling) {
		this.receiveSideScaling = receiveSideScaling;
	}

	@Column(name = "accelerated_receive_flow_steering", length = 16)
	public String getAcceleratedReceiveFlowSteering() {
		return this.acceleratedReceiveFlowSteering;
	}

	public void setAcceleratedReceiveFlowSteering(
			String acceleratedReceiveFlowSteering) {
		this.acceleratedReceiveFlowSteering = acceleratedReceiveFlowSteering;
	}

	@Column(name = "failback_timeout")
	public Integer getFailbackTimeout() {
		return this.failbackTimeout;
	}

	public void setFailbackTimeout(Integer failbackTimeout) {
		this.failbackTimeout = failbackTimeout;
	}

	@Column(name = "interrupt_mode", length = 16)
	public String getInterruptMode() {
		return this.interruptMode;
	}

	public void setInterruptMode(String interruptMode) {
		this.interruptMode = interruptMode;
	}

	@Column(name = "interrupt_coalescing_type", length = 16)
	public String getInterruptCoalescingType() {
		return this.interruptCoalescingType;
	}

	public void setInterruptCoalescingType(String interruptCoalescingType) {
		this.interruptCoalescingType = interruptCoalescingType;
	}

	@Column(name = "interrupt_timer")
	public Integer getInterruptTimer() {
		return this.interruptTimer;
	}

	public void setInterruptTimer(Integer interruptTimer) {
		this.interruptTimer = interruptTimer;
	}

	@Column(name = "leap_default")
	public Boolean getLeapDefault() {
		return this.leapDefault;
	}

	public void setLeapDefault(Boolean leapDefault) {
		this.leapDefault = leapDefault;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "lanEthernetAdapterPolicies")
	public List<LanVnic> getLanVnics() {
		return this.lanVnics;
	}

	public void setLanVnics(List<LanVnic> lanVnics) {
		this.lanVnics = lanVnics;
	}

}
