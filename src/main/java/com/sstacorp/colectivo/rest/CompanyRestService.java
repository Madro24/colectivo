package com.sstacorp.colectivo.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sstacorp.colectivo.dto.CompanyDTO;
import com.sstacorp.colectivo.services.CompanyService;
import com.sstacorp.colectivo.services.ImageService;

@Controller
@RequestMapping(value = "/companies")
public class CompanyRestService {
	@Autowired
	CompanyService companyService;
	
	@Autowired
	ImageService imageService;
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<CompanyDTO>> getCompanies() {
		
		return new ResponseEntity<List<CompanyDTO>>(companyService.getCompanies(), HttpStatus.OK);
 
	}
	
	@RequestMapping(value = "{companyId}" ,method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<CompanyDTO> getCompany(@PathVariable("companyId") Long companyId) {
		
		return new ResponseEntity<CompanyDTO>(companyService.getCompany(companyId), HttpStatus.OK);
 
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<CompanyDTO> createCompany(@RequestBody CompanyDTO companyDto) {
		
		return new ResponseEntity<CompanyDTO>(companyService.createCompany(companyDto),HttpStatus.OK);
 
	}
	
	@RequestMapping(value = "{companyId}" , method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<CompanyDTO> updateCompany(@PathVariable("companyId") Long companyId, @RequestBody CompanyDTO companyDto) {
		
		return new ResponseEntity<CompanyDTO>(companyService.updateCompany(companyDto, companyId),HttpStatus.OK);
 
	}
	
	@RequestMapping(value = "{companyId}/image" , method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Object> uploadImage(@PathVariable("companyId") Long companyId, @RequestParam String imageType , @RequestParam("image") MultipartFile image) throws IOException {
		
		imageService.uploadImage(image, imageType, companyId);
		
		return new ResponseEntity<Object>(HttpStatus.OK);
 
	}
}
