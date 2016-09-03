package com.cisco.ca.cstg.pdi.pojos;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_roles")
public class UserRoles implements java.io.Serializable {

	private static final long serialVersionUID = -2065213967727192291L;
	private Integer id;
	private String user_role;

	public UserRoles() {
	}

	public UserRoles(Integer id) {
		this.id = id;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "user_role", length = 50)
	public String getUserRole() {
		return user_role;
	}

	public void setUserRole(String user_role) {
		this.user_role = user_role;
	}

}
