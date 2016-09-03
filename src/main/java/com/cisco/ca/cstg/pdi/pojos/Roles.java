package com.cisco.ca.cstg.pdi.pojos;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Roles implements java.io.Serializable {

	private static final long serialVersionUID = 4445718621969251974L;
	
	private Integer id;
	private String description;
	private Boolean isActive;
	private List<ModuleAccess> moduleAccesses = new ArrayList<ModuleAccess>();

	public Roles() {
	}
	
	public Roles(Integer id) {
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

	@Column(name = "description", length = 45)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "isActive")
	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roles")
	public List<ModuleAccess> getModuleAccesses() {
		return this.moduleAccesses;
	}

	public void setModuleAccesses(List<ModuleAccess> moduleAccesses) {
		this.moduleAccesses = moduleAccesses;
	}

}
