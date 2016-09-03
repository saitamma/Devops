package com.cisco.ca.cstg.pdi.pojos.mo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="adminLdapGroupMap") 
@XmlAccessorType(XmlAccessType.FIELD)
public class AdminLdapGroupMap implements java.io.Serializable {

	private static final long serialVersionUID = 3027556401697744447L;
	private Metadata metadata;
	
	@XmlElement(name="aaaLdapGroup")
	private List<AaaLdapGroup> aaaLdapGroup = new ArrayList<>();
	
	public AdminLdapGroupMap(){
		
	}

	public AdminLdapGroupMap(Metadata metadata, List<AaaLdapGroup> aaaLdapGroup) {
		this.metadata = metadata;
		this.aaaLdapGroup = aaaLdapGroup;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public List<AaaLdapGroup> getAaaLdapGroup() {
		return aaaLdapGroup;
	}

	public void setAaaLdapGroup(List<AaaLdapGroup> aaaLdapGroup) {
		this.aaaLdapGroup = aaaLdapGroup;
	}
}
