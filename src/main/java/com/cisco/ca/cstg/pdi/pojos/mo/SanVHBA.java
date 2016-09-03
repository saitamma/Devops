package com.cisco.ca.cstg.pdi.pojos.mo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="sanVHBA")
public class SanVHBA implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7875341480085804456L;
	private Metadata metadata;
	
	@XmlElement(name="vhba")
	private List<Vhba> vhba = new ArrayList<>();
	
	public SanVHBA(){
	}

	public SanVHBA(Metadata metadata, List<Vhba> vhba) {
		this.metadata = metadata;
		this.vhba = vhba;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public List<Vhba> getVhba() {
		return vhba;
	}

	public void setVhba(List<Vhba> vhba) {
		this.vhba = vhba;
	}
}