package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfNUMAOptimized")
public class Biosvfnumaoptimized implements java.io.Serializable {

	private static final long serialVersionUID = -7799855050326862511L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute
	private String childAction;
	@XmlAttribute
	private String rn;
	@XmlAttribute(name="vpNUMAOptimized")
	private String vpNumaoptimized;

	public Biosvfnumaoptimized() {
	}

	public Biosvfnumaoptimized(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvfnumaoptimized(Biosvprofile biosvprofile, String childAction,
			String rn, String vpNumaoptimized) {
		this.biosvprofile = biosvprofile;
		this.childAction = childAction;
		this.rn = rn;
		this.vpNumaoptimized = vpNumaoptimized;
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

	public String getVpNumaoptimized() {
		return this.vpNumaoptimized;
	}

	public void setVpNumaoptimized(String vpNumaoptimized) {
		this.vpNumaoptimized = vpNumaoptimized;
	}

}
