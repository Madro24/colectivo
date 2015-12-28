package com.sstacorp.colectivo.validator.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sstacorp.colectivo.catalogs.MenuTypes;
import com.sstacorp.colectivo.catalogs.StatusCodes;
import com.sstacorp.colectivo.constants.MenuConstants;
import com.sstacorp.colectivo.dto.MenuDTO;
import com.sstacorp.colectivo.errors.ErrorTypes;
import com.sstacorp.colectivo.exceptions.ErrorMessage;
import com.sstacorp.colectivo.exceptions.ErrorUtil;
import com.sstacorp.colectivo.services.MenuService;
import com.sstacorp.colectivo.validation.dto.MenuValidationDTO;
import com.sstacorp.colectivo.validator.CompanyValidator;
import com.sstacorp.colectivo.validator.MenuValidator;

@Component
public class MenuValidatorImpl implements MenuValidator{
	@Autowired
	CompanyValidator companyValidator;
	
	@Autowired
	MenuService menuService;
	
	public void validateParams(MenuValidationDTO validateDto){
		List<ErrorMessage> errors = new ArrayList<ErrorMessage>();

		switch(validateDto.getRequestType()){
		case GET:
			companyValidator.validateCompany(validateDto.getCompanyId(), errors);
			break;
		case TRACE:
			companyValidator.validateCompany(validateDto.getCompanyId(), errors);
			validateMenu(validateDto.getMenuId(), errors);
			break;
		case POST:
			validateMenuInput(validateDto.getMenu(), errors);
			break;
		case PUT:
			companyValidator.validateCompany(validateDto.getCompanyId(), errors);
			validateMatchMenuId(validateDto, errors);
			validateMenuInput(validateDto.getMenu(), errors);
			break;
		default:
			break;
		}

		ErrorUtil.handleErrors(errors);
		
	}
	
	@Override
	public void validateMenu(Long menuId, List<ErrorMessage> errors ){
		if(menuId == null){
			errors.add(new ErrorMessage(ErrorTypes.ERROR_MISSING_MENU_ID));
		}
		else if(!menuService.exists(menuId)){
			errors.add(new ErrorMessage(ErrorTypes.ERROR_INVALID_MENU_ID));
		}
	}
	
	@Override
	public void validateMenuInput(MenuDTO menu, List<ErrorMessage> errors){
		if(menu.getCompanyId() == null){
			errors.add(new ErrorMessage(ErrorTypes.ERROR_COMPANY_ID_MISSING));
		}
		else{
			companyValidator.validateCompany(menu.getCompanyId(), errors);
		}
		
		if(menu.getName() == null || menu.getName().isEmpty()){
			errors.add(new ErrorMessage(ErrorTypes.ERROR_MISSING_MENU_NAME));
		}
		else if(menu.getName().length() > MenuConstants.MAX_LENGTH_MENU_NAME){
			errors.add(new ErrorMessage(ErrorTypes.ERROR_MENU_NAME_TOO_LONG));
		}
		
		// Validate menu type code
		try {
			MenuTypes.valueOf(menu.getMenuTypeCode());
		} catch (IllegalArgumentException ae) {
			errors.add(new ErrorMessage(ErrorTypes.ERROR_INVALID_MENU_TYPE_CODE));
		} catch (NullPointerException ne) {
			errors.add(new ErrorMessage(ErrorTypes.ERROR_MISSING_MENU_TYPE_CODE));
		}
		
		// Validate status code
		try {
			StatusCodes.valueOf(menu.getStatusCode());
		} catch (IllegalArgumentException ae) {
			errors.add(new ErrorMessage(ErrorTypes.ERROR_INVALID_MENU_STATUS_CODE));
		} catch (NullPointerException ne) {
			errors.add(new ErrorMessage(ErrorTypes.ERROR_MISSING_MENU_STATUS_CODE));
		}
	}
	
	@Override
	public void validateMatchMenuId(MenuValidationDTO validateDto, List<ErrorMessage> errors){
		if(!validateDto.getMenuId().equals(validateDto.getMenu().getId())){
			errors.add(new ErrorMessage(ErrorTypes.ERROR_MISMATCH_MENU_ID));
		}
	}
}
