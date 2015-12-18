package com.sstacorp.colectivo.validation.dto;

import com.sstacorp.colectivo.dto.CompanyDTO;

public class CompanyValidationDTO {
	private CompanyDTO companyDto;
	private Long companyId;
	public CompanyDTO getCompanyDto() {
		return companyDto;
	}
	public void setCompanyDto(CompanyDTO companyDto) {
		this.companyDto = companyDto;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	
}
