package com.mars.india.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ADDRESS")
public class Address implements Serializable {

	private static final long serialVersionUID = 5924361831551833717L;

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer id;
	
	@NotNull(message = "is required")
	@Column(name = "STREET_NO")
	private Integer street_no;
	
	@NotNull(message = "is required")
	@Column(name = "STREET_NAME")
	private String street_name;
	@NotNull(message = "is required")
	@Column(name = "CITY")
	private String city;
	@NotNull(message = "is required")
	@Column(name = "COUNTRY")
	private String country;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStreet_no() {
		return street_no;
	}

	public void setStreet_no(Integer street_no) {
		this.street_no = street_no;
	}

	public String getStreet_name() {
		return street_name;
	}

	public void setStreet_name(String street_name) {
		this.street_name = street_name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
}
