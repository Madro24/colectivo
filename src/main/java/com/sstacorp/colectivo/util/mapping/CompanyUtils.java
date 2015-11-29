package com.sstacorp.colectivo.util.mapping;

import com.sstacorp.colectivo.dto.CompanyDTO;
import com.sstacorp.colectivo.jpa.entity.Company;

public class CompanyUtils {

	public static CompanyDTO populateDTO(Company entity) {
		if(entity == null) return null;
		
		CompanyDTO objDto = new CompanyDTO();
		objDto.setId(entity.getId());
		objDto.setName(entity.getName());
		objDto.setDisplayName(entity.getDisplayName());
		objDto.setDescription(entity.getDescription());
		objDto.setAddress(entity.getAddress());
		objDto.setPhone(entity.getPhone());
		objDto.setRfc(entity.getRfc());
		objDto.setEmployees(null);
		
		return objDto;
	}
	
	public static Company mappedEntity(CompanyDTO dto){
		if(dto == null) return null;
		
		Company entity = new Company();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setDisplayName(dto.getDisplayName());
		entity.setDescription(dto.getDescription());
		entity.setAddress(dto.getAddress());
		entity.setPhone(dto.getPhone());
		entity.setRfc(dto.getRfc());
	
		return entity;
	}

}
