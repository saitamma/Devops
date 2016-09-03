package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfLvDIMMSupport")
public class Biosvflvdimmsupport implements java.io.Serializable {

	private static final long serialVersionUID = -4711174194483636481L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute
	private String childAction;
	@XmlAttribute
	private String rn;
	@XmlAttribute(name="vpLvDDRMode")
	private String vpLvDdrmode;

	public Biosvflvdimmsupport() {
	}

	public Biosvflvdimmsupport(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvflvdimmsupport(Biosvprofile biosvprofile, String childAction,
			String rn, String vpLvDdrmode) {
		this.biosvprofile = biosvprofile;
		this.childAction = childAction;
		this.rn = rn;
		this.vpLvDdrmode = vpLvDdrmode;
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

	public String getVpLvDdrmode() {
		return this.vpLvDdrmode;
	}

	public void setVpLvDdrmode(String vpLvDdrmode) {
		this.vpLvDdrmode = vpLvDdrmode;
	}

}