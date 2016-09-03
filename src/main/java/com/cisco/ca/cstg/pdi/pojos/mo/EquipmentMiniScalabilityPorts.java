package com.cisco.ca.cstg.pdi.pojos.mo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "equipmentMiniScalability")
public class EquipmentMiniScalabilityPorts implements Serializable {

	private static final long serialVersionUID = 1L;
	private Metadata metadata;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String slotId;
	@XmlAttribute
	private String aggrPortId;

	@XmlElement(name = "equipmentServer")
	private List<EquipmentServer> equipmentServer = new ArrayList<>();

	public EquipmentMiniScalabilityPorts() {
	}

	public EquipmentMiniScalabilityPorts(Metadata metadata, String name,
			String slotId, String aggrPortId,
			List<EquipmentServer> equipmentServer) {
		this.metadata = metadata;
		this.name = name;
		this.slotId = slotId;
		this.aggrPortId = aggrPortId;
		this.equipmentServer = equipmentServer;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSlotId() {
		return slotId;
	}

	public void setSlotId(String slotId) {
		this.slotId = slotId;
	}

	public String getAggrPortId() {
		return aggrPortId;
	}

	public void setAggrPortId(String aggrPortId) {
		this.aggrPortId = aggrPortId;
	}

	public List<EquipmentServer> getEquipmentServer() {
		return equipmentServer;
	}

	public void setEquipmentServer(List<EquipmentServer> equipmentServer) {
		this.equipmentServer = equipmentServer;
	}

}
