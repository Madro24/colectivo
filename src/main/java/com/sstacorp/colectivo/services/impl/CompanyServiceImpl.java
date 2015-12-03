package com.sstacorp.colectivo.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sstacorp.colectivo.constants.CompanyConstants;
import com.sstacorp.colectivo.dto.CompanyDTO;
import com.sstacorp.colectivo.dto.ImageDTO;
import com.sstacorp.colectivo.errors.ErrorTypes;
import com.sstacorp.colectivo.exceptions.ErrorMessage;
import com.sstacorp.colectivo.exceptions.ErrorMessageException;
import com.sstacorp.colectivo.exceptions.ErrorUtil;
import com.sstacorp.colectivo.jpa.entity.Company;
import com.sstacorp.colectivo.jpa.entity.ImageRelation;
import com.sstacorp.colectivo.jpa.repositories.CompanyRepository;
import com.sstacorp.colectivo.jpa.repositories.ImageRelationRepository;
import com.sstacorp.colectivo.services.CompanyService;
import com.sstacorp.colectivo.util.mapping.CompanyUtils;
import com.sstacorp.colectivo.util.mapping.ImageUtils;

@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	ImageRelationRepository imageRelationRepository;

	@Override
	public List<CompanyDTO> getCompanies() {
		Iterable<Company> companies = companyRepository.findAll();
		
		Iterator<Company> companyIter = companies.iterator();
		
		List<CompanyDTO> companyDTOs = new ArrayList<CompanyDTO>();
		while(companyIter.hasNext()){
			CompanyDTO company = CompanyUtils.populateDTO(companyIter.next());
			
			// Adding image details
			addingImageDetails(company);
		
			companyDTOs.add(company);
		}
		
		return companyDTOs;
	}

	@Override
	public CompanyDTO getCompany(Long id) {
		
		if( id == null){
			throw new ErrorMessageException(new ErrorMessage(ErrorTypes.ERROR_COMPANY_ID_MISSING),HttpStatus.BAD_REQUEST); 
		}
		
		CompanyDTO company = CompanyUtils.populateDTO(companyRepository.findOne(id));
		
		// Adding image details
		addingImageDetails(company);
		
		return company;
	}
	
	@Override
	public CompanyDTO createCompany(CompanyDTO companyDto) {
		
		// Validate inputs and throw an exception if it fails.
		ErrorUtil.handleErrors(validateCompany(companyDto, CompanyConstants.REQUEST_CREATE, null));
		
		// Save entity.
		Company company = companyRepository.save(CompanyUtils.mappedEntity(companyDto));
		
		return CompanyUtils.populateDTO(company);
	}

	@Override
	public void deleteCompany(CompanyDTO company) {
		// TODO Auto-generated method stub

	}

	@Override
	public CompanyDTO updateCompany(CompanyDTO companyDto, Long companyId) {
		
		// Validate inputs and throw an exception if it fails.
		ErrorUtil.handleErrors(validateCompany(companyDto, CompanyConstants.REQUEST_UPDATE, companyId));
		
		// Save entity.
		Company company = companyRepository.save(CompanyUtils.mappedEntity(companyDto));
		
		return CompanyUtils.populateDTO(company);
	}

	@Override
	public boolean exists(Long companyId){
		if(companyId == null) return false;
		
		return (companyRepository.findOne(companyId) != null) ? true: false;
		
	}
	
	private List<ErrorMessage> validateCompany(CompanyDTO company, String requestType, Long ciaID){
		List<ErrorMessage> errors = new ArrayList<ErrorMessage>();
		
		if(company == null) {
			errors.add(new ErrorMessage(ErrorTypes.ERROR_COMPANY_MISSING)); 
		}
		
		else{
			
			if(requestType.equals(CompanyConstants.REQUEST_UPDATE)){
				if(ciaID == null){
					errors.add(new ErrorMessage(ErrorTypes.ERROR_COMPANY_ID_MISSING));
				}
				else if(!ciaID.equals(company.getId())){
					errors.add(new ErrorMessage(ErrorTypes.ERROR_COMPANY_ID_MISMATCH));
				}
				else if(companyRepository.findOne(ciaID) == null){
					errors.add(new ErrorMessage(ErrorTypes.ERROR_COMPANY_NOT_EXISTS));
				}
				
			}
			
			if(company.getName() == null || company.getName().length() == 0){
				errors.add(new ErrorMessage(ErrorTypes.ERROR_COMPANY_NAME_MISSING));
			}
			else if(company.getName().length() > CompanyConstants.MAX_COMPANY_NAME){
				errors.add(new ErrorMessage(ErrorTypes.ERROR_COMPANY_NAME_TOO_LONG));
			}
			// Validate if company already exists for creating method.
			else if(requestType.equals(CompanyConstants.REQUEST_CREATE) 
					&& !companyRepository.findByName(company.getName()).isEmpty()){
				errors.add(new ErrorMessage(ErrorTypes.ERROR_COMPANY_NAME_DUPLICATED));
			}
			
			if(company.getDisplayName() == null || company.getDisplayName().length() == 0){
				errors.add(new ErrorMessage(ErrorTypes.ERROR_COMPANY_DISPLAY_NAME_MISSING));
			}
			else if(company.getDisplayName().length() > CompanyConstants.MAX_COMPANY_DISPLAY_NAME){
				errors.add(new ErrorMessage(ErrorTypes.ERROR_COMPANY_DISPLAY_NAME_TOO_LONG));
			}	
		}
		return errors;
	}
	
	public void addingImageDetails(CompanyDTO company){
		if(company == null) return;
		
		List<ImageDTO> imagesDto = new ArrayList<ImageDTO>();
		
		List<ImageRelation> images = imageRelationRepository.findByTargetId(company.getId());
		for(ImageRelation imageRelation : images){
			imagesDto.add(ImageUtils.populateDTO(imageRelation));
		}
		
		company.setImages(imagesDto);
	}

	
}
