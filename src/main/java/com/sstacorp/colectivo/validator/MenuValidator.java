package com.sstacorp.colectivo.validator;

import java.util.List;

import com.sstacorp.colectivo.dto.MenuDTO;
import com.sstacorp.colectivo.exceptions.ErrorMessage;
import com.sstacorp.colectivo.validation.dto.MenuValidationDTO;

public interface MenuValidator extends ComponentValidator<MenuValidationDTO>{

	void validateMenu(Long menuId, List<ErrorMessage> errors);
	void validateMenuInput(MenuDTO menu, List<ErrorMessage> errors);
	void validateMatchMenuId(MenuValidationDTO validateDto, List<ErrorMessage> errors);
	
}
