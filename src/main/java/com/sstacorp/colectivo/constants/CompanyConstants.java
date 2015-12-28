package com.sstacorp.colectivo.constants;

import java.util.Arrays;
import java.util.List;

import com.sstacorp.colectivo.catalogs.ImageTypes;

public class CompanyConstants {
	public static final int MAX_COMPANY_NAME = 50;
	public static final int MAX_COMPANY_DISPLAY_NAME = 50;
	public static final String REQUEST_CREATE = "CREATE";
	public static final String REQUEST_UPDATE = "UPDATE";
	
	public static final List<ImageTypes> COMPANY_IMAGE_TYPES = Arrays.asList(ImageTypes.COMPANY_PROFILE);
	
}
