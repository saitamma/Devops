package com.cisco.ca.cstg.pdi.pojos.mo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "infrastructure")
public class Infrastructure implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5064773732216683763L;
	private Metadata metadata;

	@XmlAttribute
	private String iomModel;
	@XmlAttribute
	private String softwareVersion;
	@XmlAttribute
	private String fabricInterconnectModel;
	@XmlAttribute
	private String ethernetMode;
	@XmlAttribute
	private String fcMode;

	public Infrastructure() {

	}

	public Infrastructure(Metadata metadata, String iomModel,
			String softwareVersion, String fabricInterconnectModel,
			String ethernetMode, String fcMode) {
		this.metadata = metadata;
		this.iomModel = iomModel;
		this.softwareVersion = softwareVersion;
		this.fabricInterconnectModel = fabricInterconnectModel;
		this.ethernetMode = ethernetMode;
		this.fcMode = fcMode;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public String getIomModel() {
		return iomModel;
	}

	public void setIomModel(String iomModel) {
		this.iomModel = iomModel;
	}

	public String getSoftwareVersion() {
		return softwareVersion;
	}

	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}

	public String getFabricInterconnectModel() {
		return fabricInterconnectModel;
	}

	public void setFabricInterconnectModel(String fabricInterconnectModel) {
		this.fabricInterconnectModel = fabricInterconnectModel;
	}

	public String getEthernetMode() {
		return ethernetMode;
	}

	public void setEthernetMode(String ethernetMode) {
		this.ethernetMode = ethernetMode;
	}

	public String getFcMode() {
		return fcMode;
	}

	public void setFcMode(String fcMode) {
		this.fcMode = fcMode;
	}

}
