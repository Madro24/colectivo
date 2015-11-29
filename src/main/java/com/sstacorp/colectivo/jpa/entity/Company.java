package com.sstacorp.colectivo.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COMPANY")
public class Company extends EntityTrackProperties{
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DISPLAY_NAME")
	private String displayName;
	
	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "PHONE")
	private String phone;
	
	@Column(name = "RFC")
	private String rfc;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
