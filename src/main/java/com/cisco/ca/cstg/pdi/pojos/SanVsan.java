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


@Entity
@Table(name = "san_vsan")
public class SanVsan implements java.io.Serializable {

	private static final long serialVersionUID = -9160069876437235431L;
	
	private Integer id;
	private ProjectDetails projectDetails;
	private String fiId;
	private String description;
	private String fcoeVlanName;
	private String vsanName;
	private Integer fcoeVsanId;
	private List<SanVhbaTemplate> sanVhbaTemplates = new ArrayList<SanVhbaTemplate>();

	public SanVsan() {
	}
	
	public SanVsan(int id) {
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

	@Column(name = "fi_id", length = 45)
	public String getFiId() {
		return this.fiId;
	}

	public void setFiId(String fiId) {
		this.fiId = fiId;
	}

	@Column(name = "description", length = 45)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "fcoe_vlan_name", length = 45)
	public String getFcoeVlanName() {
		return this.fcoeVlanName;
	}

	public void setFcoeVlanName(String fcoeVlanName) {
		this.fcoeVlanName = fcoeVlanName;
	}

	@Column(name = "vsan_name", length = 45)
	public String getVsanName() {
		return this.vsanName;
	}

	public void setVsanName(String vsanName) {
		this.vsanName = vsanName;
	}

	@Column(name = "fcoe_vsan_id")
	public Integer getFcoeVsanId() {
		return this.fcoeVsanId;
	}

	public void setFcoeVsanId(Integer fcoeVsanId) {
		this.fcoeVsanId = fcoeVsanId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sanVsan")
	public List<SanVhbaTemplate> getSanVhbaTemplates() {
		return this.sanVhbaTemplates;
	}

	public void setSanVhbaTemplates(List<SanVhbaTemplate> sanVhbaTemplates) {
		this.sanVhbaTemplates = sanVhbaTemplates;
	}

}
