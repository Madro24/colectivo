package com.sstacorp.colectivo.services.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.MemoryCacheImageInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sstacorp.colectivo.catalogs.ImageTypes;
import com.sstacorp.colectivo.catalogs.SupportedImageTypes;
import com.sstacorp.colectivo.dto.ProductDTO;
import com.sstacorp.colectivo.errors.ErrorTypes;
import com.sstacorp.colectivo.exceptions.ErrorMessage;
import com.sstacorp.colectivo.exceptions.ErrorMessageException;
import com.sstacorp.colectivo.jpa.entity.Image;
import com.sstacorp.colectivo.jpa.entity.Product;
import com.sstacorp.colectivo.jpa.repositories.ImageRepository;
import com.sstacorp.colectivo.jpa.repositories.ProductRepository;
import com.sstacorp.colectivo.services.ProductService;
import com.sstacorp.colectivo.util.ImageManipulationUtil;

@Service
public class ProductServiceImpl implements ProductService {
	private static final int MAX_IMAGE_NAME_LENGTH = 50;
	private static final int MAX_IMAGE_DESCRIPTION_LENGTH = 100;
	
	private static final int IMAGE_DEFAULT_WIDTH = 1000;
	private static final int IMAGE_DEFAULT_HEIGHT = 667;
	
	private static final int IMAGE_THUMBNAIL_WIDTH = 480;
	private static final int IMAGE_THUMBNAIL_HEIGHT = 320;

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ImageRepository imageRepository;
	
	@Override
	public ProductDTO addProduct(ProductDTO productDto) {

		if(productDto != null){
			Product product = productDto.getProductEntity();

			product = productRepository.save(product);
			
			if(product != null) return new ProductDTO(product);
		}
		return null;
	}

	@Override
	public ProductDTO updateProduct(Long id, ProductDTO product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeProduct(Long productId) {
		// TODO Auto-generated method stub

	}

	@Override
	public ProductDTO changeImageProduct(Long Id, MultipartFile image) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long uploadImage(MultipartFile image) {
		ArrayList<ErrorMessage> errors = new ArrayList<ErrorMessage>();

		if(image == null || image.isEmpty()){
			throw new ErrorMessageException(new ErrorMessage(ErrorTypes.ERROR_IMAGE_MISSING),HttpStatus.BAD_REQUEST);
		}
		
		SupportedImageTypes contentType;
	    try {
	    	contentType = SupportedImageTypes.valueOf(image.getContentType().toLowerCase());
	     } catch (IllegalArgumentException ex) {  
	    	throw new ErrorMessageException(new ErrorMessage(ErrorTypes.ERROR_CONTENT_TYPE),HttpStatus.BAD_REQUEST);
	     }
	    BufferedImage bufferedImage = null;
	    
		ImageReader imageReader = null;
		MemoryCacheImageInputStream mciis = null;
		try{
			Iterator<ImageReader> imageReaders = ImageIO.getImageReadersByMIMEType(contentType.getType());
			if(imageReaders != null && imageReaders.hasNext()){
				imageReader = imageReaders.next();
			}
			else{
				throw new ErrorMessageException(new ErrorMessage(ErrorTypes.ERROR_IMAGE_PROCESS),HttpStatus.BAD_REQUEST);
			}
			
			if(image.getBytes() != null){
				mciis = new MemoryCacheImageInputStream(new ByteArrayInputStream(image.getBytes()));
			}
			else{
				throw new ErrorMessageException(new ErrorMessage(ErrorTypes.ERROR_IMAGE_PROCESS),HttpStatus.BAD_REQUEST);
			}
			
			if(imageReader != null && mciis != null){
				imageReader.setInput(mciis);
				if(imageReader.getNumImages(true) > 1){
					throw new ErrorMessageException(new ErrorMessage(ErrorTypes.ERROR_IMAGE_UPLOAD_COUNT),HttpStatus.BAD_REQUEST);
				}
				bufferedImage = imageReader.read(0);
			}
			
			
		}
		catch(Exception e){
			throw new ErrorMessageException(new ErrorMessage(ErrorTypes.ERROR_IMAGE_PROCESS),HttpStatus.BAD_REQUEST);
		}
		finally{
			if(mciis != null){
				try {
					mciis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	
		
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		if(width < IMAGE_DEFAULT_WIDTH || height < IMAGE_DEFAULT_HEIGHT){
			errors.add(new ErrorMessage(ErrorTypes.ERROR_IMAGE_TOO_SMALL.addMessageArgs(IMAGE_DEFAULT_WIDTH,IMAGE_DEFAULT_HEIGHT)));
		}

		Image imageEntity = new Image();

        // image name validation
		String imageName = image.getOriginalFilename();
		if(imageName == null || imageName.trim().equals("")){
			errors.add(new ErrorMessage(ErrorTypes.ERROR_IMAGE_NAME_MISSING));
		}
		else if(imageName.length() > MAX_IMAGE_NAME_LENGTH){
			errors.add(new ErrorMessage(ErrorTypes.ERROR_IMAGE_NAME_LENGTH.addMessageArgs(MAX_IMAGE_NAME_LENGTH)));
		}
		
		if(!errors.isEmpty()){
			throw new ErrorMessageException(errors,HttpStatus.BAD_REQUEST);
		}
		imageEntity.setContentType("JPEG");
		
		imageRepository.save(imageEntity);
		
		Long imageId = imageEntity.getId();
		bufferedImage = ImageManipulationUtil.resize(bufferedImage, IMAGE_DEFAULT_WIDTH, IMAGE_DEFAULT_HEIGHT);
		
	//	saveImage(bufferedImage, "Default", imageId);
		
		bufferedImage = ImageManipulationUtil.resize(bufferedImage, IMAGE_THUMBNAIL_WIDTH, IMAGE_THUMBNAIL_HEIGHT);
		
	//	saveImage(bufferedImage, "Thumbnail", ecardId);
		
		//return ecardDao.getInfoForEcardId(ecardId);
		return null;
	}

	private void saveImage(BufferedImage bufferedImage, String type, Long imageId) throws IOException{
		
	/*	Images imageEntry = ecardDao.getEcard(ecardId, type);
		
		
		if(imageEntry == null){
			imageEntry = new Images();
			imageEntry.setImageTypeCode(ecardDao.getImageTypeCodeByType(type));
		}
		
		imageEntry.setMediaType("image/png");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write( bufferedImage, "png", baos );
		baos.flush();
		byte[] imageByteArray = baos.toByteArray();
		baos.close();
		
		imageEntry.setPicture(imageByteArray);
		imagesDao.save(imageEntry);
		
		EcardImages ecardImage = new EcardImages();
		ecardImage.setEcardId(ecardId);
		ecardImage.setImagesId(imageEntry.getId());
		
		ecardImagesDao.save(ecardImage);
	*/	
	}
}
