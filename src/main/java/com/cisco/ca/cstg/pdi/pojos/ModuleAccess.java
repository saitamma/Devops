package com.cisco.ca.cstg.pdi.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ModuleAccess generated by hbm2java
 */
@Entity
@Table(name = "module_access")
public class ModuleAccess implements java.io.Serializable {

	private static final long serialVersionUID = 324892094410906023L;
	private Integer id;
	private Roles roles;
	private Boolean isAllowed;

	public ModuleAccess() {
	}
	
	public ModuleAccess(Integer id) {
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
	@JoinColumn(name = "roles")
	public Roles getRoles() {
		return this.roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	@Column(name = "isAllowed")
	public Boolean getIsAllowed() {
		return this.isAllowed;
	}

	public void setIsAllowed(Boolean isAllowed) {
		this.isAllowed = isAllowed;
	}

}