package com.sstacorp.colectivo.services;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.sstacorp.colectivo.catalogs.ImageTypes;
import com.sstacorp.colectivo.dto.ImageDTO;


public interface ImageService{
	
	public void uploadImage(MultipartFile image, String imageType, Long targetId) throws IOException;
	public ResponseEntity<Object> getImage(Long imageId) throws Exception;
	public List<ImageDTO> getImageIdReferences(Long targetId, List<ImageTypes> types);
}
