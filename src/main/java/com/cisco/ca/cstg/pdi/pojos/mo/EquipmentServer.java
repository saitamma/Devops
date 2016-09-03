package com.cisco.ca.cstg.pdi.pojos.mo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="equipmentServer")
public class EquipmentServer  implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2543527818795319150L;
	private EquipmentServer equipmentServer;
	
	@XmlAttribute
	private Integer portId;
	@XmlAttribute
	private Integer slotId;
	@XmlAttribute
	private String usrLbl;
	@XmlAttribute
	private String fiId;
	@XmlAttribute
	private Integer chassisId;
	
	public EquipmentServer(){
	}

	public EquipmentServer(EquipmentServer equipmentServer, Integer portId, Integer slotId,
			String usrLbl, String fiId, Integer chassisId) {
		this.equipmentServer = equipmentServer;
		this.portId = portId;
		this.slotId = slotId;
		this.usrLbl = usrLbl;
		this.fiId = fiId;
		this.chassisId = chassisId;
	}

	public EquipmentServer getEquipmentServer() {
		return equipmentServer;
	}

	public void setEquipmentServer(EquipmentServer equipmentServer) {
		this.equipmentServer = equipmentServer;
	}

	public Integer getPortId() {
		return portId;
	}

	public void setPortId(Integer portId) {
		this.portId = portId;
	}

	public Integer getSlotId() {
		return slotId;
	}

	public void setSlotId(Integer slotId) {
		this.slotId = slotId;
	}

	public String getUsrLbl() {
		return usrLbl;
	}

	public void setUsrLbl(String usrLbl) {
		this.usrLbl = usrLbl;
	}

	public String getFiId() {
		return fiId;
	}

	public void setFiId(String fiId) {
		this.fiId = fiId;
	}

	public Integer getChassisId() {
		return chassisId;
	}

	public void setChassisId(Integer chassisId) {
		this.chassisId = chassisId;
	}		
}