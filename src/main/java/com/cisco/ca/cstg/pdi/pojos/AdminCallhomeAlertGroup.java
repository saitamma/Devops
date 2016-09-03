package com.cisco.ca.cstg.pdi.pojos;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admin_callhome_alert_group")
public class AdminCallhomeAlertGroup implements java.io.Serializable {

	private static final long serialVersionUID = -327441825905088847L;
	private Integer id;
	private String name;
	private String xmlValue;
	
	public AdminCallhomeAlertGroup() {
	}

	public AdminCallhomeAlertGroup(Integer id) {
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

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "xml_value")
	public String getXmlValue() {
		return this.xmlValue;
	}

	public void setXmlValue(String xmlValue) {
		this.xmlValue = xmlValue;
	}

}
