package com.sstacorp.colectivo.exceptions;

import com.sstacorp.colectivo.errors.ErrorTypes;


public class ErrorMessage {
	private String code;
	private String field;
	private String message;
	
	public ErrorMessage() {}
	
	public ErrorMessage(String code, String field, String message) {
		super();
		this.code = code;
		this.field = field;
		this.message = message;
	}
	
	public ErrorMessage(ErrorTypes errorType) {
		super();
		this.code = errorType.getCode();
		this.field = errorType.getField();
		this.message = errorType.getMessage();
	}
	public String getCode() {
		return code;
	}
	public ErrorMessage setCode(String code) {
		this.code = code;
		return this;
	}
	public String getField() {
		return field;
	}
	public ErrorMessage setField(String field) {
		this.field = field;
		return this;
	}
	public String getMessage() {
		return message;
	}
	public ErrorMessage setMessage(String message) {
		this.message = message;
		return this;
	}
	
	
}
