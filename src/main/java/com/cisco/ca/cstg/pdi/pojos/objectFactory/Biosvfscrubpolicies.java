package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfScrubPolicies")
public class Biosvfscrubpolicies implements java.io.Serializable {

	private static final long serialVersionUID = 7480610412003212561L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute
	private String childAction;
	@XmlAttribute
	private String rn;
	@XmlAttribute
	private String vpDemandScrub;
	@XmlAttribute
	private String vpPatrolScrub;

	public Biosvfscrubpolicies() {
	}

	public Biosvfscrubpolicies(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvfscrubpolicies(Biosvprofile biosvprofile, String childAction,
			String rn, String vpDemandScrub, String vpPatrolScrub) {
		this.biosvprofile = biosvprofile;
		this.childAction = childAction;
		this.rn = rn;
		this.vpDemandScrub = vpDemandScrub;
		this.vpPatrolScrub = vpPatrolScrub;
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

	public String getVpDemandScrub() {
		return this.vpDemandScrub;
	}

	public void setVpDemandScrub(String vpDemandScrub) {
		this.vpDemandScrub = vpDemandScrub;
	}

	public String getVpPatrolScrub() {
		return this.vpPatrolScrub;
	}

	public void setVpPatrolScrub(String vpPatrolScrub) {
		this.vpPatrolScrub = vpPatrolScrub;
	}

}
