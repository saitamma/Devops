package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "adaptorHostEthIfProfile")
public class Adaptorhostethifprofile implements java.io.Serializable {

	private static final long serialVersionUID = 4877414406889510917L;
	private int primaryKey;
	private Orgorg orgorg;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String descr;
	@XmlAttribute
	private String policyOwner;
	@XmlElement
	private List<Adaptorethrecvqueueprofile> adaptorEthRecvQueueProfile = new ArrayList<Adaptorethrecvqueueprofile>();
	@XmlElement
	private List<Adaptorethinterruptprofile> adaptorEthInterruptProfile = new ArrayList<Adaptorethinterruptprofile>();
	@XmlElement
	private List<Adaptoretharfsprofile> adaptorEthArfsProfile = new ArrayList<Adaptoretharfsprofile>();
	@XmlElement
	private List<Adaptorethcompqueueprofile> adaptorEthCompQueueProfile = new ArrayList<Adaptorethcompqueueprofile>();
	@XmlElement
	private List<Adaptorrssprofile> adaptorRssProfile = new ArrayList<Adaptorrssprofile>();
	@XmlElement
	private List<Adaptorethworkqueueprofile> adaptorEthWorkQueueProfile = new ArrayList<Adaptorethworkqueueprofile>();
	@XmlElement
	private List<Adaptorethoffloadprofile> adaptorEthOffloadProfile = new ArrayList<Adaptorethoffloadprofile>();
	@XmlElement
	private List<Adaptorethfailoverprofile> adaptorEthFailoverProfile = new ArrayList<Adaptorethfailoverprofile>();
	@XmlElement
	private List<Adaptoripv4rsshashprofile> adaptorIpV4RssHashProfile = new ArrayList<Adaptoripv4rsshashprofile>();
	@XmlElement
	private List<Adaptoripv6rsshashprofile> adaptorIpV6RssHashProfile = new ArrayList<Adaptoripv6rsshashprofile>();
	@XmlElement
	private List<Adaptorextipv6rsshashprofile> adaptorExtIpV6RssHashProfile = new ArrayList<Adaptorextipv6rsshashprofile>();

	public Adaptorhostethifprofile() {
	}

	public Adaptorhostethifprofile(Orgorg orgorg) {
		this.orgorg = orgorg;
	}

	
	public Adaptorhostethifprofile(String name, String descr,
			String policyOwner,
			List<Adaptorethrecvqueueprofile> adaptorEthRecvQueueProfile,
			List<Adaptorethinterruptprofile> adaptorEthInterruptProfile,
			List<Adaptoretharfsprofile> adaptorEthArfsProfile,
			List<Adaptorethcompqueueprofile> adaptorEthCompQueueProfile,
			List<Adaptorrssprofile> adaptorRssProfile,
			List<Adaptorethworkqueueprofile> adaptorEthWorkQueueProfile,
			List<Adaptorethoffloadprofile> adaptorEthOffloadProfile,
			List<Adaptorethfailoverprofile> adaptorEthFailoverProfile,
			List<Adaptoripv4rsshashprofile> adaptorIpV4RssHashProfile,
			List<Adaptoripv6rsshashprofile> adaptorIpV6RssHashProfile,
			List<Adaptorextipv6rsshashprofile> adaptorExtIpV6RssHashProfile) {
		super();
		this.name = name;
		this.descr = descr;
		this.policyOwner = policyOwner;
		this.adaptorEthRecvQueueProfile = adaptorEthRecvQueueProfile;
		this.adaptorEthInterruptProfile = adaptorEthInterruptProfile;
		this.adaptorEthArfsProfile = adaptorEthArfsProfile;
		this.adaptorEthCompQueueProfile = adaptorEthCompQueueProfile;
		this.adaptorRssProfile = adaptorRssProfile;
		this.adaptorEthWorkQueueProfile = adaptorEthWorkQueueProfile;
		this.adaptorEthOffloadProfile = adaptorEthOffloadProfile;
		this.adaptorEthFailoverProfile = adaptorEthFailoverProfile;
		this.adaptorIpV4RssHashProfile = adaptorIpV4RssHashProfile;
		this.adaptorIpV6RssHashProfile = adaptorIpV6RssHashProfile;
		this.adaptorExtIpV6RssHashProfile = adaptorExtIpV6RssHashProfile;
	}

	public int getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Orgorg getOrgorg() {
		return orgorg;
	}

