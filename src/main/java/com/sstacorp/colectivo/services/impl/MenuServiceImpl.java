package com.sstacorp.colectivo.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.sstacorp.colectivo.dto.MenuDTO;
import com.sstacorp.colectivo.jpa.entity.Menu;
import com.sstacorp.colectivo.jpa.repositories.MenuRepository;
import com.sstacorp.colectivo.mapping.MenuUtils;
import com.sstacorp.colectivo.services.CompanyService;
import com.sstacorp.colectivo.services.MenuContentService;
import com.sstacorp.colectivo.services.MenuService;
import com.sstacorp.colectivo.validation.dto.MenuValidationDTO;
import com.sstacorp.colectivo.validator.ComponentValidator;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	MenuRepository menuRepository;

	@Autowired
	CompanyService companyService;
	
	@Autowired
	MenuContentService menuContentService;
	
	@Autowired
	ComponentValidator<MenuValidationDTO> menuValidator;
	
	@Override
	public List<MenuDTO> getMenusByCompany(Long companyId) {
		
		menuValidator.validateParams(new MenuValidationDTO(companyId, HttpMethod.GET));
		
		List<Menu> menus = menuRepository.findByCompanyId(companyId);
		
		List<MenuDTO> menusDtoList = new ArrayList<MenuDTO>();
		MenuDTO menuDto = null;
		
		for(Menu menu : menus){
			
			menuDto = MenuUtils.populateDto(menu);
			
			// Populating menu content.
			menuDto.setProducts(menuContentService.getMenuContent(menuDto.getId()));
			
			menusDtoList.add(menuDto);	
		}
		
		return menusDtoList;
	}

	@Override
	public MenuDTO getMenuById(Long companyId,Long menuId) {
		
		// using HttpMethod.TRACE only to differ from getMenusByCompany validations.
		menuValidator.validateParams(new MenuValidationDTO(companyId,menuId,HttpMethod.TRACE));
		
		MenuDTO menuDto = MenuUtils.populateDto(menuRepository.findOne(menuId));
		
		// Populating menu content.
		menuDto.setProducts(menuContentService.getMenuContent(menuId));
		
		return menuDto;

	}

	@Override
	public MenuDTO createMenu(MenuDTO menu, Long companyId) {
		
		menuValidator.validateParams(new MenuValidationDTO(menu, companyId,HttpMethod.POST));
		
		Menu createdMenu = menuRepository.save(MenuUtils.mappedEntity(menu));
		
		// Adding menu content
		if(createdMenu != null){
					
			menu.setId(createdMenu.getId());
			menuContentService.addMenuContent(menu);
		}
		
		return menu;
	}

	@Override
	public MenuDTO updateMenu(MenuDTO menu, Long companyId, Long menuId) {
		
		menuValidator.validateParams(new MenuValidationDTO(menu, companyId,menuId, HttpMethod.PUT));
		
		Menu createdMenu = menuRepository.save(MenuUtils.mappedEntity(menu));
		
		// Updating menu content
		if(createdMenu != null){
			
			menu.setId(createdMenu.getId());
			menuContentService.addMenuContent(menu);
		}
		
		return menu;
	}

	@Override
	public MenuDTO deleteMenu(Long companyId, Long menuId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Boolean exists(Long menuId){
		if(menuId == null) return false;
		
		return (menuRepository.findOne(menuId)) != null ? true : false ; 
	}
	
	

}
