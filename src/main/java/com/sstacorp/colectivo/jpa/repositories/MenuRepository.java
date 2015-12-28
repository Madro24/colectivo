package com.sstacorp.colectivo.jpa.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sstacorp.colectivo.jpa.entity.Menu;

public interface MenuRepository extends CrudRepository<Menu, Long> {
	List<Menu> findByCompanyId(Long companyId);
}
