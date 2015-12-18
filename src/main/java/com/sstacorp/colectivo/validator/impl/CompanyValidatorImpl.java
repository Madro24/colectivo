package com.sstacorp.colectivo.validator.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sstacorp.colectivo.errors.ErrorTypes;
import com.sstacorp.colectivo.exceptions.ErrorMessage;
import com.sstacorp.colectivo.services.CompanyService;
import com.sstacorp.colectivo.validation.dto.CompanyValidationDTO;
import com.sstacorp.colectivo.validator.CompanyValidator;

@Component
public class CompanyValidatorImpl implements CompanyValidator {
	
	@Autowired
	CompanyService companyService;
	
	@Override
	public void validateParams(CompanyValidationDTO validateDto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void validateCompany(Long companyId, List<ErrorMessage> errors ){
		if(companyId == null){
			errors.add(new ErrorMessage(ErrorTypes.ERROR_COMPANY_ID_MISSING));
		}
		else if(!companyService.exists(companyId)){
			errors.add(new ErrorMessage(ErrorTypes.ERROR_COMPANY_NOT_EXISTS));
		}
	}

}
