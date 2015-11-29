package com.sstacorp.colectivo.catalogs;

public enum SupportedImageTypes {
	JPEG("image/jpeg"),
	PNG("image/png");
	
	private String type;
	
	private SupportedImageTypes(String type){
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public static SupportedImageTypes getSupportedImageByType(String type) {
		for (int i = 0; i < SupportedImageTypes.values().length; i++) {
			if (type.equalsIgnoreCase(SupportedImageTypes.values()[i].type))
				return SupportedImageTypes.values()[i];
		}
		return null;
	}
	
	
}
