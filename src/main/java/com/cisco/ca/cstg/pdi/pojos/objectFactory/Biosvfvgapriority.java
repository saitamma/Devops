package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="Biosvfvgapriority")
public class Biosvfvgapriority implements java.io.Serializable {

	private static final long serialVersionUID = -2154183762852216141L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute
	private String childAction;
	@XmlAttribute
	private String rn;
	@XmlAttribute(name="vpVGAPriority")
	private String vpVgapriority;

	public Biosvfvgapriority() {
	}

	public Biosvfvgapriority(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvfvgapriority(Biosvprofile biosvprofile, String childAction,
			String rn, String vpVgapriority) {
		this.biosvprofile = biosvprofile;
		this.childAction = childAction;
		this.rn = rn;
		this.vpVgapriority = vpVgapriority;
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

	public String getVpVgapriority() {
		return this.vpVgapriority;
	}

	public void setVpVgapriority(String vpVgapriority) {
		this.vpVgapriority = vpVgapriority;
	}

}
