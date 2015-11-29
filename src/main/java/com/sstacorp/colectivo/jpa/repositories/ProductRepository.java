package com.sstacorp.colectivo.jpa.repositories;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.sstacorp.colectivo.jpa.entity.Product;

public interface ProductRepository extends Serializable,
		CrudRepository<Product, Long> {

}
