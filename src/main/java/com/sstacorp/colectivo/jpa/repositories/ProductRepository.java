package com.sstacorp.colectivo.jpa.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.sstacorp.colectivo.jpa.entity.Product;

public interface ProductRepository extends Serializable,
		CrudRepository<Product, Long> {

	Product findByIdAndCompanyId(Long productId, Long companyId);
	List<Product> findByCompanyId(Long companyId);
	
	@Query(value = "SELECT P.* FROM PRODUCT P "
			+ "INNER JOIN MENU_CONTENT MC ON P.ID = MC.PRODUCT_ID "
			+ "WHERE P.COMPANY_ID = ?1 AND MC.MENU_ID = ?2", 
			nativeQuery = true)
    public List<Product> findByCompanyIdMenuId(Long companyId, Long menuId);
	
}
