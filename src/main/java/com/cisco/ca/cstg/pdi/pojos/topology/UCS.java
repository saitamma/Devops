package com.cisco.ca.cstg.pdi.pojos.topology;

import java.util.HashSet;
import java.util.Set;

import com.cisco.ca.cstg.pdi.exceptions.TopologyException;

public class UCS extends Device {
	private final int ucsNum;
	protected final Set<Link> links = new HashSet<Link>();

	public UCS(Pod pod, int ucsNum, String model, String name) {
		super(pod, "ucs" + ucsNum, model, name);
		this.ucsNum = ucsNum;
	}

	public boolean addLink(Link link) throws TopologyException {		
		return links.add(link);
	}

	public void removeLink(Link link) {
		links.remove(link);
	}

	public int getUcsNum() {
		return ucsNum;
	}

	public Set<Link> getLinks() {
		return links;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;}
		if (obj == null){
			return false;}
		if (!(obj instanceof Device)){
			return false;}
		UCS other = (UCS) obj;
		if (key == null) {
			if (other.key != null){
				return false;}
		} else if (!key.equals(other.key)){
			return false;}
		return true;
	}
}
