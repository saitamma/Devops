package com.cisco.ca.cstg.pdi.pojos.topology;

/**
 * @author madmurug Aug 25, 2015
 */
public class EtherPorts {

	private String chassisid;
	private String chassisport;
	private String fabricport;

	/**
	 * @return the chassisid
	 */
	public String getChassisid() {
		return chassisid;
	}

	/**
	 * @param chassisid
	 *            the chassisid to set
	 */
	public void setChassisid(String chassisid) {
		this.chassisid = chassisid;
	}

	/**
	 * @return the chassisport
	 */
	public String getChassisport() {
		return chassisport;
	}

	/**
	 * @param chassisport
	 *            the chassisport to set
	 */
	public void setChassisport(String chassisport) {
		this.chassisport = chassisport;
	}

	/**
	 * @return the fabricport
	 */
	public String getFabricport() {
		return fabricport;
	}

	/**
	 * @param fabricport
	 *            the fabricport to set
	 */
	public void setFabricport(String fabricport) {
		this.fabricport = fabricport;
	}

}
