package com.cisco.ca.cstg.pdi.pojos.topology;

public class Link {
	private final int fromPort;
	private final String toPort;
	private final Device down;
	private final Device up;

	public Link(int port, Device down, String uplinkPort, Device up) {
		this.fromPort = port;
		this.toPort = uplinkPort;
		this.down = down;
		this.up = up;
	}

	public int getFromPort() {
		return fromPort;
	}

	public String getToPort() {
		return toPort;
	}

	public Device getDown() {
		return down;
	}

	public Device getUp() {
		return up;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + fromPort;
		result = prime * result + ((toPort == null) ? 0 : toPort.hashCode());
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
		Link other = (Link) obj;
		if (fromPort != other.fromPort){
			return false;}
		if (toPort == null) {
			if (other.toPort != null){
				return false;}
		} else if (!toPort.equals(other.toPort)){
			return false;}
		return true;
	}

	@Override
	public String toString() {
		return "Link [port=" + fromPort + ", uplinkPort=" + toPort + "]";
	}

}
