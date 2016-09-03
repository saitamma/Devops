package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfDramRefreshRate")
public class Biosvfdramrefreshrate implements java.io.Serializable {

	private static final long serialVersionUID = 2213095790696858381L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute
	private String childAction;
	@XmlAttribute
	private String rn;
	@XmlAttribute
	private String vpDramRefreshRate;

	public Biosvfdramrefreshrate() {
	}

	public Biosvfdramrefreshrate(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvfdramrefreshrate(Biosvprofile biosvprofile, String childAction,
			String rn, String vpDramRefreshRate) {
		this.biosvprofile = biosvprofile;
		this.childAction = childAction;
		this.rn = rn;
		this.vpDramRefreshRate = vpDramRefreshRate;
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

	public String getVpDramRefreshRate() {
		return this.vpDramRefreshRate;
	}

	public void setVpDramRefreshRate(String vpDramRefreshRate) {
		this.vpDramRefreshRate = vpDramRefreshRate;
	}

}
