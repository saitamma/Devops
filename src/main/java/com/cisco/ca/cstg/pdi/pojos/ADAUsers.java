package com.cisco.ca.cstg.pdi.pojos;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ada_users")
public class ADAUsers implements Serializable {

	private static final long serialVersionUID = 945988824067303874L;
	private Integer id;
	private String name;
	private String userId;
	private String mailId;
	private Date createdDate;
	private int isActive;
	private UserRoles userRoles;
	private Integer createdBy;
	private String userRolesName;
	
	public ADAUsers() {
	}

	public ADAUsers(Integer id) {
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

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "user_id")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "mail_id")
	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	@Column(name = "isActive")
	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	@Column(name = "created_date")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "createdBy")
	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role")
	public UserRoles getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(UserRoles userRoles) {
		this.userRoles = userRoles;
	}
	
	@Transient
	public String getUserRolesName() {
		return userRolesName;
	}

	public void setUserRolesName(String userRolesName) {
		this.userRolesName = userRolesName;
	}
		
}
