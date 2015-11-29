package com.sstacorp.colectivo.exceptions;

import java.util.List;

public class ErrorResource {
    private String code;
    private String message;
    private List<ErrorMessage> fieldErrors;
    
    
	public ErrorResource() {}
	
	public ErrorResource(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<ErrorMessage> getFieldErrors() {
		return fieldErrors;
	}
	public void setFieldErrors(List<ErrorMessage> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}
    
    
}
