package com.sstacorp.colectivo.constants;

import java.util.Arrays;
import java.util.List;

import com.sstacorp.colectivo.catalogs.ImageTypes;

public class ProductConstants {

	public static final int MAX_LENGTH_PRODUCT_NAME = 100;
	public static final int MAX_LENGTH_PRODUCT_DESCRIPTION = 250;

	public static final List<ImageTypes> PRODUCT_IMAGE_TYPES = Arrays.asList(
							ImageTypes.PRODUCT_MAIN,
							ImageTypes.PRODUCT_EXTRA);
}
