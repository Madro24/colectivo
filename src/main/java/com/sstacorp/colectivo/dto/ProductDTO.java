package com.sstacorp.colectivo.dto;

import com.sstacorp.colectivo.jpa.entity.Product;

public class ProductDTO {
	private Long id;
	private String name;
	private String description;
	private Double price;
	private Long imageId;
	private String typeCode;
	private Long companyId;
	
	public ProductDTO() {
		super();
	}
	
	public ProductDTO(Long id, String name, String description, Double price,
			Long imageId, String typeCode, Long companyId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imageId = imageId;
		this.typeCode = typeCode;
		this.companyId = companyId;
	}

	public ProductDTO(Product product){
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.price = product.getPrice();
		this.typeCode = product.getTypeCode();
		this.companyId = product.getCompanyId();
	}
	
	public Product getProductEntity(){
		Product product = new Product();
		product.setId(id);
		product.setName(name);
		product.setDescription(description);
		product.setPrice(price);
		product.setTypeCode(typeCode);
		product.setCompanyId(companyId);
		return product;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Long getImageId() {
		return imageId;
	}
	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	
	
}
