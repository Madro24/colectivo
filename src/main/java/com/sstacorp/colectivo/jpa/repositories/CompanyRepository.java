package com.sstacorp.colectivo.jpa.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sstacorp.colectivo.jpa.entity.Company;

public interface CompanyRepository extends CrudRepository<Company, Long> {
	List<Company> findByName(String name); 
}
