package com.cisco.ca.cstg.pdi.pojos.topology;

import com.cisco.ca.cstg.pdi.exceptions.PojoCreationException;

public class MdsSwitches {
	private final Pod pod;
	public final String key;
	private final Switch switchA;
	private final Switch switchB;

	public MdsSwitches(Pod pod, String key, String name, String model)
			throws PojoCreationException {
		this.pod = pod;
		this.key = key;
		this.switchA = new Switch(pod, key, "1", model, name, "");
		this.switchB = new Switch(pod, key, "2", model, name, "");
	}

	public Pod getPod() {
		return pod;
	}

	public String getKey() {
		return key;
	}

	public Switch getSwitchA() {
		return switchA;
	}

	public Switch getSwitchB() {
		return switchB;
	}

}
