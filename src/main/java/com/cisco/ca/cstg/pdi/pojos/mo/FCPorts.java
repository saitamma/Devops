package com.cisco.ca.cstg.pdi.pojos.mo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="fcPorts")
public class FCPorts implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5381464051449694916L;
	private Metadata metadata;
	
	@XmlAttribute
	private boolean available;
	
	public FCPorts(){
	}

	public FCPorts(Metadata metadata, boolean available) {
		this.metadata = metadata;
		this.available = available;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}	
}
