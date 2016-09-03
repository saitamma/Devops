package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfProcessorC7Report")
public class Biosvfprocessorc7report implements java.io.Serializable {

	private static final long serialVersionUID = 2317211225852848802L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute
	private String childAction;
	@XmlAttribute
	private String rn;
	@XmlAttribute(name="vpProcessorC7Report")
	private String vpProcessorC7report;

	public Biosvfprocessorc7report() {
	}

	public Biosvfprocessorc7report(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvfprocessorc7report(Biosvprofile biosvprofile,
			String childAction, String rn, String vpProcessorC7report) {
		this.biosvprofile = biosvprofile;
		this.childAction = childAction;
		this.rn = rn;
		this.vpProcessorC7report = vpProcessorC7report;
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

	public String getVpProcessorC7report() {
		return this.vpProcessorC7report;
	}

	public void setVpProcessorC7report(String vpProcessorC7report) {
		this.vpProcessorC7report = vpProcessorC7report;
	}

}
