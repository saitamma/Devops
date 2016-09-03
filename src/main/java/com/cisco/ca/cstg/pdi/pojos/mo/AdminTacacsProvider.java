package com.cisco.ca.cstg.pdi.pojos.mo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="adminTacacsProvider") 
@XmlAccessorType(XmlAccessType.FIELD)
public class AdminTacacsProvider implements java.io.Serializable {

	private static final long serialVersionUID = -6149089194526683718L;
	private Metadata metadata;
	
	@XmlElement(name="aaaTacacsProvider")
	private List<AaaTacacsProvider> aaaTacacsProvider = new ArrayList<AaaTacacsProvider>();
	
	public AdminTacacsProvider() {	
	}

	public AdminTacacsProvider(Metadata metadata,
			List<AaaTacacsProvider> aaaTacacsProvider) {
		this.metadata = metadata;
		this.aaaTacacsProvider = aaaTacacsProvider;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public List<AaaTacacsProvider> getAaaTacacsProvider() {
		return aaaTacacsProvider;
	}

	public void setAaaTacacsProvider(List<AaaTacacsProvider> aaaTacacsProvider) {
		this.aaaTacacsProvider = aaaTacacsProvider;
	}
}
