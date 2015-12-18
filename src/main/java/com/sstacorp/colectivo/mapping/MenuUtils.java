package com.sstacorp.colectivo.mapping;

import com.sstacorp.colectivo.dto.MenuDTO;
import com.sstacorp.colectivo.jpa.entity.Menu;

public class MenuUtils extends MappingUtilsAbstract{
	
	public static MenuDTO populateDto(Menu entity){
		MenuDTO dto = new MenuDTO();
		
		populateCommonDto(dto, entity);
		
		return dto;
	}
	
	public static Menu mappedEntity(MenuDTO dto){
		Menu entity = new Menu();
		
		mappedCommonEntity(dto, entity);
		
		return entity;
	}
	
}
