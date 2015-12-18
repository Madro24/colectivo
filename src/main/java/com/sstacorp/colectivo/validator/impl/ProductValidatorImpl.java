package com.sstacorp.colectivo.validator.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sstacorp.colectivo.catalogs.ProductTypes;
import com.sstacorp.colectivo.catalogs.StatusCodes;
import com.sstacorp.colectivo.constants.ProductConstants;
import com.sstacorp.colectivo.dto.ProductDTO;
import com.sstacorp.colectivo.errors.ErrorTypes;
import com.sstacorp.colectivo.exceptions.ErrorMessage;
import com.sstacorp.colectivo.services.ProductService;
import com.sstacorp.colectivo.validation.dto.ProductValidationDTO;
import com.sstacorp.colectivo.validator.CompanyValidator;
import com.sstacorp.colectivo.validator.MenuValidator;
import com.sstacorp.colectivo.validator.ProductValidator;

@Component
public class ProductValidatorImpl implements ProductValidator{
	
	@Autowired
	CompanyValidator companyValidator;
	
	@Autowired
	MenuValidator menuValidator;
	
	@Autowired
	ProductService productService;
	
	@Override
	public void validateParams(ProductValidationDTO validateDto) {
		
		List<ErrorMessage> errors = new ArrayList<ErrorMessage>();
		
		switch(validateDto.getValidationType()){
		case CIA:
			companyValidator.validateCompany(validateDto.getCompanyId(), errors);
			break;
		case CIA_MENU:
			companyValidator.validateCompany(validateDto.getCompanyId(), errors);
			menuValidator.validateMenu(validateDto.getMenuId(), errors);
			break;
		case CIA_PRODUCT_ID:
			companyValidator.validateCompany(validateDto.getCompanyId(), errors);
			this.validateProductId(validateDto.getProductId(), errors);
			validateCompanysProduct(validateDto.getCompanyId(), validateDto.getProductId(),errors);
			break;
		case PRODUCT_ID:
			this.validateProductId(validateDto.getProductId(), errors);
			break;
		case PRODUCT_DTO:
			this.validateProductDto(validateDto.getProductDto(), errors);
			break;
		case PRODUCT_DTO_CIA:
			companyValidator.validateCompany(validateDto.getCompanyId(), errors);
			this.validateProductDto(validateDto.getProductDto(), errors);
			break;
		case PRODUCT_DTO_CIA_PR_ID:
			companyValidator.validateCompany(validateDto.getCompanyId(), errors);
			this.validateMatchProductId(validateDto, errors);
			this.validateProductDto(validateDto.getProductDto(), errors);
			this.validateCompanysProduct(validateDto.getProductDto().getCompanyId(), validateDto.getProductDto().getId(),errors);
			break;
		}	
	}


	@Override
	public void validateProductId(Long productId, List<ErrorMessage> errors) {
	
		if(productId == null){
			errors.add(new ErrorMessage(ErrorTypes.ERROR_MISSING_PRODUCT_ID));
		}
		else if(!productService.exists(productId)){
			errors.add(new ErrorMessage(ErrorTypes.ERROR_INVALID_PRODUCT_ID));
		}
	}


	@Override
	public void validateProductDto(ProductDTO product, List<ErrorMessage> errors) {
		
		companyValidator.validateCompany(product.getCompanyId(), errors);
		
		if(product.getName() == null || product.getName().isEmpty()){
			errors.add(new ErrorMessage(ErrorTypes.ERROR_MISSING_PRODUCT_NAME));
		}
		else if(product.getName().length() > ProductConstants.MAX_LENGTH_PRODUCT_NAME){
			errors.add(new ErrorMessage(ErrorTypes.ERROR_PRODUCT_NAME_TOO_LONG));
		}
		
		if(product.getDescription() == null || product.getDescription().isEmpty()){
			errors.add(new ErrorMessage(ErrorTypes.ERROR_MISSING_PRODUCT_DESCRIPTION));
		}
		else if(product.getDescription().length() > ProductConstants.MAX_LENGTH_PRODUCT_DESCRIPTION){
			errors.add(new ErrorMessage(ErrorTypes.ERROR_PRODUCT_DESCRIPTION_TOO_LONG));
		}

		// Validate menu type code
		try {
			ProductTypes.valueOf(product.getTypeCode());
		} catch (IllegalArgumentException ae) {
			errors.add(new ErrorMessage(ErrorTypes.ERROR_INVALID_PRODUCT_TYPE_CODE));
		} catch (NullPointerException ne) {
			errors.add(new ErrorMessage(ErrorTypes.ERROR_MISSING_PRODUCT_TYPE_CODE));
		}
		
		// Validate status code
		try {
			StatusCodes.valueOf(product.getStatusCode());
		} catch (IllegalArgumentException ae) {
			errors.add(new ErrorMessage(ErrorTypes.ERROR_INVALID_PRODUCT_STATUS_CODE));
		} catch (NullPointerException ne) {
			errors.add(new ErrorMessage(ErrorTypes.ERROR_MISSING_PRODUCT_STATUS_CODE));
		}
	}


	@Override
	public void validateMatchProductId(ProductValidationDTO validateDto,
			List<ErrorMessage> errors) {
		if(!validateDto.getProductId().equals(validateDto.getProductDto().getId())){
			errors.add(new ErrorMessage(ErrorTypes.ERROR_MISMATCH_PRODUCT_ID));
		}
		
	}


	@Override
	public void validateMatchCompanyId(ProductValidationDTO validateDto,
			Long companyId, List<ErrorMessage> errors) {
		if(!validateDto.getCompanyId().equals(validateDto.getProductDto().getCompanyId())){
			errors.add(new ErrorMessage(ErrorTypes.ERROR_MISMATCH_COMPANY_ID));
		}
	}
	
	public void validateCompanysProduct(Long companyId,Long productId, List<ErrorMessage> errors){
		if(!productService.productBelongsToCompany(companyId, productId )){
			errors.add(new ErrorMessage(ErrorTypes.ERROR_INVALID_PRODUCT_FOR_COMPANY));
		}
	}

}


