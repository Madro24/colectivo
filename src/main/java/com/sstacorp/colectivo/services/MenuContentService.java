package com.sstacorp.colectivo.services;

import java.util.List;

import com.sstacorp.colectivo.dto.MenuDTO;
import com.sstacorp.colectivo.dto.ProductDTO;

public interface MenuContentService {
	void addMenuContent(MenuDTO menu);
	List<ProductDTO> getMenuContent(Long menuId);
}
