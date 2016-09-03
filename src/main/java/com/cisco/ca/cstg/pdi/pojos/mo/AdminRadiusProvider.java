package com.cisco.ca.cstg.pdi.pojos.mo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="adminRadiusProvider") 
@XmlAccessorType(XmlAccessType.FIELD)
public class AdminRadiusProvider implements java.io.Serializable {

	private static final long serialVersionUID = -6149089194526683718L;
	private Metadata metadata;
	
	@XmlElement(name="aaaRadiusProvider")
	private List<AaaRadiusProvider> aaaRadiusProvider = new ArrayList<AaaRadiusProvider>();
	
	public AdminRadiusProvider() {	
	}

	public AdminRadiusProvider(Metadata metadata,
			List<AaaRadiusProvider> aaaRadiusProvider) {
		this.metadata = metadata;
		this.aaaRadiusProvider = aaaRadiusProvider;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public List<AaaRadiusProvider> getAaaRadiusProvider() {
		return aaaRadiusProvider;
	}

	public void setAaaRadiusProvider(List<AaaRadiusProvider> aaaRadiusProvider) {
		this.aaaRadiusProvider = aaaRadiusProvider;
	}
}
