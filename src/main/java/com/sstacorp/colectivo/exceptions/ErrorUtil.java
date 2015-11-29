package com.sstacorp.colectivo.exceptions;

import java.util.List;

import org.springframework.http.HttpStatus;

public class ErrorUtil {
	/**
	 * This will loop thru all errors, add to ErrorMessageException and throw the exception if any errors are found 
	 * @param errors
	 */	
	public static void handleErrors(List<ErrorMessage> errors,HttpStatus httpStatus) {
		if (errors != null && errors.size() > 0) {
			ErrorMessageException errorMsgException = new ErrorMessageException();
			errorMsgException.setErrors(errors);
			
			if(httpStatus == null) errorMsgException.setStatus(HttpStatus.BAD_REQUEST);
			else errorMsgException.setStatus(httpStatus);
			
			throw errorMsgException;
		}
	}
	
	public static void handleErrors(List<ErrorMessage> errors) {
		if (errors != null && errors.size() > 0) {
			throw new ErrorMessageException(errors,HttpStatus.BAD_REQUEST);
		}
	}
}
