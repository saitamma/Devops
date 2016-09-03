package com.cisco.ca.cstg.pdi.pojos.mo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="ucsDetails")
public class UcsDetails implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4696116887968514374L;
	private Metadata metadata;
	
	@XmlAttribute
	private String pushStatus;
	@XmlAttribute
	private String pingStatus;
	@XmlAttribute
	private String hostName;
	@XmlAttribute
	private String username;
	@XmlAttribute
	private String password;
	
	public UcsDetails(){		
	}

	public UcsDetails(Metadata metadata, String pushStatus, String pingStatus,
			String hostName, String username, String password) {
		this.metadata = metadata;
		this.pushStatus = pushStatus;
		this.pingStatus = pingStatus;
		this.hostName = hostName;
		this.username = username;
		this.password = password;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public String getPushStatus() {
		return pushStatus;
	}

	public void setPushStatus(String pushStatus) {
		this.pushStatus = pushStatus;
	}

	public String getPingStatus() {
		return pingStatus;
	}

	public void setPingStatus(String pingStatus) {
		this.pingStatus = pingStatus;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
