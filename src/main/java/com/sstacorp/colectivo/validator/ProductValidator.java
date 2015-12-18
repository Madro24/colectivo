package com.sstacorp.colectivo.validator;

import java.util.List;

import com.sstacorp.colectivo.dto.ProductDTO;
import com.sstacorp.colectivo.exceptions.ErrorMessage;
import com.sstacorp.colectivo.validation.dto.ProductValidationDTO;

public interface ProductValidator extends
		ComponentValidator<ProductValidationDTO> {

	void validateProductId(Long productId, List<ErrorMessage> errors);
	void validateProductDto(ProductDTO product, List<ErrorMessage> errors);
	void validateMatchProductId(ProductValidationDTO validateDTO, List<ErrorMessage> errors);
	void validateMatchCompanyId(ProductValidationDTO validateDTO, Long companyId, List<ErrorMessage> errors);
}
