package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfDirectCacheAccess")
public class Biosvfdirectcacheaccess implements java.io.Serializable {

	private static final long serialVersionUID = 2224481014081840744L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute
	private String childAction;
	@XmlAttribute
	private String rn;
	@XmlAttribute(name="vpDirectCacheAccess")
	private String vpDirectCacheAccess;

	public Biosvfdirectcacheaccess() {
	}

	public Biosvfdirectcacheaccess(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvfdirectcacheaccess(Biosvprofile biosvprofile,
			String childAction, String rn, String vpDirectCacheAccess) {
		this.biosvprofile = biosvprofile;
		this.childAction = childAction;
		this.rn = rn;
		this.vpDirectCacheAccess = vpDirectCacheAccess;
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

	public String getVpDirectCacheAccess() {
		return this.vpDirectCacheAccess;
	}

	public void setVpDirectCacheAccess(String vpDirectCacheAccess) {
		this.vpDirectCacheAccess = vpDirectCacheAccess;
	}

}
