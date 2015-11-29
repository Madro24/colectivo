package com.sstacorp.colectivo.services.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.MemoryCacheImageInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sstacorp.colectivo.catalogs.ImageTypes;
import com.sstacorp.colectivo.catalogs.SupportedImageTypes;
import com.sstacorp.colectivo.constants.ImageConstants;
import com.sstacorp.colectivo.errors.ErrorTypes;
import com.sstacorp.colectivo.exceptions.ErrorMessage;
import com.sstacorp.colectivo.exceptions.ErrorMessageException;
import com.sstacorp.colectivo.jpa.entity.Image;
import com.sstacorp.colectivo.jpa.entity.ImageRelation;
import com.sstacorp.colectivo.jpa.repositories.ImageRelationRepository;
import com.sstacorp.colectivo.jpa.repositories.ImageRepository;
import com.sstacorp.colectivo.services.ImageService;
import com.sstacorp.colectivo.util.ByteArrayStreamingOutput;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
	ImageRelationRepository imageRelationRepository;
	
	@Override
	public void uploadImage(MultipartFile image, String imageType, Long targetId) throws IOException {
		ArrayList<ErrorMessage> errors = new ArrayList<ErrorMessage>();

		if(image == null || image.isEmpty()){
			throw new ErrorMessageException(new ErrorMessage(ErrorTypes.ERROR_IMAGE_MISSING),HttpStatus.BAD_REQUEST);
		}
		if(imageType == null || imageType.isEmpty()){
			throw new ErrorMessageException(new ErrorMessage(ErrorTypes.ERROR_IMAGE_TYPE_MISSING),HttpStatus.BAD_REQUEST);
		}
		if(targetId == null){
			throw new ErrorMessageException(new ErrorMessage(ErrorTypes.ERROR_IMAGE_TARGET_MISSING),HttpStatus.BAD_REQUEST);
		}
		
		ImageTypes imgTypesEnum;
	    try {
	    	imgTypesEnum = ImageTypes.valueOf(imageType);
	     } catch (IllegalArgumentException ex) {  
	    	throw new ErrorMessageException(new ErrorMessage(ErrorTypes.ERROR_IMAGE_TYPE_INVALID),HttpStatus.BAD_REQUEST);
	     }
		
		SupportedImageTypes contentType;
	    try {
	    	contentType = SupportedImageTypes.getSupportedImageByType(image.getContentType());
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
		if(width < ImageConstants.IMAGE_DEFAULT_WIDTH || height < ImageConstants.IMAGE_DEFAULT_HEIGHT){
			errors.add(new ErrorMessage(ErrorTypes.ERROR_IMAGE_TOO_SMALL.addMessageArgs(ImageConstants.IMAGE_DEFAULT_WIDTH,ImageConstants.IMAGE_DEFAULT_HEIGHT)));
		}

		Image imageEntity = new Image();

        // image name validation
		String imageName = image.getOriginalFilename();
		if(imageName == null || imageName.trim().equals("")){
			errors.add(new ErrorMessage(ErrorTypes.ERROR_IMAGE_NAME_MISSING));
		}
		else if(imageName.length() > ImageConstants.MAX_IMAGE_NAME_LENGTH){
			errors.add(new ErrorMessage(ErrorTypes.ERROR_IMAGE_NAME_LENGTH.addMessageArgs(ImageConstants.MAX_IMAGE_NAME_LENGTH)));
		}
		
		if(!errors.isEmpty()){
			throw new ErrorMessageException(errors,HttpStatus.BAD_REQUEST);
		}
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write( bufferedImage, "png", baos );
		baos.flush();
		byte[] imageByteArray = baos.toByteArray();
		baos.close();
		
		imageEntity.setImage(imageByteArray);
		imageEntity.setContentType(contentType.name());
		
		Image savedImage = imageRepository.save(imageEntity);
		
		// Save Image relation entity.
		if(savedImage != null){
			ImageRelation imageRelationEntity = new ImageRelation();
			imageRelationEntity.setImageId(savedImage.getId());
			imageRelationEntity.setImageTypeCode(imgTypesEnum.getCode());
			imageRelationEntity.setTargetId(targetId);
			
			imageRelationRepository.save(imageRelationEntity);
		}
		
		
	}

	@Override
	public ResponseEntity<Object> getImage( Long imageId) throws Exception{

		Image imageEntry = imageRepository.findOne(imageId);
		
        if (imageEntry == null) {
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }
        
        SupportedImageTypes mediaType = SupportedImageTypes.valueOf(imageEntry.getContentType());
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Disposition", "file; filename=\"" + "image" + imageId + "." + mediaType.name().toLowerCase() + "\"");
        headers.setContentType(MediaType.parseMediaType(mediaType.getType()));
        
        return new ResponseEntity<Object>(new ByteArrayStreamingOutput(imageEntry.getImage()), headers, HttpStatus.OK);
	}
}
