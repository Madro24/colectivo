package com.sstacorp.colectivo.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sstacorp.colectivo.dto.ProductDTO;
import com.sstacorp.colectivo.services.ImageService;
import com.sstacorp.colectivo.services.ProductService;

@Controller
@RequestMapping(value = "companies/{companyId}")
public class ProductRestService {

	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "products", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<ProductDTO>> getCompanyProducts(
			@PathVariable("companyId") Long companyId
			){
		
		return new ResponseEntity<List<ProductDTO>> (productService.getProductsList(companyId), HttpStatus.OK);
	}
	
	@RequestMapping(value = "products/{productId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<ProductDTO> getProductById(
			@PathVariable("companyId") Long companyId,
			@PathVariable("productId") Long productId
			){
		
		return new ResponseEntity<ProductDTO> (productService.getProductById(companyId, productId ), HttpStatus.OK);
	}
	
	@RequestMapping(value = "menus/{menuId}/products", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<ProductDTO>> getCompanyMenuProducts(
			@PathVariable("companyId") Long companyId,
			@PathVariable("menuId") Long menuId
			){
		
		return new ResponseEntity<List<ProductDTO>> (productService.getProductsList(companyId, menuId), HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "products", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<ProductDTO>  postCreateProduct(
			@PathVariable("companyId") Long companyId,
			@RequestBody ProductDTO product) {
		
		return new ResponseEntity<ProductDTO>(productService.addProduct(product, companyId),HttpStatus.OK);
 
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "products/{productId}", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ProductDTO>  putUpdateProduct(
			@PathVariable("companyId") Long companyId,
			@PathVariable("productId") Long productId,
			@RequestBody ProductDTO product) {
		
		return new ResponseEntity<ProductDTO>(productService.updateProduct(product, companyId, productId),HttpStatus.OK);
 
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "products/{productId}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<Object>  deleteProduct(
			@PathVariable("companyId") Long companyId,
			@PathVariable("productId") Long productId,
			@RequestBody ProductDTO product) {
		
		productService.removeProduct(companyId, productId);
		
		return new ResponseEntity<Object>(HttpStatus.OK);
 
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "products/{productId}/image" , method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Object> uploadImage(
			@PathVariable("companyId") Long companyId, 
			@PathVariable("productId") Long productId, 
			@RequestParam String imageType , 
			@RequestParam("image") MultipartFile image) throws IOException {
		
		productService.uploadImage(companyId, productId, imageType,image);
		
		return new ResponseEntity<Object>(HttpStatus.OK);
 
	}
	
}
