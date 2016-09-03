package com.cisco.ca.cstg.pdi.pojos.mo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="adminLdapRole") 
@XmlAccessorType(XmlAccessType.FIELD)
public class AdminLdapRole implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4661975017956458370L;
	private Metadata metadata;
	
	@XmlElement(name="aaaRole")
	private List<AaaRole> aaaRole = new ArrayList<>();
	
	public AdminLdapRole() {
	}
	
	public AdminLdapRole(Metadata metadata, List<AaaRole> aaaRole) {
		this.metadata = metadata;
		this.aaaRole = aaaRole;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public List<AaaRole> getAaaRole() {
		return aaaRole;
	}

	public void setAaaRole(List<AaaRole> aaaRole) {
		this.aaaRole = aaaRole;
	}

}
