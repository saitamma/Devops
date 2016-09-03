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
@Table(name = "sub_menu_state")
public class SubMenuState implements java.io.Serializable {

	private static final long serialVersionUID = 5126807000461226394L;
	private Integer id;
	private MainMenuState mainMenuState;
	private int subMenuId;
	private String name;

	public SubMenuState() {
	}

	public SubMenuState(int subMenuId) {
		this.subMenuId = subMenuId;
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
	@JoinColumn(name = "main_menu_id")
	public MainMenuState getMainMenuState() {
		return this.mainMenuState;
	}

	public void setMainMenuState(MainMenuState mainMenuState) {
		this.mainMenuState = mainMenuState;
	}

	@Column(name = "sub_menu_id", nullable = false)
	public int getSubMenuId() {
		return this.subMenuId;
	}

	public void setSubMenuId(int subMenuId) {
		this.subMenuId = subMenuId;
	}

	@Column(name = "name", length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
