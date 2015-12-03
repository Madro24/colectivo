package com.sstacorp.colectivo.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.sstacorp.colectivo.dto.MenuDTO;
import com.sstacorp.colectivo.errors.ErrorTypes;
import com.sstacorp.colectivo.exceptions.ErrorMessage;
import com.sstacorp.colectivo.exceptions.ErrorUtil;
import com.sstacorp.colectivo.jpa.entity.Menu;
import com.sstacorp.colectivo.jpa.repositories.MenuRepository;
import com.sstacorp.colectivo.services.CompanyService;
import com.sstacorp.colectivo.services.MenuService;
import com.sstacorp.colectivo.util.mapping.MenuUtils;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	MenuRepository menuRepository;

	@Autowired
	CompanyService companyService;
	
	@Override
	public List<MenuDTO> getMenusByCompany(Long companyId) {
		validateParams(null,companyId,null,HttpMethod.GET);
		
		List<Menu> menus = menuRepository.findByCompanyId(companyId);
		
		List<MenuDTO> menusDto = new ArrayList<MenuDTO>();
		for(Menu menu : menus){
			menusDto.add(MenuUtils.populateDto(menu));
		}
		
		return menusDto;
	}

	@Override
	public MenuDTO getMenuById(Long companyId,Long menuId) {
		
		// using HttpMethod.TRACE only to differ from getMenusByCompany validations.
		validateParams(null,companyId,menuId,HttpMethod.TRACE);
		
		return MenuUtils.populateDto(menuRepository.findOne(menuId));

	}

	@Override
	public MenuDTO createMenu(MenuDTO menu, Long companyId) {
		validateParams(menu,companyId,null,HttpMethod.POST);
		
		Menu createdMenu = menuRepository.save(MenuUtils.mappedEntity(menu));
		
		return MenuUtils.populateDto(createdMenu);
	}

	@Override
	public MenuDTO updateMenu(MenuDTO menu, Long companyId, Long menuId) {
		validateParams(menu,companyId,menuId,HttpMethod.PUT);
		
		Menu createdMenu = menuRepository.save(MenuUtils.mappedEntity(menu));
		
		return MenuUtils.populateDto(createdMenu);
	}

	@Override
	public MenuDTO deleteMenu(Long companyId, Long menuId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void validateParams(MenuDTO menu, Long companyId, Long menuId, HttpMethod requestType){
		List<ErrorMessage> errors = new ArrayList<ErrorMessage>();

		if(companyId == null){
			errors.add(new ErrorMessage(ErrorTypes.ERROR_COMPANY_ID_MISSING));
		}
		else if(!companyService.exists(companyId)){
			errors.add(new ErrorMessage(ErrorTypes.ERROR_COMPANY_NOT_EXISTS));
		};
		

		ErrorUtil.handleErrors(errors);
		
		
		
	}

}
