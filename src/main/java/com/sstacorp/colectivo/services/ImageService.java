package com.sstacorp.colectivo.services;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;


public interface ImageService{
	public void uploadImage(MultipartFile image, String imageType, Long targetId) throws IOException;
	public ResponseEntity<Object> getImage(Long imageId) throws Exception;
}
