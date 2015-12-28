package com.sstacorp.colectivo.validator;

public interface ComponentValidator<T> {
	void validateParams(T validateDto);
}
