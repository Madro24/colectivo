package com.sstacorp.colectivo.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sstacorp.colectivo.dto.MenuDTO;
import com.sstacorp.colectivo.dto.ProductDTO;
import com.sstacorp.colectivo.jpa.entity.MenuContent;
import com.sstacorp.colectivo.jpa.repositories.MenuContentRepository;
import com.sstacorp.colectivo.services.MenuContentService;

@Service
public class MenuContentServiceImpl implements MenuContentService {

	@Autowired
	MenuContentRepository menuContentRepository;
	
	@Override
	public void addMenuContent(MenuDTO menuDto) {
		
		MenuContent menuContentEntity = null;
		List<MenuContent> newMenuContentList = null;
		List<MenuContent> removeMenuContentList = null;
		List<MenuContent> currentMenuContentList = null;
		
		List<Long> currentProductIdList = new ArrayList<Long>();

		
		if(menuDto != null && menuDto.getId() != null && !CollectionUtils.isEmpty(menuDto.getProducts())){
			newMenuContentList = new ArrayList<MenuContent>();
			
			currentMenuContentList = menuContentRepository.findByMenuId(menuDto.getId());
			
			for(MenuContent menuContent: currentMenuContentList){
				currentProductIdList.add(menuContent.getProductId());
			}
			
			
			for(ProductDTO productDto: menuDto.getProducts()){
			
				// Add new products to menu.
				if(!currentProductIdList.contains(productDto.getId())){
					menuContentEntity = new MenuContent();
					menuContentEntity.setMenuId(menuDto.getId());
					menuContentEntity.setProductId(productDto.getId());
					
					newMenuContentList.add(menuContentEntity);
				}

			}
			
		}
		
		menuContentRepository.save(menuContentList);
		
	}

}
