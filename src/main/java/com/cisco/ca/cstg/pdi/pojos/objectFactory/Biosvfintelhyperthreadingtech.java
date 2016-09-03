package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfIntelHyperThreadingTech")
public class Biosvfintelhyperthreadingtech implements java.io.Serializable {

	private static final long serialVersionUID = 2739681938899722383L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute
	private String childAction;
	@XmlAttribute
	private String rn;
	@XmlAttribute
	private String vpIntelHyperThreadingTech;

	public Biosvfintelhyperthreadingtech() {
	}

	public Biosvfintelhyperthreadingtech(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvfintelhyperthreadingtech(Biosvprofile biosvprofile,
			String childAction, String rn, String vpIntelHyperThreadingTech) {
		this.biosvprofile = biosvprofile;
		this.childAction = childAction;
		this.rn = rn;
		this.vpIntelHyperThreadingTech = vpIntelHyperThreadingTech;
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

	public String getChildAction() {
		return this.childAction;
	}

	public void setChildAction(String childAction) {
		this.childAction = childAction;
	}

	public String getRn() {
		return this.rn;
	}

	public void setRn(String rn) {
		this.rn = rn;
	}

	public String getVpIntelHyperThreadingTech() {
		return this.vpIntelHyperThreadingTech;
	}

	public void setVpIntelHyperThreadingTech(String vpIntelHyperThreadingTech) {
		this.vpIntelHyperThreadingTech = vpIntelHyperThreadingTech;
	}

}
