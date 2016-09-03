package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfACPI10Support")
public class Biosvfacpi10support implements java.io.Serializable {

	private static final long serialVersionUID = 3150382724761912976L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute(name="vpACPI10Support")
	private String vpAcpi10support;

	public Biosvfacpi10support() {
	}

	public Biosvfacpi10support(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvfacpi10support(Biosvprofile biosvprofile, String childAction,
			String rn, String vpAcpi10support) {
		this.biosvprofile = biosvprofile;
		this.vpAcpi10support = vpAcpi10support;
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

	public String getVpAcpi10support() {
		return this.vpAcpi10support;
	}

	public void setVpAcpi10support(String vpAcpi10support) {
		this.vpAcpi10support = vpAcpi10support;
	}

}
