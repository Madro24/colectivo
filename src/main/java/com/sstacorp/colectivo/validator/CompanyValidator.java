package com.sstacorp.colectivo.validator;

import java.util.List;

import com.sstacorp.colectivo.exceptions.ErrorMessage;
import com.sstacorp.colectivo.validation.dto.CompanyValidationDTO;

public interface CompanyValidator extends
		ComponentValidator<CompanyValidationDTO> {

	void validateCompany(Long companyId, List<ErrorMessage> errors );
}
