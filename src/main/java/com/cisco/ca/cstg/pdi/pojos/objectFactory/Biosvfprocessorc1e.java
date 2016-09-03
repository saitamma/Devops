package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfProcessorC1E")
public class Biosvfprocessorc1e implements java.io.Serializable {

	private static final long serialVersionUID = 2279524769954746771L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute
	private String childAction;
	@XmlAttribute
	private String rn;
	@XmlAttribute(name="vpProcessorC1E")
	private String vpProcessorC1e;

	public Biosvfprocessorc1e() {
	}

	public Biosvfprocessorc1e(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvfprocessorc1e(Biosvprofile biosvprofile, String childAction,
			String rn, String vpProcessorC1e) {
		this.biosvprofile = biosvprofile;
		this.childAction = childAction;
		this.rn = rn;
		this.vpProcessorC1e = vpProcessorC1e;
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

	public String getVpProcessorC1e() {
		return this.vpProcessorC1e;
	}

	public void setVpProcessorC1e(String vpProcessorC1e) {
		this.vpProcessorC1e = vpProcessorC1e;
	}

}
