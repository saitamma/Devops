package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfQPILinkFrequencySelect")
public class Biosvfqpilinkfrequencyselect implements java.io.Serializable {

	private static final long serialVersionUID = -7699387696416908481L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute
	private String childAction;
	@XmlAttribute
	private String rn;
	@XmlAttribute(name="vpQPILinkFrequencySelect")
	private String vpQpilinkFrequencySelect;

	public Biosvfqpilinkfrequencyselect() {
	}

	public Biosvfqpilinkfrequencyselect(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvfqpilinkfrequencyselect(Biosvprofile biosvprofile,
			String childAction, String rn, String vpQpilinkFrequencySelect) {
		this.biosvprofile = biosvprofile;
		this.childAction = childAction;
		this.rn = rn;
		this.vpQpilinkFrequencySelect = vpQpilinkFrequencySelect;
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

	public String getVpQpilinkFrequencySelect() {
		return this.vpQpilinkFrequencySelect;
	}

	public void setVpQpilinkFrequencySelect(String vpQpilinkFrequencySelect) {
		this.vpQpilinkFrequencySelect = vpQpilinkFrequencySelect;
	}

}
