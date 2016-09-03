package com.cisco.ca.cstg.pdi.pojos.topology;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TopologyJSONGenerator {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(TopologyJSONGenerator.class);

	@JsonIgnore
	private final static String UNDERSCORE = "_";

	@JsonIgnore
	private Pod pod;

	private final Map<String, String> chassis_pid = new HashMap<String, String>();

	private final Map<String, String> device_name = new HashMap<String, String>();

	private final Map<String, String> current_pod = new HashMap<String, String>();

	private final Map<String, String> interconnect_links = new HashMap<String, String>();

	public TopologyJSONGenerator() {
		super();
	}

	/**
	 * @return the pod
	 */
	public Pod getPod() {
		return pod;
	}

	/**
	 * @param pod
	 *            the pod to set
	 */
	public void setPod(Pod pod) {
		this.pod = pod;
	}

	public void generateData() {
		this.populateDeviceDetails();
		this.populateCurrentPod(pod.getPodNum());
		this.populateInterconnectLinks();
	}

	private void populateDeviceDetails() {
		this.addFIDetails();
		this.addUcsDeviceDetails();
	}

	private void addFIDetails() {
		Fint fint1 = pod.getFints().getFint1();
		chassis_pid.put(concatenate(UNDERSCORE, pod.key, fint1.key),
				fint1.getModel());
		device_name.put(concatenate(UNDERSCORE, pod.key, fint1.key),
				fint1.getName());
		Fint fint2 = pod.getFints().getFint2();
		chassis_pid.put(concatenate(UNDERSCORE, pod.key, fint2.key),
				fint2.getModel());
		device_name.put(concatenate(UNDERSCORE, pod.key, fint2.key),
				fint2.getName());
	}

	private void addUcsDeviceDetails() {
		for (Entry<String, UCS> entry : pod.getUcss().entrySet()) {
			UCS ucs = entry.getValue();
			chassis_pid.put(concatenate(UNDERSCORE, pod.key, ucs.key),
					ucs.getModel());
			device_name.put(concatenate(UNDERSCORE, pod.key, ucs.key),
					ucs.getName());
		}
	}

	private void populateCurrentPod(long podNum) {
		current_pod.put("pod_1_connectivity", "ssvpc");
		current_pod.put("pod_1_deploy_type", "deploy");

		String podCount = current_pod.get("pod_count");
		if (podCount == null) {
			current_pod.put("pod_count", "1");
		} else {
			podCount = Integer.toString(Integer.valueOf(podCount) + 1);
			current_pod.put("pod_count", podCount);
		}

		String podIds = current_pod.get("pod_id");
		if (podIds == null) {
			current_pod.put("pod_id", Long.toString(podNum));
		} else {
			podIds = podIds + "," + Long.toString(podNum);
			current_pod.put("pod_id", podIds);
		}

		current_pod.put("tool_type", "ucs");
	}

	private void populateInterconnectLinks() {
		for (Entry<String, List<Link>> entry : pod.getLinks().entrySet()) {
			String value = createPortRange(entry.getValue());
			interconnect_links.put(entry.getKey(), value);
		}

	}

	private String createPortRange(List<Link> links) {
		String value = "";
		Collections.sort(links, new LinkComp());
		StringBuilder leftSideStringBuilder = new StringBuilder();
		StringBuilder rightSideStringBuilder = new StringBuilder();
		boolean addedToStringBuilder = false;
		for (Link link : links) {
			if (addedToStringBuilder) {
				leftSideStringBuilder.append(",");
				rightSideStringBuilder.append(",");
			}
			leftSideStringBuilder.append(link.getFromPort());
			rightSideStringBuilder.append(link.getToPort());
			addedToStringBuilder = true;

		}
		value = leftSideStringBuilder + ":" + rightSideStringBuilder;
		return value;
	}

	private String concatenate(String delemiter, String... keys) {
		StringBuilder key = new StringBuilder();
		boolean add = true;
		for (String str : keys) {
			if (add) {
				key.append(str);
				add = false;
			} else {
				key.append(delemiter);
				key.append(str);
			}
		}

		return key.toString();
	}

	public Map<String, String> getChassis_pid() {
		return chassis_pid;
	}

	public Map<String, String> getDevice_name() {
		return device_name;
	}

	public Map<String, String> getCurrent_pod() {
		return current_pod;
	}

	public Map<String, String> getInterconnect_links() {
		return interconnect_links;
	}

	@Override
	public String toString() {
		return "TopologyDataGenerator [chassis_pid=" + chassis_pid
				+ ", device_name=" + device_name + ", current_pod="
				+ current_pod + ", interconnect_links=" + interconnect_links
				+ "]";
	}

	public String toJson() {
		String json = "";
		ObjectMapper mapper = new ObjectMapper();

		try {
			return mapper.writeValueAsString(this);
		} catch (JsonGenerationException e) {
			LOGGER.error("Exception occured in toJson method,", e);
		} catch (JsonMappingException e) {
			LOGGER.error("Exception occured in toJson method,", e);
		} catch (IOException e) {
			LOGGER.error("Exception occured in toJson method,", e);
		}
		return json;
	}

}
