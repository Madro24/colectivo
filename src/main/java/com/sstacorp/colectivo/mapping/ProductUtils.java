package com.sstacorp.colectivo.mapping;

import com.sstacorp.colectivo.dto.ProductDTO;
import com.sstacorp.colectivo.jpa.entity.Product;

public class ProductUtils extends MappingUtilsAbstract{
	public static ProductDTO populateDto(Product entity){
		ProductDTO dto = new ProductDTO();
		
		populateCommonDto(dto, entity);
		
		return dto;
	}
	
	public static Product mappedEntity(ProductDTO dto){
		Product entity = new Product();
		
		mappedCommonEntity(dto, entity);
		
		return entity;
	}
}
