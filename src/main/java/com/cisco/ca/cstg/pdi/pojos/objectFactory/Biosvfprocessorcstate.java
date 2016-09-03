package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfProcessorCState")
public class Biosvfprocessorcstate implements java.io.Serializable {

	private static final long serialVersionUID = 4833226593345005091L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute
	private String childAction;
	@XmlAttribute
	private String rn;
	@XmlAttribute(name="vpProcessorCState")
	private String vpProcessorCstate;

	public Biosvfprocessorcstate() {
	}

	public Biosvfprocessorcstate(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvfprocessorcstate(Biosvprofile biosvprofile, String childAction,
			String rn, String vpProcessorCstate) {
		this.biosvprofile = biosvprofile;
		this.childAction = childAction;
		this.rn = rn;
		this.vpProcessorCstate = vpProcessorCstate;
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

	public String getVpProcessorCstate() {
		return this.vpProcessorCstate;
	}

	public void setVpProcessorCstate(String vpProcessorCstate) {
		this.vpProcessorCstate = vpProcessorCstate;
	}

}
