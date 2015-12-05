package com.sstacorp.colectivo.mapping;

import com.sstacorp.colectivo.catalogs.ImageTypes;
import com.sstacorp.colectivo.dto.ImageDTO;
import com.sstacorp.colectivo.jpa.entity.ImageRelation;

public class ImageUtils {
	
	public static ImageDTO populateDTO(ImageRelation entity){
		if(entity == null) return null;
		
		ImageDTO dto = new ImageDTO();
		dto.setId(entity.getImageId());
		dto.setType(ImageTypes.getImageTypeByCode(entity.getImageTypeCode()).toString());
		
		return dto;
	}
}
