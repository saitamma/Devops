package com.cisco.ca.cstg.pdi.pojos;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "san_vhba_template")
public class SanVhbaTemplate implements java.io.Serializable {

	private static final long serialVersionUID = -5472063536710718845L;
	
	private Integer id;
	private ProjectDetails projectDetails;
	private SanWwpn sanWwpn;
	private LanQosPolicy lanQosPolicy;
	private Organizations organizations;
	private SanVsan sanVsan;
	private String vhbaName;
	private String description;
	private String switchId;
	private String templateType;
	private List<SanVhba> sanVhbas = new ArrayList<SanVhba>();

	public SanVhbaTemplate() {
	}
	
	public SanVhbaTemplate(int id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	public ProjectDetails getProjectDetails() {
		return this.projectDetails;
	}

	public void setProjectDetails(ProjectDetails projectDetails) {
		this.projectDetails = projectDetails;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "wwpn_pool_id")
	@Fetch(FetchMode.SELECT)
	public SanWwpn getSanWwpn() {
		return this.sanWwpn;
	}

	public void setSanWwpn(SanWwpn sanWwpn) {
		this.sanWwpn = sanWwpn;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "qos_policy_id")
	@Fetch(FetchMode.SELECT)
	public LanQosPolicy getLanQosPolicy() {
		return this.lanQosPolicy;
	}

	public void setLanQosPolicy(LanQosPolicy lanQosPolicy) {
		this.lanQosPolicy = lanQosPolicy;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization")
	public Organizations getOrganizations() {
		return this.organizations;
	}

	public void setOrganizations(Organizations organizations) {
		this.organizations = organizations;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "vsan_id")
	@Fetch(FetchMode.SELECT)
	public SanVsan getSanVsan() {
		return this.sanVsan;
	}

	public void setSanVsan(SanVsan sanVsan) {
		this.sanVsan = sanVsan;
	}

	@Column(name = "vhba_name", length = 45)
	public String getVhbaName() {
		return this.vhbaName;
	}

	public void setVhbaName(String vhbaName) {
		this.vhbaName = vhbaName;
	}

	@Column(name = "description", length = 45)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "switch_id", length = 45)
	public String getSwitchId() {
		return this.switchId;
	}

	public void setSwitchId(String switchId) {
		this.switchId = switchId;
	}

	@Column(name = "template_type", length = 45)
	public String getTemplateType() {
		return this.templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sanVhbaTemplate")
	public List<SanVhba> getSanVhbas() {
		return this.sanVhbas;
	}

	public void setSanVhbas(List<SanVhba> sanVhbas) {
		this.sanVhbas = sanVhbas;
	}

}
