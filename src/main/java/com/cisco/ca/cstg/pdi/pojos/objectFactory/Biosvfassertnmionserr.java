package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfAssertNMIOnSERR")
public class Biosvfassertnmionserr implements java.io.Serializable {

	private static final long serialVersionUID = 7613028762836003311L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute(name="vpAssertNMIOnSERR")
	private String vpAssertNmionSerr;

	public Biosvfassertnmionserr() {
	}

	public Biosvfassertnmionserr(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvfassertnmionserr(Biosvprofile biosvprofile, String childAction,
			String rn, String vpAssertNmionSerr) {
		this.biosvprofile = biosvprofile;
		this.vpAssertNmionSerr = vpAssertNmionSerr;
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

	public String getVpAssertNmionSerr() {
		return this.vpAssertNmionSerr;
	}

	public void setVpAssertNmionSerr(String vpAssertNmionSerr) {
		this.vpAssertNmionSerr = vpAssertNmionSerr;
	}

}
