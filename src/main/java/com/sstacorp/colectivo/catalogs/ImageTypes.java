package com.sstacorp.colectivo.catalogs;

public enum ImageTypes {
	PRODUCT_MAIN("PRD_MAIN"),
	PRODUCT_EXTRA("PRD_XTRA"),
	COMPANY_PROFILE("CIA_PRFL"),
	PARTICIPANT_PROFILE("PAX_PRFL"),
	BACKGROUND("BACKGROUND"),
	;
	
	private String code;
	
	private ImageTypes(String code){
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public static ImageTypes getImageTypeByCode(String code) {
		for (int i = 0; i < ImageTypes.values().length; i++) {
			if (code.equalsIgnoreCase(ImageTypes.values()[i].code))
				return ImageTypes.values()[i];
		}
		return null;
	}
	
	
}
