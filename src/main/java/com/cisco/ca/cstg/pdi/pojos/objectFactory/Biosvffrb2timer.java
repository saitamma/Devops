package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfFRB2Timer")
public class Biosvffrb2timer implements java.io.Serializable {

	private static final long serialVersionUID = -65839571085242616L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute
	private String childAction;
	@XmlAttribute
	private String rn;
	@XmlAttribute(name="vpFRB2Timer")
	private String vpFrb2timer;

	public Biosvffrb2timer() {
	}

	public Biosvffrb2timer(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvffrb2timer(Biosvprofile biosvprofile, String childAction,
			String rn, String vpFrb2timer) {
		this.biosvprofile = biosvprofile;
		this.childAction = childAction;
		this.rn = rn;
		this.vpFrb2timer = vpFrb2timer;
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

	public String getVpFrb2timer() {
		return this.vpFrb2timer;
	}

	public void setVpFrb2timer(String vpFrb2timer) {
		this.vpFrb2timer = vpFrb2timer;
	}

}
