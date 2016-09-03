package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfLocalX2Apic")
public class Biosvflocalx2apic implements java.io.Serializable {

	private static final long serialVersionUID = -2286234879574935781L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute
	private String childAction;
	@XmlAttribute
	private String rn;
	@XmlAttribute(name="vpLocalX2Apic")
	private String vpLocalX2apic;

	public Biosvflocalx2apic() {
	}

	public Biosvflocalx2apic(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvflocalx2apic(Biosvprofile biosvprofile, String childAction,
			String rn, String vpLocalX2apic) {
		this.biosvprofile = biosvprofile;
		this.childAction = childAction;
		this.rn = rn;
		this.vpLocalX2apic = vpLocalX2apic;
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

	public String getVpLocalX2apic() {
		return this.vpLocalX2apic;
	}

	public void setVpLocalX2apic(String vpLocalX2apic) {
		this.vpLocalX2apic = vpLocalX2apic;
	}

}
