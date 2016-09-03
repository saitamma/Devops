package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfPSTATECoordination")
public class Biosvfpstatecoordination implements java.io.Serializable {

	private static final long serialVersionUID = -2275025376925299072L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute
	private String childAction;
	@XmlAttribute
	private String rn;
	@XmlAttribute(name="vpPSTATECoordination")
	private String vpPstatecoordination;

	public Biosvfpstatecoordination() {
	}

	public Biosvfpstatecoordination(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvfpstatecoordination(Biosvprofile biosvprofile,
			String childAction, String rn, String vpPstatecoordination) {
		this.biosvprofile = biosvprofile;
		this.childAction = childAction;
		this.rn = rn;
		this.vpPstatecoordination = vpPstatecoordination;
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

	public String getVpPstatecoordination() {
		return this.vpPstatecoordination;
	}

	public void setVpPstatecoordination(String vpPstatecoordination) {
		this.vpPstatecoordination = vpPstatecoordination;
	}

}
