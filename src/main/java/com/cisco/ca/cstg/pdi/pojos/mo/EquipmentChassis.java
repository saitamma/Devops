package com.cisco.ca.cstg.pdi.pojos.mo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="equipmentChassis")
public class EquipmentChassis implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7571151565771946495L;
	private Metadata metadata;
	
	@XmlAttribute
	private String cdpLinkAgg;
	@XmlAttribute
	private String cdpAction;
		
	public EquipmentChassis(){
	}

	public EquipmentChassis(Metadata metadata, String cdpLinkAgg,
			String cdpAction) {
		this.metadata = metadata;
		this.cdpLinkAgg = cdpLinkAgg;
		this.cdpAction = cdpAction;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public String getCdpLinkAgg() {
		return cdpLinkAgg;
	}

	public void setCdpLinkAgg(String cdpLinkAgg) {
		this.cdpLinkAgg = cdpLinkAgg;
	}

	public String getCdpAction() {
		return cdpAction;
	}

	public void setCdpAction(String cdpAction) {
		this.cdpAction = cdpAction;
	}
}
