package com.sstacorp.colectivo.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.TransformerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sstacorp.colectivo.dto.MenuDTO;
import com.sstacorp.colectivo.dto.ProductDTO;
import com.sstacorp.colectivo.jpa.entity.MenuContent;
import com.sstacorp.colectivo.jpa.repositories.MenuContentRepository;
import com.sstacorp.colectivo.services.MenuContentService;
import com.sstacorp.colectivo.services.ProductService;

@Service
public class MenuContentServiceImpl implements MenuContentService {

	@Autowired
	MenuContentRepository menuContentRepository;
	
	@Autowired
	ProductService productService;
	
	@Override
	public void addMenuContent(MenuDTO menuDto) {
		
		MenuContent menuContentEntity = null;
		List<MenuContent> addMenuContentList = null;
		List<MenuContent> currentMenuContentList = null;
		List<MenuContent> deleteMenuContentIdList = null;
		
		List<ProductDTO> newProductList = null;
		
		Set<Long> newProductIdList = null;
		
		if(menuDto != null && menuDto.getId() != null && !CollectionUtils.isEmpty(menuDto.getProducts())){
			
			addMenuContentList = new ArrayList<MenuContent>();
			deleteMenuContentIdList = new ArrayList<MenuContent>();
			
			// Getting unique product list from new menu content list.
			newProductList = menuDto.getProducts();	
			newProductIdList = new HashSet((List<Long>) CollectionUtils.collect(newProductList, TransformerUtils.invokerTransformer("getId")));
			
			// Getting products from current menu content.
			currentMenuContentList = menuContentRepository.findByMenuId(menuDto.getId());
			
	
			// Handling new and deleted content.
			for(MenuContent menuContent: currentMenuContentList){		
				if(newProductIdList.contains(menuContent.getProductId())){
					
					// It exists, do not create a new record.
					newProductIdList.remove(menuContent.getProductId());
				}
				else {
					
					// Adding record into delete list.
					deleteMenuContentIdList.add(menuContent);
				}
				
			}
				
			// Creating list for adding new menu content.
			for(Long productId: newProductIdList){
				menuContentEntity = new MenuContent();
				menuContentEntity.setMenuId(menuDto.getId());
				menuContentEntity.setProductId(productId);
					
				addMenuContentList.add(menuContentEntity);
			}
			
			// Removing deleted products from menuContent.
			if(!CollectionUtils.isEmpty(deleteMenuContentIdList)){
				
				menuContentRepository.delete(deleteMenuContentIdList);
			}
			
			
			// Adding new products to menu.
			if(!CollectionUtils.isEmpty(addMenuContentList)){
				
				menuContentRepository.save(addMenuContentList);
			}
			
			
		}

		
	}

	@Override
	public List<ProductDTO> getMenuContent(Long menuId) {
		
		List<ProductDTO> productList = null;
		List<MenuContent> menuContentList = null;
		
		if(menuId != null){
			
			menuContentList = menuContentRepository.findByMenuId(menuId);
			
			if(!CollectionUtils.isEmpty(menuContentList)){
				
				productList = new ArrayList<ProductDTO>();
				
				for(MenuContent menuContent : menuContentList){
				
					productList.add(productService.getProductById(menuContent.getProductId()));	
				}
			}
		}

		return productList;
	}

}
