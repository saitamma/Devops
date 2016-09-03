package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfProcessorC3Report")
public class Biosvfprocessorc3report implements java.io.Serializable {

	private static final long serialVersionUID = -5407516461060521779L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute
	private String childAction;
	@XmlAttribute
	private String rn;
	@XmlAttribute(name="vpProcessorC3Report")
	private String vpProcessorC3report;

	public Biosvfprocessorc3report() {
	}

	public Biosvfprocessorc3report(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvfprocessorc3report(Biosvprofile biosvprofile,
			String childAction, String rn, String vpProcessorC3report) {
		this.biosvprofile = biosvprofile;
		this.childAction = childAction;
		this.rn = rn;
		this.vpProcessorC3report = vpProcessorC3report;
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

	public String getVpProcessorC3report() {
		return this.vpProcessorC3report;
	}

	public void setVpProcessorC3report(String vpProcessorC3report) {
		this.vpProcessorC3report = vpProcessorC3report;
	}

}
