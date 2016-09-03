package com.cisco.ca.cstg.pdi.pojos.topology;

import java.util.HashSet;
import java.util.Set;

import com.cisco.ca.cstg.pdi.exceptions.TopologyException;

public class Fint extends Device {
	private final int fintNum;
	private final long fintid;
	protected final Set<Link> links = new HashSet<Link>();
	protected final Set<Link> nSwitchlinks = new HashSet<Link>();
	protected final Set<Link> sSwitchlinks = new HashSet<Link>();

	public Fint(Pod pod, int fintNum, String model, String name, long fintid) {
		super(pod, "fint" + fintNum, model, name);
		this.fintNum = fintNum;
		this.fintid = fintid;
	}

	public boolean addLink(Link link) throws TopologyException {
		int size = links.size();
		size++;
		if (size > 96) {
			throw new TopologyException("Link can not be added to FI");
		}
		return links.add(link);
	}

	public boolean addNSwitchlinks(Link link) throws TopologyException {
		int size = links.size();
		size++;
		if (size > 96) {
			throw new TopologyException(
					"Network switch link can not be added to FI");
		}
		return nSwitchlinks.add(link);
	}

	public boolean addSSwitchlinks(Link link) throws TopologyException {
		int size = links.size();
		size++;
		if (size > 8) {
			throw new TopologyException(
					"Storage switch link can not be added to FI");
		}
		return sSwitchlinks.add(link);
	}

	public int getFintNum() {
		return fintNum;
	}

	public Link[] getLinks() {
		return links.toArray(new Link[links.size()]);
	}

	public Link[] getNSwitchlinks() {
		return nSwitchlinks.toArray(new Link[nSwitchlinks.size()]);
	}

	public Link[] getSSwitchlinks() {
		return sSwitchlinks.toArray(new Link[sSwitchlinks.size()]);
	}

	public long getFintid() {
		return fintid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {

		// If the object is compared with itself then return true
		if (o == this) {
			return true;
		}

		/*
		 * Check if o is an instance of Fint or not "null instanceof [type]"
		 * also returns false
		 */
		if (!(o instanceof Fint)) {
			return false;
		}

		// typecast o to Fint so that we can compare data members
		Fint f = (Fint) o;

		// Compare the data members and return accordingly
		return this.key.equals(f.key);
	}
}
