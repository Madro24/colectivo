package com.sstacorp.colectivo.services;

import java.util.List;

import com.sstacorp.colectivo.dto.MenuDTO;


public interface MenuService {
	List<MenuDTO> getMenusByCompany(Long companyId);
	MenuDTO getMenuById(Long companyId,Long menuId);
	MenuDTO createMenu(MenuDTO menu, Long companyId);
	MenuDTO updateMenu(MenuDTO menu, Long companyId, Long menuId);
	MenuDTO deleteMenu(Long companyId, Long menuId);
	Boolean exists(Long menuId);
}
