package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "adaptorEthInterruptProfile")
public class Adaptorethinterruptprofile implements java.io.Serializable {

	private static final long serialVersionUID = 8223778074049085118L;
	private int primaryKey;
	private Adaptorhostethifprofile adaptorhostethifprofile;
	@XmlAttribute
	private String coalescingTime;
	@XmlAttribute
	private String coalescingType;
	@XmlAttribute
	private String count;
	@XmlAttribute
	private String mode;

	public Adaptorethinterruptprofile() {
	}

	public Adaptorethinterruptprofile(
			Adaptorhostethifprofile adaptorhostethifprofile) {
		this.adaptorhostethifprofile = adaptorhostethifprofile;
	}

	public Adaptorethinterruptprofile(
			Adaptorhostethifprofile adaptorhostethifprofile,
			String coalescingTime, String coalescingType, String count,
			String mode) {
		this.adaptorhostethifprofile = adaptorhostethifprofile;
		this.coalescingTime = coalescingTime;
		this.coalescingType = coalescingType;
		this.count = count;
		this.mode = mode;
	}

	public int getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Adaptorhostethifprofile getAdaptorhostethifprofile() {
		return this.adaptorhostethifprofile;
	}

	public void setAdaptorhostethifprofile(
			Adaptorhostethifprofile adaptorhostethifprofile) {
		this.adaptorhostethifprofile = adaptorhostethifprofile;
	}

	public String getCoalescingTime() {
		return this.coalescingTime;
	}

	public void setCoalescingTime(String coalescingTime) {
		this.coalescingTime = coalescingTime;
	}

	public String getCoalescingType() {
		return this.coalescingType;
	}

	public void setCoalescingType(String coalescingType) {
		this.coalescingType = coalescingType;
	}

	public String getCount() {
		return this.count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getMode() {
		return this.mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

}
