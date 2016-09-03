package com.cisco.ca.cstg.pdi.pojos.mo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="adminLdapProvider") 
@XmlAccessorType(XmlAccessType.FIELD)
public class AdminLdapProvider implements java.io.Serializable {

	private static final long serialVersionUID = -6149089194526683718L;
	private Metadata metadata;
	
	@XmlElement(name="aaaLdapProvider")
	private List<AaaLdapProvider> aaaLdapProvider = new ArrayList<AaaLdapProvider>();
	
	public AdminLdapProvider() {	
	}

	public AdminLdapProvider(Metadata metadata,
			List<AaaLdapProvider> aaaLdapProvider) {
		this.metadata = metadata;
		this.aaaLdapProvider = aaaLdapProvider;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public List<AaaLdapProvider> getAaaLdapProvider() {
		return aaaLdapProvider;
	}

	public void setAaaLdapProvider(List<AaaLdapProvider> aaaLdapProvider) {
		this.aaaLdapProvider = aaaLdapProvider;
	}
}
