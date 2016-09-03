package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfIntelEntrySASRAIDModule")
public class Biosvfintelentrysasraidmodule implements java.io.Serializable {

	private static final long serialVersionUID = -8845666666991048371L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute
	private String childAction;
	@XmlAttribute
	private String rn;
	@XmlAttribute(name="vpSASRAID")
	private String vpSasraid;
	@XmlAttribute(name="vpSASRAIDModule")
	private String vpSasraidmodule;

	public Biosvfintelentrysasraidmodule() {
	}

	public Biosvfintelentrysasraidmodule(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvfintelentrysasraidmodule(Biosvprofile biosvprofile,
			String childAction, String rn, String vpSasraid,
			String vpSasraidmodule) {
		this.biosvprofile = biosvprofile;
		this.childAction = childAction;
		this.rn = rn;
		this.vpSasraid = vpSasraid;
		this.vpSasraidmodule = vpSasraidmodule;
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

	public String getVpSasraid() {
		return this.vpSasraid;
	}

	public void setVpSasraid(String vpSasraid) {
		this.vpSasraid = vpSasraid;
	}

	public String getVpSasraidmodule() {
		return this.vpSasraidmodule;
	}

	public void setVpSasraidmodule(String vpSasraidmodule) {
		this.vpSasraidmodule = vpSasraidmodule;
	}

}
