package com.sstacorp.colectivo.errors;

public enum ErrorTypes {
	ERROR_PROFILE_USER_EMPTY("profileUser","PROFILE_USER_EMPTY","Profile User property is empty"),
	ERROR_SYSUSER_FIELD_EMPTY("sysUser","SYSUSER_EMPTY","System User's properties must be filled"),
	ERROR_PARTICIPANT_FIELD_EMPTY("participant","PARTICIPANT_EMPTY","Participant's properties must be filled"),
	ERROR_USERNAME_EXIST ("username","INVALID_USERNAME","Username already exists"),
	
	//Product - Image errors
	ERROR_IMAGE_MISSING("upload","IMAGE_MISSING","Must upload an image"),
	ERROR_IMAGE_TYPE_MISSING("imageTypeCode","IMAGE_TYPE_MISSING","Image type code is required."),
	ERROR_IMAGE_TYPE_INVALID("imageTypeCode","IMAGE_TYPE_INVALID","Image type code is not valid."),
	ERROR_IMAGE_TARGET_MISSING("imageTargetId","IMAGE_TARGET_MISSING","Image target is required (company, participant, product, menu, etc)."),
	ERROR_CONTENT_TYPE("upload","CONTENT_TYPE","Unsupported content type. Supported content types are PNG, JPG, and JPEG."),
	ERROR_IMAGE_PROCESS("upload","IMAGE_PROCESS","Error processing image."),
	ERROR_IMAGE_UPLOAD_COUNT("upload","IMAGE_UPLOAD_COUNT","Only one image can be uploaded."),
	ERROR_IMAGE_TOO_SMALL("upload","IMAGE_TOO_SMALL","Minimum ecard size is %d by %d."),
	ERROR_IMAGE_NAME_MISSING("displayName","IMAGE_NAME_MISSING","Display name must be provided."),
	ERROR_IMAGE_NAME_LENGTH("displayName","IMAGE_NAME_LENGTH","Display name is too long.  Max length is %d"),
	ERROR_DUPLICATE_IMAGE_NAME("displayName","DUPLICATE_IMAGE_NAME","Image with specified image name already exists."),
	
	// Company - Errors
	ERROR_COMPANY_MISSING("company","COMPANY_MISSING","Company object is null."),
	ERROR_COMPANY_ID_MISSING("companyId","COMPANY_ID_MISSING","Company id is required to update."),
	ERROR_COMPANY_ID_MISMATCH("companyId","COMPANY_ID_MISMATCH","Company id didn't match with dto object"),
	ERROR_COMPANY_NAME_MISSING("name","COMPANY_NAME_MISSING","Company name is required."),
	ERROR_COMPANY_NAME_TOO_LONG("name","COMPANY_NAME_TOO_LONG","Company name is longer than expected."),
	ERROR_COMPANY_NAME_DUPLICATED("name","COMPANY_NAME_DUPLICATED","Company name cannot be duplicated."),
	ERROR_COMPANY_DISPLAY_NAME_MISSING("displayName","COMPANY_DISPLAY_NAME_MISSING","Company display name is required."),
	ERROR_COMPANY_DISPLAY_NAME_TOO_LONG("displayName","COMPANY_DISPLAY_NAME_TOO_LONG","Company display name is longer than expected."),
	ERROR_COMPANY_NOT_EXISTS("company","COMPANY_NOT_EXISTS","Company id doesn't exist."),
	
	// Menus - Errors
	ERROR_MISSING_MENU_ID("menuId","MISSING_MENU_ID","A menu id is required"),
	ERROR_INVALID_MENU_ID("menuId","INVALID_MENU_ID","Menu id doesn't exist"),
	ERROR_MISSING_MENU_NAME("name","MISSING_MENU_NAME","A menu name is required"),
	ERROR_MENU_NAME_TOO_LONG("name","MENU_NAME_TOO_LONG","Menu name is too long"),
	ERROR_MISSING_MENU_TYPE_CODE("menuTypeCode","MISSING_MENU_TYPE","A menu type is required"),
	ERROR_INVALID_MENU_TYPE_CODE("menuTypeCode","INVALID_MENU_TYPE","A valid menu type is required"),
	ERROR_MISSING_MENU_STATUS_CODE("statusCode","MISSING_MENU_STATUS","A status code is required"),
	ERROR_INVALID_MENU_STATUS_CODE("statusCode","INVALID_MENU_STASUS","A valid status code is required"),
	ERROR_MISMATCH_MENU_ID("menuId","MISMATCH_MENU_ID","Query menu id must be equal to request body menu id "), 
	
	// Product - Errors
	ERROR_MISSING_PRODUCT_NAME("name","MISSING_PRODUCT_NAME","A product name is required"), 
	ERROR_PRODUCT_NAME_TOO_LONG("name","PRODUCT_NAME_TOO_LONG","Product name is too long than expected"), 
	ERROR_MISSING_PRODUCT_DESCRIPTION("description","MISSING_PRODUCT_DESCRIPTION","A product description is required"), 
	ERROR_PRODUCT_DESCRIPTION_TOO_LONG("description","PRODUCT_DESCRIPTION_TOO_LONG","Product description is too long than expected"), 
	ERROR_INVALID_PRODUCT_TYPE_CODE("typeCode","INVALID_PRODUCT_TYPE","A valid product type is required"), 
	ERROR_MISSING_PRODUCT_TYPE_CODE("typeCode","MISSING_PRODUCT_TYPE","A product type is required"), 
	ERROR_INVALID_PRODUCT_STATUS_CODE("statusCode","INVALID_PRODUCT_STATUS","A valid product status is required"), 
	ERROR_MISSING_PRODUCT_STATUS_CODE("statusCode","MISSING_PRODUCT_STATUS","A product status is required"), 
	ERROR_MISSING_PRODUCT_ID("productId","MISSING_PRODUCT_ID","A product Id is required"), 
	ERROR_INVALID_PRODUCT_ID("productId","INVALID_PRODUCT_ID","A valid product id is required"), 
	ERROR_MISMATCH_PRODUCT_ID("productId","MISMATCH_PRODUCT_ID","Query product Id must be equal to request body product Id"),
	ERROR_MISMATCH_COMPANY_ID("companyId","MISMATCH_COMPANY_ID","Query company Id must be equal to request body product - companyId"), 
	ERROR_INVALID_PRODUCT_FOR_COMPANY("productId/companyId","INVALID_PRODUCT_FOR_COMPANY","Product doesn't belong to company");
	
	private String field;
	private String code;
	private String message;
	
	
	private ErrorTypes(String field, String code, String message) {
		this.field = field;
		this.code = code;
		this.message = message;
	}
	
	public ErrorTypes addMessageArgs(Object... messageArgs){
		this.message = String.format(message, messageArgs);
		return this;
	}
	
	public String getField() {
		return field;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
	
}
