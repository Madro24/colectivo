package com.sstacorp.colectivo.dto;

import java.util.List;

public class CompanyDTO {
	private Long id;
	private String name;
	private String displayName;
	private String description;
	private String address;
	private String phone;
	private String rfc;
	private List<ParticipantDTO> employees;
	private List<ImageDTO> images;
	
	public CompanyDTO() {
		super();
	}

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
	public List<ParticipantDTO> getEmployees() {
		return employees;
	}
	public void setEmployees(List<ParticipantDTO> employees) {
		this.employees = employees;
	}

	public List<ImageDTO> getImages() {
		return images;
	}

	public void setImages(List<ImageDTO> images) {
		this.images = images;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
