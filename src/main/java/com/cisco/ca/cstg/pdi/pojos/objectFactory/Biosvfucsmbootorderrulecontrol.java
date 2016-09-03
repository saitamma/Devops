package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfUCSMBootOrderRuleControl")
public class Biosvfucsmbootorderrulecontrol implements java.io.Serializable {

	private static final long serialVersionUID = 3284350426459572470L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute
	private String childAction;
	@XmlAttribute
	private String rn;
	@XmlAttribute
	private String vpUcsmbootOrderRule;

	public Biosvfucsmbootorderrulecontrol() {
	}

	public Biosvfucsmbootorderrulecontrol(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvfucsmbootorderrulecontrol(Biosvprofile biosvprofile,
			String childAction, String rn, String vpUcsmbootOrderRule) {
		this.biosvprofile = biosvprofile;
		this.childAction = childAction;
		this.rn = rn;
		this.vpUcsmbootOrderRule = vpUcsmbootOrderRule;
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

	public String getVpUcsmbootOrderRule() {
		return this.vpUcsmbootOrderRule;
	}

	public void setVpUcsmbootOrderRule(String vpUcsmbootOrderRule) {
		this.vpUcsmbootOrderRule = vpUcsmbootOrderRule;
	}

}
