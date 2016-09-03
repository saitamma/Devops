package com.cisco.ca.cstg.pdi.pojos.mo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="vhba")
public class Vhba implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -915600807381774848L;
	private SanVHBA sanVHBA;
	
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String adapterPolicy;
	@XmlAttribute
	private String vhbaTemplate;

	
	public Vhba(){
	}

	public Vhba(SanVHBA sanVHBA, String name, String adapterPolicy, String vhbaTemplate) {
		this.sanVHBA = sanVHBA;
		this.name = name;
		this.adapterPolicy = adapterPolicy;
		this.vhbaTemplate = vhbaTemplate;
	}

	public SanVHBA getSanVHBA() {
		return sanVHBA;
	}

	public void setSanVHBA(SanVHBA sanVHBA) {
		this.sanVHBA = sanVHBA;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdapterPolicy() {
		return adapterPolicy;
	}

	public void setAdapterPolicy(String adapterPolicy) {
		this.adapterPolicy = adapterPolicy;
	}

	public String getVhbaTemplate() {
		return vhbaTemplate;
	}

	public void setVhbaTemplate(String vhbaTemplate) {
		this.vhbaTemplate = vhbaTemplate;
	}

}
