package com.sstacorp.colectivo.rest;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sstacorp.colectivo.services.ImageService;

@Controller
@RequestMapping(value = "/images")
public class ImageRestService {

	@Autowired
	ImageService imageService;
	
	@RequestMapping( method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Object> uploadImage( 
			@RequestParam("image") MultipartFile image,
			@RequestParam("type") String imageType,
			@RequestParam("target") Long targetId) throws IOException {
		
		imageService.uploadImage(image, imageType, targetId);
		
		return new ResponseEntity<Object>(HttpStatus.OK);
 
	}
	
	@RequestMapping(value = "{imageId}", method = RequestMethod.GET, headers = "accept=text/html")
	public @ResponseBody ResponseEntity<Object> getImage(@PathVariable("imageId") Long imageId) throws Exception {
		
		return imageService.getImage(imageId);
 
	}
}
