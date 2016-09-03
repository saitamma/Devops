package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfExecuteDisableBit")
public class Biosvfexecutedisablebit implements java.io.Serializable {

	private static final long serialVersionUID = 304715940642994972L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute
	private String childAction;
	@XmlAttribute
	private String rn;
	@XmlAttribute
	private String vpExecuteDisableBit;

	public Biosvfexecutedisablebit() {
	}

	public Biosvfexecutedisablebit(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvfexecutedisablebit(Biosvprofile biosvprofile,
			String childAction, String rn, String vpExecuteDisableBit) {
		this.biosvprofile = biosvprofile;
		this.childAction = childAction;
		this.rn = rn;
		this.vpExecuteDisableBit = vpExecuteDisableBit;
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

	public String getVpExecuteDisableBit() {
		return this.vpExecuteDisableBit;
	}

	public void setVpExecuteDisableBit(String vpExecuteDisableBit) {
		this.vpExecuteDisableBit = vpExecuteDisableBit;
	}

}
