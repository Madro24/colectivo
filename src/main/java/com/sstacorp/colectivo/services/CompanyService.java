package com.sstacorp.colectivo.services;

import java.util.List;

import com.sstacorp.colectivo.dto.CompanyDTO;


public interface CompanyService {
	List<CompanyDTO> getCompanies();
	CompanyDTO getCompany(Long companyId);
	CompanyDTO createCompany(CompanyDTO companyDto);
	CompanyDTO updateCompany(CompanyDTO company, Long companyId);
	void deleteCompany(CompanyDTO company);
	boolean exists(Long companyId);
}