	public void setOrgorg(Orgorg orgorg) {
		this.orgorg = orgorg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getPolicyOwner() {
		return policyOwner;
	}

	public void setPolicyOwner(String policyOwner) {
		this.policyOwner = policyOwner;
	}

	public List<Adaptorethrecvqueueprofile> getAdaptorEthRecvQueueProfile() {
		return adaptorEthRecvQueueProfile;
	}

	public void setAdaptorEthRecvQueueProfile(
			List<Adaptorethrecvqueueprofile> adaptorEthRecvQueueProfile) {
		this.adaptorEthRecvQueueProfile = adaptorEthRecvQueueProfile;
	}

	public List<Adaptorethinterruptprofile> getAdaptorEthInterruptProfile() {
		return adaptorEthInterruptProfile;
	}

	public void setAdaptorEthInterruptProfile(
			List<Adaptorethinterruptprofile> adaptorEthInterruptProfile) {
		this.adaptorEthInterruptProfile = adaptorEthInterruptProfile;
	}

	public List<Adaptoretharfsprofile> getAdaptorEthArfsProfile() {
		return adaptorEthArfsProfile;
	}

	public void setAdaptorEthArfsProfile(
			List<Adaptoretharfsprofile> adaptorEthArfsProfile) {
		this.adaptorEthArfsProfile = adaptorEthArfsProfile;
	}

	public List<Adaptorethcompqueueprofile> getAdaptorEthCompQueueProfile() {
		return adaptorEthCompQueueProfile;
	}

	public void setAdaptorEthCompQueueProfile(
			List<Adaptorethcompqueueprofile> adaptorEthCompQueueProfile) {
		this.adaptorEthCompQueueProfile = adaptorEthCompQueueProfile;
	}

	public List<Adaptorrssprofile> getAdaptorRssProfile() {
		return adaptorRssProfile;
	}

	public void setAdaptorRssProfile(List<Adaptorrssprofile> adaptorRssProfile) {
		this.adaptorRssProfile = adaptorRssProfile;
	}

	public List<Adaptorethworkqueueprofile> getAdaptorEthWorkQueueProfile() {
		return adaptorEthWorkQueueProfile;
	}

	public void setAdaptorEthWorkQueueProfile(
			List<Adaptorethworkqueueprofile> adaptorEthWorkQueueProfile) {
		this.adaptorEthWorkQueueProfile = adaptorEthWorkQueueProfile;
	}

	public List<Adaptorethoffloadprofile> getAdaptorEthOffloadProfile() {
		return adaptorEthOffloadProfile;
	}

	public void setAdaptorEthOffloadProfile(
			List<Adaptorethoffloadprofile> adaptorEthOffloadProfile) {
		this.adaptorEthOffloadProfile = adaptorEthOffloadProfile;
	}

	public List<Adaptorethfailoverprofile> getAdaptorEthFailoverProfile() {
		return adaptorEthFailoverProfile;
	}

	public void setAdaptorEthFailoverProfile(
			List<Adaptorethfailoverprofile> adaptorEthFailoverProfile) {
		this.adaptorEthFailoverProfile = adaptorEthFailoverProfile;
	}

	public List<Adaptoripv4rsshashprofile> getAdaptorIpV4RssHashProfile() {
		return adaptorIpV4RssHashProfile;
	}

	public void setAdaptorIpV4RssHashProfile(
			List<Adaptoripv4rsshashprofile> adaptorIpV4RssHashProfile) {
		this.adaptorIpV4RssHashProfile = adaptorIpV4RssHashProfile;
	}

	public List<Adaptoripv6rsshashprofile> getAdaptorIpV6RssHashProfile() {
		return adaptorIpV6RssHashProfile;
	}

	public void setAdaptorIpV6RssHashProfile(
			List<Adaptoripv6rsshashprofile> adaptorIpV6RssHashProfile) {
		this.adaptorIpV6RssHashProfile = adaptorIpV6RssHashProfile;
	}

	public List<Adaptorextipv6rsshashprofile> getAdaptorExtIpV6RssHashProfile() {
		return adaptorExtIpV6RssHashProfile;
	}

	public void setAdaptorExtIpV6RssHashProfile(
			List<Adaptorextipv6rsshashprofile> adaptorExtIpV6RssHashProfile) {
		this.adaptorExtIpV6RssHashProfile = adaptorExtIpV6RssHashProfile;
	}


}
