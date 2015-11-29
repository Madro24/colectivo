package com.sstacorp.colectivo.exceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ErrorMessageException extends RuntimeException {
	private static final long serialVersionUID = -4698360549556994847L;
	
	private List<ErrorMessage> errors;
	private HttpStatus status;
	private String code;
	private String message;
	
	public ErrorMessageException() {}
	
	public ErrorMessageException(List<ErrorMessage> errors, HttpStatus status,
			String code, String message) {
		this.errors = errors;
		this.status = status;
		this.code = code;
		this.message = message;
	}
	public ErrorMessageException(ErrorMessage error, HttpStatus status,
			String code, String message) {
		this.errors = Arrays.asList(error);
		this.status = status;
		this.code = code;
		this.message = message;
	}
	public ErrorMessageException(List<ErrorMessage> errors , HttpStatus status){
		this.errors = errors;
		this.status = status;
	}
	
	public ErrorMessageException(ErrorMessage error, HttpStatus status){
		errors = Arrays.asList(error);
		this.status = status;
	}
	
	
	public void addErrorMessage(ErrorMessage error){
		if(errors == null ) errors = new ArrayList<ErrorMessage>();
		errors.add(error);
	}

	public List<ErrorMessage> getErrors() {
		return errors;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public void setErrors(List<ErrorMessage> errors) {
		this.errors = errors;
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
	
	
}
