package com.sstacorp.colectivo.validation.dto;

import com.sstacorp.colectivo.dto.ProductDTO;
import com.sstacorp.colectivo.validation.types.ProductValidationTypes;

public class ProductValidationDTO {
	private ProductDTO productDto;
	private Long companyId;
	private Long menuId;
	private Long productId;
	private ProductValidationTypes validationType;
	
	public ProductValidationDTO(){}
	
	public ProductValidationDTO(ProductDTO product, Long companyId) {
		this.companyId = companyId;
		this.productDto = product;
		this.validationType = ProductValidationTypes.PRODUCT_DTO_CIA;
	}
	public ProductValidationDTO(ProductDTO product, Long companyId,
			Long productId) {
		this.productDto = product;
		this.companyId = companyId;
		this.productId = productId;
		this.validationType = ProductValidationTypes.PRODUCT_DTO_CIA_PR_ID;
	}
	public ProductValidationDTO(Long companyId, Long productId) {
		this.companyId = companyId;
		this.productId = productId;
		this.validationType = ProductValidationTypes.CIA_PRODUCT_ID;
	}
	public ProductValidationDTO(Long productId) {
		this.productId = productId;
		this.validationType = ProductValidationTypes.PRODUCT_ID;
	}
	
	public static ProductValidationDTO createProductValidationCompanyMenu(Long companyId, Long menuId){
		ProductValidationDTO dto = new ProductValidationDTO();
		dto.setCompanyId(companyId);
		dto.setMenuId(menuId);
		dto.setValidationType(ProductValidationTypes.CIA_MENU);
		
		return dto;
	}
	
	public static ProductValidationDTO createProductValidationCompany(Long companyId){
		ProductValidationDTO dto = new ProductValidationDTO();
		dto.setCompanyId(companyId);
		dto.setValidationType(ProductValidationTypes.CIA);
		
		return dto;
	}
	
	public ProductDTO getProductDto() {
		return productDto;
	}
	public void setProductDto(ProductDTO productDto) {
		this.productDto = productDto;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public ProductValidationTypes getValidationType() {
		return validationType;
	}
	public void setValidationType(ProductValidationTypes validationType) {
		this.validationType = validationType;
	}
}
