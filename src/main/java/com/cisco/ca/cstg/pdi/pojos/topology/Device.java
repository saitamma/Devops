package com.cisco.ca.cstg.pdi.pojos.topology;

public class Device {

	protected final Pod pod;
	public final String key;
	protected final String name;
	private final String model;

	public Device(Pod pod, String key, String model, String name) {
		super();
		this.pod = pod;
		this.key = key;
		this.model = model;
		this.name = name;
	}

	public Pod getPod() {
		return pod;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Device))
			return false;
		Device other = (Device) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}

	public String getModel() {
		return model;
	}

	public String getName() {
		return name;
	}
}