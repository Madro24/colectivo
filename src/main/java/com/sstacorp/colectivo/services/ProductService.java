package com.sstacorp.colectivo.services;

import org.springframework.web.multipart.MultipartFile;

import com.sstacorp.colectivo.dto.ProductDTO;


public interface ProductService {
	ProductDTO addProduct(ProductDTO product);
	ProductDTO updateProduct(Long id, ProductDTO product);
	void removeProduct(Long productId);
	ProductDTO changeImageProduct(Long id,MultipartFile image );
	Long uploadImage(MultipartFile image);
}
