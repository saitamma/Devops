package com.cisco.ca.cstg.pdi.pojos;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * MainMenuState generated by hbm2java
 */
@Entity
@Table(name = "main_menu_state")
public class MainMenuState implements java.io.Serializable {

	private static final long serialVersionUID = -7992613367244224153L;
	private int id;
	private String name;
	private List<SubMenuState> subMenuStates = new ArrayList<SubMenuState>();
	private List<WizardStatus> wizardStatuses = new ArrayList<WizardStatus>();

	public MainMenuState() {
	}

	public MainMenuState(int id) {
		this.id = id;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "name", length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "mainMenuState")
	public List<SubMenuState> getSubMenuStates() {
		return this.subMenuStates;
	}

	public void setSubMenuStates(List<SubMenuState> subMenuStates) {
		this.subMenuStates = subMenuStates;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mainMenuState")
	public List<WizardStatus> getWizardStatuses() {
		return this.wizardStatuses;
	}

	public void setWizardStatuses(List<WizardStatus> wizardStatuses) {
		this.wizardStatuses = wizardStatuses;
	}

}