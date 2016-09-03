package com.cisco.ca.cstg.pdi.pojos;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "time_zone")
public class TimeZone implements java.io.Serializable {

	private static final long serialVersionUID = -4910172052091607318L;
	private Integer id;
	private String countryCode;
	private String countryTimeZone;
	private String comments;

	public TimeZone() {
	}

	public TimeZone(Integer id) {
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

	@Column(name = "country_code", length = 10)
	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Column(name = "timezone", length = 45)
	public String getCountryTimeZone() {
		return this.countryTimeZone;
	}

	public void setCountryTimeZone(String countryTimeZone) {
		this.countryTimeZone = countryTimeZone;
	}

	@Column(name = "comments", length = 75)
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
}
