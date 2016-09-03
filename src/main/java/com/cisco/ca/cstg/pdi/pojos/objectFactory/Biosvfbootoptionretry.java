package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfBootOptionRetry")
public class Biosvfbootoptionretry implements java.io.Serializable {

	private static final long serialVersionUID = -2124089559229681055L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute
	private String vpBootOptionRetry;

	public Biosvfbootoptionretry() {
	}

	public Biosvfbootoptionretry(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvfbootoptionretry(Biosvprofile biosvprofile, String childAction,
			String rn, String vpBootOptionRetry) {
		this.biosvprofile = biosvprofile;
		this.vpBootOptionRetry = vpBootOptionRetry;
	}

	public Integer getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(Integer primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Biosvprofile getBiosvprofile() {
		return this.biosvprofile;
	}

	public void setBiosvprofile(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public String getVpBootOptionRetry() {
		return this.vpBootOptionRetry;
	}

	public void setVpBootOptionRetry(String vpBootOptionRetry) {
		this.vpBootOptionRetry = vpBootOptionRetry;
	}

}
