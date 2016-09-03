package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfAssertNMIOnPERR")
public class Biosvfassertnmionperr implements java.io.Serializable {

	private static final long serialVersionUID = 8857098775289222896L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute(name="vpAssertNMIOnPERR")
	private String vpAssertNmionPerr;

	public Biosvfassertnmionperr() {
	}

	public Biosvfassertnmionperr(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvfassertnmionperr(Biosvprofile biosvprofile, String childAction,
			String rn, String vpAssertNmionPerr) {
		this.biosvprofile = biosvprofile;
		this.vpAssertNmionPerr = vpAssertNmionPerr;
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

	public String getVpAssertNmionPerr() {
		return this.vpAssertNmionPerr;
	}

	public void setVpAssertNmionPerr(String vpAssertNmionPerr) {
		this.vpAssertNmionPerr = vpAssertNmionPerr;
	}

}
