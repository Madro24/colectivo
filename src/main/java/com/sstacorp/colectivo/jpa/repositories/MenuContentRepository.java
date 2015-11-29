package com.sstacorp.colectivo.jpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.sstacorp.colectivo.jpa.entity.MenuContent;

public interface MenuContentRepository extends
		CrudRepository<MenuContent, Long> {

}
