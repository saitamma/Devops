package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfResumeOnACPowerLoss")
public class Biosvfresumeonacpowerloss implements java.io.Serializable {

	private static final long serialVersionUID = -8762628438665999353L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute
	private String childAction;
	@XmlAttribute
	private String rn;
	@XmlAttribute(name="vpResumeOnACPowerLoss")
	private String vpResumeOnAcpowerLoss;

	public Biosvfresumeonacpowerloss() {
	}

	public Biosvfresumeonacpowerloss(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvfresumeonacpowerloss(Biosvprofile biosvprofile,
			String childAction, String rn, String vpResumeOnAcpowerLoss) {
		this.biosvprofile = biosvprofile;
		this.childAction = childAction;
		this.rn = rn;
		this.vpResumeOnAcpowerLoss = vpResumeOnAcpowerLoss;
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

	public String getVpResumeOnAcpowerLoss() {
		return this.vpResumeOnAcpowerLoss;
	}

	public void setVpResumeOnAcpowerLoss(String vpResumeOnAcpowerLoss) {
		this.vpResumeOnAcpowerLoss = vpResumeOnAcpowerLoss;
	}

}
