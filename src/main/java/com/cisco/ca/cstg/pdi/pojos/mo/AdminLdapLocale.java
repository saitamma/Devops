package com.cisco.ca.cstg.pdi.pojos.mo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="adminLdapLocale") 
@XmlAccessorType(XmlAccessType.FIELD)
public class AdminLdapLocale implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5944885502658265861L;
	private Metadata metadata;
	
	@XmlElement(name="aaaLocale")
	private List<AaaLocale> aaaLocale = new ArrayList<AaaLocale>();
	
	public AdminLdapLocale(){
		
	}
	
	public AdminLdapLocale(Metadata metadata, List<AaaLocale> aaaLocale) {
		this.metadata = metadata;
		this.aaaLocale = aaaLocale;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public List<AaaLocale> getAaaLocale() {
		return aaaLocale;
	}

	public void setAaaLocale(List<AaaLocale> aaaLocale) {
		this.aaaLocale = aaaLocale;
	}
}