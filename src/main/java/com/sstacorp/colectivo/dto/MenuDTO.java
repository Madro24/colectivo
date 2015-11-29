package com.sstacorp.colectivo.dto;

import java.util.List;

public class MenuDTO {
	private Long id;
	private String name;
	private String menuTypeCode;
	private String statusCode;
	private Long companyId;
	private List<ProductDTO> products;
	
	public MenuDTO() {
		super();
	}

	public MenuDTO(Long id, String name, String menuTypeCode,
			String statusCode, Long companyId, List<ProductDTO> products) {
		super();
		this.id = id;
		this.name = name;
		this.menuTypeCode = menuTypeCode;
		this.statusCode = statusCode;
		this.companyId = companyId;
		this.products = products;
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
	public String getMenuTypeCode() {
		return menuTypeCode;
	}
	public void setMenuTypeCode(String menuTypeCode) {
		this.menuTypeCode = menuTypeCode;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public List<ProductDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}
	
}
