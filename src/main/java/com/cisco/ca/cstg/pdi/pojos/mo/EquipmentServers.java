package com.cisco.ca.cstg.pdi.pojos.mo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="equipmentServers")
public class EquipmentServers  implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6077448343342559593L;
	private Metadata metadata;
	
	@XmlElement(name="equipmentServer")
	private List<EquipmentServer> equipmentServer = new ArrayList<>();
	
	public EquipmentServers(){
	}

	
	public EquipmentServers(Metadata metadata,
			List<EquipmentServer> equipmentServer) {
		this.metadata = metadata;
		this.equipmentServer = equipmentServer;
	}


	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public List<EquipmentServer> getEquipmentServer() {
		return equipmentServer;
	}


	public void setEquipmentServer(List<EquipmentServer> equipmentServer) {
		this.equipmentServer = equipmentServer;
	}
}