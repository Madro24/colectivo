package com.sstacorp.colectivo.rest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sstacorp.colectivo.dto.MenuDTO;
 
@Controller
@RequestMapping(value = "/menus")
public class MenuRestService {
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public @ResponseBody MenuDTO getMenuById(@PathVariable("id") Long menuId) {
		MenuDTO menu = new MenuDTO();
		menu.setId(menuId);
		menu.setName("MyMenu");
		return menu;
 
	}
}
