package com.cisco.ca.cstg.pdi.pojos.topology;

import java.util.HashSet;
import java.util.Set;

public class Switch extends Device {

	private final String type;
	protected final Set<Link> links = new HashSet<Link>();
	protected final String ip;

	public Switch(Pod pod, String typeKey, String type, String model,
			String name, String ip) {
		super(pod, typeKey + type, model, name);
		this.type = type;
		this.ip = ip;
	}

	public String getType() {
		return type;
	}

	public boolean addLink(Link link) {
		return links.add(link);
	}

	public Link[] getLinks() {
		return links.toArray(new Link[links.size()]);
	}

	public String getIp() {
		return ip;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;}
		if (obj == null){
			return false;}
		if (getClass() != obj.getClass()){
			return false;}
		Switch other = (Switch) obj;
		if (ip == null) {
			if (other.ip != null){
				return false;}
		} else if (!ip.equals(other.ip)){
			return false;}
		return true;
	}

}
