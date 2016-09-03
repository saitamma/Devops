package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "callhomePeriodicSystemInventory")
public class Callhomeperiodicsysteminventory implements java.io.Serializable {

	private static final long serialVersionUID = 428106069858766286L;
	private int primaryKey;
	private Callhomeep callhomeEp;
	@XmlAttribute
	private String adminState;
	@XmlAttribute
	private String intervalDays;
	@XmlAttribute
	private String maximumRetryCount;
	@XmlAttribute
	private String minimumSendNowIntervalSeconds;
	@XmlAttribute
	private String pollIntervalSeconds;
	@XmlAttribute
	private String retryDelayMinutes;
	@XmlAttribute
	private String sendNow;
	@XmlAttribute
	private String timeOfDayHour;
	@XmlAttribute
	private String timeOfDayMinute;

	public Callhomeperiodicsysteminventory() {
	}

	public Callhomeperiodicsysteminventory(Callhomeep callhomeEp) {
		this.callhomeEp = callhomeEp;
	}

	public Callhomeperiodicsysteminventory(Callhomeep callhomeEp,
			String adminState, String intervalDays, String maximumRetryCount,
			String minimumSendNowIntervalSeconds, String pollIntervalSeconds,
			String retryDelayMinutes, String sendNow, String timeOfDayHour,
			String timeOfDayMinute) {
		this.callhomeEp = callhomeEp;
		this.adminState = adminState;
		this.intervalDays = intervalDays;
		this.maximumRetryCount = maximumRetryCount;
		this.minimumSendNowIntervalSeconds = minimumSendNowIntervalSeconds;
		this.pollIntervalSeconds = pollIntervalSeconds;
		this.retryDelayMinutes = retryDelayMinutes;
		this.sendNow = sendNow;
		this.timeOfDayHour = timeOfDayHour;
		this.timeOfDayMinute = timeOfDayMinute;
	}

	public int getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Callhomeep getCallhomeEp() {
		return callhomeEp;
	}

	public void setCallhomeEp(Callhomeep callhomeEp) {
		this.callhomeEp = callhomeEp;
	}

	public String getAdminState() {
		return this.adminState;
	}

	public void setAdminState(String adminState) {
		this.adminState = adminState;
	}

	public String getIntervalDays() {
		return this.intervalDays;
	}

	public void setIntervalDays(String intervalDays) {
		this.intervalDays = intervalDays;
	}

	public String getMaximumRetryCount() {
		return this.maximumRetryCount;
	}

	public void setMaximumRetryCount(String maximumRetryCount) {
		this.maximumRetryCount = maximumRetryCount;
	}

	public String getMinimumSendNowIntervalSeconds() {
		return this.minimumSendNowIntervalSeconds;
	}

	public void setMinimumSendNowIntervalSeconds(
			String minimumSendNowIntervalSeconds) {
		this.minimumSendNowIntervalSeconds = minimumSendNowIntervalSeconds;
	}

	public String getPollIntervalSeconds() {
		return this.pollIntervalSeconds;
	}

	public void setPollIntervalSeconds(String pollIntervalSeconds) {
		this.pollIntervalSeconds = pollIntervalSeconds;
	}

	public String getRetryDelayMinutes() {
		return this.retryDelayMinutes;
	}

	public void setRetryDelayMinutes(String retryDelayMinutes) {
		this.retryDelayMinutes = retryDelayMinutes;
	}

	public String getSendNow() {
		return this.sendNow;
	}

	public void setSendNow(String sendNow) {
		this.sendNow = sendNow;
	}

	public String getTimeOfDayHour() {
		return this.timeOfDayHour;
	}

	public void setTimeOfDayHour(String timeOfDayHour) {
		this.timeOfDayHour = timeOfDayHour;
	}

	public String getTimeOfDayMinute() {
		return this.timeOfDayMinute;
	}

	public void setTimeOfDayMinute(String timeOfDayMinute) {
		this.timeOfDayMinute = timeOfDayMinute;
	}

}
