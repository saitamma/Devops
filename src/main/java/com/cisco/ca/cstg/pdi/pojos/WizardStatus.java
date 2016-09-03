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

@Entity
@Table(name = "wizard_status")
public class WizardStatus implements java.io.Serializable {

	private static final long serialVersionUID = 2180045250683815464L;
	private Integer id;
	private ProjectDetails projectDetails;
	private MainMenuState mainMenuState;
	private Integer subMenuId;
	private Boolean isActive;
	private Boolean isCompleted;

	public WizardStatus() {
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "main_menu_id")
	public MainMenuState getMainMenuState() {
		return this.mainMenuState;
	}

	public void setMainMenuState(MainMenuState mainMenuState) {
		this.mainMenuState = mainMenuState;
	}

	@Column(name = "sub_menu_id")
	public Integer getSubMenuId() {
		return this.subMenuId;
	}

	public void setSubMenuId(Integer subMenuId) {
		this.subMenuId = subMenuId;
	}

	@Column(name = "isActive")
	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Column(name = "isCompleted")
	public Boolean getIsCompleted() {
		return this.isCompleted;
	}

	public void setIsCompleted(Boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
}
