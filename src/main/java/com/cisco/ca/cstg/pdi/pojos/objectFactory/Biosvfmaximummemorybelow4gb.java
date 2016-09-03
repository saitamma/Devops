package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfMaximumMemoryBelow4GB")
public class Biosvfmaximummemorybelow4gb implements java.io.Serializable {

	private static final long serialVersionUID = -2948569712228237448L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute
	private String childAction;
	@XmlAttribute
	private String rn;
	@XmlAttribute(name="vpMaximumMemoryBelow4GB")
	private String vpMaximumMemoryBelow4gb;

	public Biosvfmaximummemorybelow4gb() {
	}

	public Biosvfmaximummemorybelow4gb(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvfmaximummemorybelow4gb(Biosvprofile biosvprofile,
			String childAction, String rn, String vpMaximumMemoryBelow4gb) {
		this.biosvprofile = biosvprofile;
		this.childAction = childAction;
		this.rn = rn;
		this.vpMaximumMemoryBelow4gb = vpMaximumMemoryBelow4gb;
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

	public String getVpMaximumMemoryBelow4gb() {
		return this.vpMaximumMemoryBelow4gb;
	}

	public void setVpMaximumMemoryBelow4gb(String vpMaximumMemoryBelow4gb) {
		this.vpMaximumMemoryBelow4gb = vpMaximumMemoryBelow4gb;
	}

}
