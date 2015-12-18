package com.sstacorp.colectivo.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.sstacorp.colectivo.dto.ProductDTO;


public interface ProductService {
	List<ProductDTO> getProductsList(Long companyId);
	List<ProductDTO> getProductsList(Long companyId, Long menuId);
	ProductDTO getProductById(Long companyId, Long productId);
	ProductDTO getProductById(Long productId);
	ProductDTO addProduct(ProductDTO product, Long companyId);
	ProductDTO updateProduct(ProductDTO product, Long companyId, Long productId);
	void removeProduct(Long companyId, Long productId );
	ProductDTO changeImageProduct(Long id,MultipartFile image );
	Long uploadImage(MultipartFile image);
	boolean exists(Long companyId);
	boolean productBelongsToCompany(Long companyId, Long productId);
}
