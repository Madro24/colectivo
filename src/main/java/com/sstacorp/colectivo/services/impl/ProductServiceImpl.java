package com.sstacorp.colectivo.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sstacorp.colectivo.catalogs.ImageTypes;
import com.sstacorp.colectivo.constants.ProductConstants;
import com.sstacorp.colectivo.dto.ProductDTO;
import com.sstacorp.colectivo.jpa.entity.Product;
import com.sstacorp.colectivo.jpa.repositories.ProductRepository;
import com.sstacorp.colectivo.mapping.ProductUtils;
import com.sstacorp.colectivo.services.ImageService;
import com.sstacorp.colectivo.services.ProductService;
import com.sstacorp.colectivo.validation.dto.ProductValidationDTO;
import com.sstacorp.colectivo.validator.ComponentValidator;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ImageService imageService;
	
	@Autowired
	ComponentValidator<ProductValidationDTO> productValidator;
	
	@Override
	public ProductDTO addProduct(ProductDTO product, Long companyId) {
		
		// validate inputs
		ProductValidationDTO validateDto = new ProductValidationDTO(product, companyId);	
		productValidator.validateParams(validateDto);
		
		return saveProduct(product);
	}


	@Override
	public ProductDTO updateProduct(ProductDTO product, Long companyId, Long productId) {
		
		// validate inputs
		ProductValidationDTO validateDto = new ProductValidationDTO(product, companyId, productId);	
		productValidator.validateParams(validateDto);
		
		return saveProduct(product);
	}

	@Override
	public void removeProduct(Long companyId, Long productId) {

		// validate inputs
		ProductValidationDTO validateDto = new ProductValidationDTO(companyId, productId);
		productValidator.validateParams(validateDto);
		
		removeProduct(productId);

	}


	@Override
	public Long uploadImage(Long companyId, Long productId, String imageType, MultipartFile image) throws IOException {

		// validate inputs
		ProductValidationDTO validateDto = new ProductValidationDTO(companyId, productId);
		productValidator.validateParams(validateDto);
		
		// default image type
		if(imageType == null || imageType.isEmpty()){
			imageType = ImageTypes.PRODUCT_MAIN.getCode();
		}
		
		imageService.uploadImage(image, imageType, productId);
		
		return null;
	}


	@Override
	public List<ProductDTO> getProductsList(Long companyId) {

		// validate inputs
		ProductValidationDTO validateDto = ProductValidationDTO.createProductValidationCompany(companyId);
		productValidator.validateParams(validateDto);
		
		return populateProductDTOList(productRepository.findByCompanyId(companyId));
	}

	@Override
	public List<ProductDTO> getProductsList(Long companyId, Long menuId) {

		// validate inputs
		ProductValidationDTO validateDto = ProductValidationDTO.createProductValidationCompanyMenu(companyId, menuId); 
		productValidator.validateParams(validateDto);
		
		return populateProductDTOList(productRepository.findByCompanyIdMenuId(companyId, menuId));
	}

	@Override
	public ProductDTO getProductById(Long productId) {
		
		// validate inputs
		ProductValidationDTO validateDto = new ProductValidationDTO(productId);
		productValidator.validateParams(validateDto);
		
		return populateDtoImages(productRepository.findOne(productId));
	}


	@Override
	public ProductDTO getProductById(Long companyId, Long productId) {
		
		// validate inputs
		ProductValidationDTO validateDto = new ProductValidationDTO(companyId, productId);
		productValidator.validateParams(validateDto);
		
		return getProductById(productId);
	}
	

	@Override
	public boolean exists(Long productId){
		
		if(productId == null) return false;
		
		return (productRepository.findOne(productId) != null) ? true: false;
	}


	@Override
	public boolean productBelongsToCompany(Long companyId, Long productId) {
		
		if(productId == null || companyId == null) return false;
		
		return (productRepository.findByIdAndCompanyId(productId, companyId) != null) ? true: false;
	}
	
	private List<ProductDTO> populateProductDTOList(List<Product> productsList){
		
		if(CollectionUtils.isEmpty(productsList)) return null;
		
		List<ProductDTO> products = new ArrayList<ProductDTO>();
		ProductDTO dto = null;
		for(Product entity : productsList){
			dto = populateDtoImages(entity);
		
			products.add(dto);
		}
		
		return products;
	}
	
	private void removeProduct(Long productId) {
		// TODO Auto-generated method stub

	}
	
	
	private ProductDTO saveProduct(ProductDTO productDto) {
		
		// validate inputs
		Product product = ProductUtils.mappedEntity(productDto);
		product = productRepository.save(product);
		
		return populateDtoImages(product);
	}
	
	private ProductDTO populateDtoImages(Product entity){
		
		ProductDTO dto = ProductUtils.populateDto(entity);
		
		// Adding image details
		dto.setImages(imageService.getImageIdReferences(dto.getId(), ProductConstants.PRODUCT_IMAGE_TYPES));
		
		return dto;
	}
	
}
