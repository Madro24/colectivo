package com.sstacorp.colectivo.jpa.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sstacorp.colectivo.jpa.entity.MenuContent;

public interface MenuContentRepository extends
		CrudRepository<MenuContent, Long> {

	MenuContent findByMenuIdAndProductId(Long menuId, Long productId);
	List<MenuContent> findByMenuId(Long menuId);
}
