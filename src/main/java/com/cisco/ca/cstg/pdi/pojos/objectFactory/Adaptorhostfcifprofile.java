package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "adaptorHostFcIfProfile")
public class Adaptorhostfcifprofile implements java.io.Serializable {

	private static final long serialVersionUID = -8632134802625942010L;
	private int primaryKey;
	private Orgorg orgorg;
	
	@XmlAttribute
	private String policyOwner;
	
	@XmlAttribute
	private String descr;
	
	@XmlAttribute
	private String name;
	
	@XmlElement
	private List<Adaptorfcworkqueueprofile> adaptorFcWorkQueueProfile  = new ArrayList<Adaptorfcworkqueueprofile>();
	
	@XmlElement
	private List<Adaptorfcrecvqueueprofile> adaptorFcRecvQueueProfile  = new ArrayList<Adaptorfcrecvqueueprofile>();
	
	@XmlElement
	private List<Adaptorfcportplogiprofile> adaptorFcPortPLogiProfile  = new ArrayList<Adaptorfcportplogiprofile>();
	
	@XmlElement
	private List<Adaptorfcportprofile> adaptorFcPortProfile  = new ArrayList<Adaptorfcportprofile>();
	
	@XmlElement
	private List<Adaptorfcinterruptprofile> adaptorFcInterruptProfile  = new ArrayList<Adaptorfcinterruptprofile>();
	
	@XmlElement
	private List<Adaptorfcportflogiprofile> adaptorFcPortFLogiProfile  = new ArrayList<Adaptorfcportflogiprofile>();
	
	@XmlElement
	private List<Adaptorfccdbworkqueueprofile> adaptorFcCdbWorkQueueProfile  = new ArrayList<Adaptorfccdbworkqueueprofile>();
	
	@XmlElement
	private List<Adaptorfcerrorrecoveryprofile> adaptorFcErrorRecoveryProfile  = new ArrayList<Adaptorfcerrorrecoveryprofile>();

	public Adaptorhostfcifprofile() {
	}

	public Adaptorhostfcifprofile(int primaryKey, Orgorg orgorg) {
		this.primaryKey = primaryKey;
		this.orgorg = orgorg;
	}
	
	public Adaptorhostfcifprofile(Orgorg orgorg, String policyOwner,
			String descr, String name,
			List<Adaptorfcworkqueueprofile> adaptorFcWorkQueueProfile,
			List<Adaptorfcrecvqueueprofile> adaptorFcRecvQueueProfile,
			List<Adaptorfcportplogiprofile> adaptorFcPortPLogiProfile,
			List<Adaptorfcportprofile> adaptorFcPortProfile,
			List<Adaptorfcinterruptprofile> adaptorFcInterruptProfile,
			List<Adaptorfcportflogiprofile> adaptorFcPortFLogiProfile,
			List<Adaptorfccdbworkqueueprofile> adaptorFcCdbWorkQueueProfile,
			List<Adaptorfcerrorrecoveryprofile> adaptorFcErrorRecoveryProfile) {
		super();
		this.orgorg = orgorg;
		this.policyOwner = policyOwner;
		this.descr = descr;
		this.name = name;
		this.adaptorFcWorkQueueProfile = adaptorFcWorkQueueProfile;
		this.adaptorFcRecvQueueProfile = adaptorFcRecvQueueProfile;
		this.adaptorFcPortPLogiProfile = adaptorFcPortPLogiProfile;
		this.adaptorFcPortProfile = adaptorFcPortProfile;
		this.adaptorFcInterruptProfile = adaptorFcInterruptProfile;
		this.adaptorFcPortFLogiProfile = adaptorFcPortFLogiProfile;
		this.adaptorFcCdbWorkQueueProfile = adaptorFcCdbWorkQueueProfile;
		this.adaptorFcErrorRecoveryProfile = adaptorFcErrorRecoveryProfile;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Orgorg getOrgorg() {
		return this.orgorg;
	}

	public void setOrgorg(Orgorg orgorg) {
		this.orgorg = orgorg;
	}

	public String getPolicyOwner() {
		return this.policyOwner;
	}

	public void setPolicyOwner(String policyOwner) {
		this.policyOwner = policyOwner;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Adaptorfcworkqueueprofile> getAdaptorFcWorkQueueProfile() {
		return adaptorFcWorkQueueProfile;
	}

	public void setAdaptorFcWorkQueueProfile(
			List<Adaptorfcworkqueueprofile> adaptorFcWorkQueueProfile) {
		this.adaptorFcWorkQueueProfile = adaptorFcWorkQueueProfile;
	}

	public List<Adaptorfcrecvqueueprofile> getAdaptorFcRecvQueueProfile() {
		return adaptorFcRecvQueueProfile;
	}

	public void setAdaptorFcRecvQueueProfile(
			List<Adaptorfcrecvqueueprofile> adaptorFcRecvQueueProfile) {
		this.adaptorFcRecvQueueProfile = adaptorFcRecvQueueProfile;
	}

	public List<Adaptorfcportplogiprofile> getAdaptorFcPortPLogiProfile() {
		return adaptorFcPortPLogiProfile;
	}

	public void setAdaptorFcPortPLogiProfile(
			List<Adaptorfcportplogiprofile> adaptorFcPortPLogiProfile) {
		this.adaptorFcPortPLogiProfile = adaptorFcPortPLogiProfile;
	}

	public List<Adaptorfcportprofile> getAdaptorFcPortProfile() {
		return adaptorFcPortProfile;
	}

	public void setAdaptorFcPortProfile(
			List<Adaptorfcportprofile> adaptorFcPortProfile) {
		this.adaptorFcPortProfile = adaptorFcPortProfile;
	}

	public List<Adaptorfcinterruptprofile> getAdaptorFcInterruptProfile() {
		return adaptorFcInterruptProfile;
	}

	public void setAdaptorFcInterruptProfile(
			List<Adaptorfcinterruptprofile> adaptorFcInterruptProfile) {
		this.adaptorFcInterruptProfile = adaptorFcInterruptProfile;
	}

	public List<Adaptorfcportflogiprofile> getAdaptorFcPortFLogiProfile() {
		return adaptorFcPortFLogiProfile;
	}

	public void setAdaptorFcPortFLogiProfile(
			List<Adaptorfcportflogiprofile> adaptorFcPortFLogiProfile) {
		this.adaptorFcPortFLogiProfile = adaptorFcPortFLogiProfile;
	}

	public List<Adaptorfccdbworkqueueprofile> getAdaptorFcCdbWorkQueueProfile() {
		return adaptorFcCdbWorkQueueProfile;
	}

	public void setAdaptorFcCdbWorkQueueProfile(
			List<Adaptorfccdbworkqueueprofile> adaptorFcCdbWorkQueueProfile) {
		this.adaptorFcCdbWorkQueueProfile = adaptorFcCdbWorkQueueProfile;
	}

	public List<Adaptorfcerrorrecoveryprofile> getAdaptorFcErrorRecoveryProfile() {
		return adaptorFcErrorRecoveryProfile;
	}

	public void setAdaptorFcErrorRecoveryProfile(
			List<Adaptorfcerrorrecoveryprofile> adaptorFcErrorRecoveryProfile) {
		this.adaptorFcErrorRecoveryProfile = adaptorFcErrorRecoveryProfile;
	}
}
