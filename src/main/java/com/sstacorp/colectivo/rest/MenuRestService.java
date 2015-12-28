package com.sstacorp.colectivo.rest;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sstacorp.colectivo.dto.MenuDTO;
import com.sstacorp.colectivo.services.MenuService;
 
@Controller
@RequestMapping(value = "companies/{companyId}/menus")
public class MenuRestService {
	
	@Autowired
	MenuService menuService;
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<MenuDTO>>  getCompanysMenus(@PathVariable("companyId") Long companyId) {
		
		return new ResponseEntity<List<MenuDTO>>(menuService.getMenusByCompany(companyId),HttpStatus.OK);
 
	}
	
	@RequestMapping(value = "{menuId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<MenuDTO>  getMenuById(@PathVariable("companyId") Long companyId, @PathVariable("menuId") Long menuId) {
		
		return new ResponseEntity<MenuDTO>(menuService.getMenuById(companyId, menuId),HttpStatus.OK);
 
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<MenuDTO>  postCreateMenu(
			@PathVariable("companyId") Long companyId,
			@RequestBody MenuDTO menu) {
		
		return new ResponseEntity<MenuDTO>(menuService.createMenu(menu, companyId),HttpStatus.OK);
 
	}
	
	@RequestMapping(value = "{menuId}",method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<MenuDTO>  putUpdateMenu(
			@PathVariable("companyId") Long companyId,
			@PathVariable("menuId") Long menuId,
			@RequestBody MenuDTO menu) {
		
		return new ResponseEntity<MenuDTO>(menuService.updateMenu(menu, companyId, menuId),HttpStatus.OK);
 
	}
	
	@RequestMapping(value = "{menuId}",method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<Object>  deleteMenu(
			@PathVariable("companyId") Long companyId,
			@PathVariable("menuId") Long menuId) {
		
		menuService.deleteMenu(companyId, menuId);
		
		return new ResponseEntity<Object>(HttpStatus.OK);
 
	}
}
