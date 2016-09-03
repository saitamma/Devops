package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfIntelVTForDirectedIO")
public class Biosvfintelvtfordirectedio implements java.io.Serializable {

	private static final long serialVersionUID = 7692084712289714198L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute
	private String childAction;
	@XmlAttribute
	private String rn;
	@XmlAttribute(name="vpIntelVTDATSSupport")
	private String vpIntelVtdatssupport;
	@XmlAttribute(name="vpIntelVTDCoherencySupport")
	private String vpIntelVtdcoherencySupport;
	@XmlAttribute(name="vpIntelVTDInterruptRemapping")
	private String vpIntelVtdinterruptRemapping;
	@XmlAttribute(name="vpIntelVTDPassThroughDMASupport")
	private String vpIntelVtdpassThroughDmasupport;
	@XmlAttribute(name="vpIntelVTForDirectedIO")
	private String vpIntelVtforDirectedIo;

	public Biosvfintelvtfordirectedio() {
	}

	public Biosvfintelvtfordirectedio(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvfintelvtfordirectedio(Biosvprofile biosvprofile,
			String childAction, String rn, String vpIntelVtdatssupport,
			String vpIntelVtdcoherencySupport,
			String vpIntelVtdinterruptRemapping,
			String vpIntelVtdpassThroughDmasupport,
			String vpIntelVtforDirectedIo) {
		this.biosvprofile = biosvprofile;
		this.childAction = childAction;
		this.rn = rn;
		this.vpIntelVtdatssupport = vpIntelVtdatssupport;
		this.vpIntelVtdcoherencySupport = vpIntelVtdcoherencySupport;
		this.vpIntelVtdinterruptRemapping = vpIntelVtdinterruptRemapping;
		this.vpIntelVtdpassThroughDmasupport = vpIntelVtdpassThroughDmasupport;
		this.vpIntelVtforDirectedIo = vpIntelVtforDirectedIo;
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

	public String getVpIntelVtdatssupport() {
		return this.vpIntelVtdatssupport;
	}

	public void setVpIntelVtdatssupport(String vpIntelVtdatssupport) {
		this.vpIntelVtdatssupport = vpIntelVtdatssupport;
	}

	public String getVpIntelVtdcoherencySupport() {
		return this.vpIntelVtdcoherencySupport;
	}

	public void setVpIntelVtdcoherencySupport(String vpIntelVtdcoherencySupport) {
		this.vpIntelVtdcoherencySupport = vpIntelVtdcoherencySupport;
	}

	public String getVpIntelVtdinterruptRemapping() {
		return this.vpIntelVtdinterruptRemapping;
	}

	public void setVpIntelVtdinterruptRemapping(
			String vpIntelVtdinterruptRemapping) {
		this.vpIntelVtdinterruptRemapping = vpIntelVtdinterruptRemapping;
	}

	public String getVpIntelVtdpassThroughDmasupport() {
		return this.vpIntelVtdpassThroughDmasupport;
	}

	public void setVpIntelVtdpassThroughDmasupport(
			String vpIntelVtdpassThroughDmasupport) {
		this.vpIntelVtdpassThroughDmasupport = vpIntelVtdpassThroughDmasupport;
	}

	public String getVpIntelVtforDirectedIo() {
		return this.vpIntelVtforDirectedIo;
	}

	public void setVpIntelVtforDirectedIo(String vpIntelVtforDirectedIo) {
		this.vpIntelVtforDirectedIo = vpIntelVtforDirectedIo;
	}

}
