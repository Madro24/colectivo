package com.sstacorp.colectivo.util.mapping;

import org.springframework.beans.BeanUtils;

public class MappingUtilsAbstract {
	
	static <T,K> void populateCommonDto(T dto, K entity){
		BeanUtils.copyProperties(entity, dto);
	}
	
	static <T, K> void mappedCommonEntity(T dto, K entity){
		BeanUtils.copyProperties(dto, entity);
	}
	
}
