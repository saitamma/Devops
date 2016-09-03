package com.cisco.ca.cstg.pdi.pojos.topology;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NetworkSwitches {

	private final Pod pod;
	private final String key;
	private final Map<String, Switch> swithes;
	private static char add = 'a';
	private static final String pid = "nexus";

	public NetworkSwitches(Pod pod) {
		this.pod = pod;
		this.key = "agg1";
		this.swithes = new HashMap<String, Switch>();
	}

	public static void incrementAddChar() {
		add++;
	}

	public boolean addNetworkSwitch(String model, String ip) {
		if (swithes.containsKey(ip)) {
			return false;
		}
		int size = swithes.size();
		if (size != 0) {
			incrementAddChar();
		}
		size++;
		Switch sw = new Switch(pod, key, "" + add, model,
				pid + "_" + size, ip);
		swithes.put(ip, sw);
		return true;
	}

	public Switch getSwitchByIp(String ip) {
		return swithes.get(ip);
	}

	public List<Switch> getListOfSwitches() {
		return new ArrayList<Switch>(swithes.values());
	}

	public Pod getPod() {
		return pod;
	}

	public String getKey() {
		return key;
	}

}
