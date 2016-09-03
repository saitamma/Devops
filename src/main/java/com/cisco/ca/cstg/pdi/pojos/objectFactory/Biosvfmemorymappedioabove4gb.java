package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfMemoryMappedIOAbove4GB")
public class Biosvfmemorymappedioabove4gb implements java.io.Serializable {

	private static final long serialVersionUID = 8028130456554039401L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute
	private String childAction;
	@XmlAttribute
	private String rn;
	@XmlAttribute(name="vpMemoryMappedIOAbove4GB")
	private String vpMemoryMappedIoabove4gb;

	public Biosvfmemorymappedioabove4gb() {
	}

	public Biosvfmemorymappedioabove4gb(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvfmemorymappedioabove4gb(Biosvprofile biosvprofile,
			String childAction, String rn, String vpMemoryMappedIoabove4gb) {
		this.biosvprofile = biosvprofile;
		this.childAction = childAction;
		this.rn = rn;
		this.vpMemoryMappedIoabove4gb = vpMemoryMappedIoabove4gb;
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

	public String getVpMemoryMappedIoabove4gb() {
		return this.vpMemoryMappedIoabove4gb;
	}

	public void setVpMemoryMappedIoabove4gb(String vpMemoryMappedIoabove4gb) {
		this.vpMemoryMappedIoabove4gb = vpMemoryMappedIoabove4gb;
	}

}
