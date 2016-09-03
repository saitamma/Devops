package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfProcessorPrefetchConfig")
public class Biosvfprocessorprefetchconfig implements java.io.Serializable {

	private static final long serialVersionUID = 3757378448691912014L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute
	private String childAction;
	@XmlAttribute
	private String rn;
	@XmlAttribute(name="vpAdjacentCacheLinePrefetcher")
	private String vpAdjacentCacheLinePrefetcher;
	@XmlAttribute(name="vpDCUIPPrefetcher")
	private String vpDcuipprefetcher;
	@XmlAttribute(name="vpDCUStreamerPrefetch")
	private String vpDcustreamerPrefetch;
	@XmlAttribute(name="vpHardwarePrefetcher")
	private String vpHardwarePrefetcher;

	public Biosvfprocessorprefetchconfig() {
	}

	public Biosvfprocessorprefetchconfig(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvfprocessorprefetchconfig(Biosvprofile biosvprofile,
			String childAction, String rn,
			String vpAdjacentCacheLinePrefetcher, String vpDcuipprefetcher,
			String vpDcustreamerPrefetch, String vpHardwarePrefetcher) {
		this.biosvprofile = biosvprofile;
		this.childAction = childAction;
		this.rn = rn;
		this.vpAdjacentCacheLinePrefetcher = vpAdjacentCacheLinePrefetcher;
		this.vpDcuipprefetcher = vpDcuipprefetcher;
		this.vpDcustreamerPrefetch = vpDcustreamerPrefetch;
		this.vpHardwarePrefetcher = vpHardwarePrefetcher;
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

	public String getVpAdjacentCacheLinePrefetcher() {
		return this.vpAdjacentCacheLinePrefetcher;
	}

	public void setVpAdjacentCacheLinePrefetcher(
			String vpAdjacentCacheLinePrefetcher) {
		this.vpAdjacentCacheLinePrefetcher = vpAdjacentCacheLinePrefetcher;
	}

	public String getVpDcuipprefetcher() {
		return this.vpDcuipprefetcher;
	}

	public void setVpDcuipprefetcher(String vpDcuipprefetcher) {
		this.vpDcuipprefetcher = vpDcuipprefetcher;
	}

	public String getVpDcustreamerPrefetch() {
		return this.vpDcustreamerPrefetch;
	}

	public void setVpDcustreamerPrefetch(String vpDcustreamerPrefetch) {
		this.vpDcustreamerPrefetch = vpDcustreamerPrefetch;
	}

	public String getVpHardwarePrefetcher() {
		return this.vpHardwarePrefetcher;
	}

	public void setVpHardwarePrefetcher(String vpHardwarePrefetcher) {
		this.vpHardwarePrefetcher = vpHardwarePrefetcher;
	}

}
