package com.sstacorp.colectivo.validation;

public interface ComponentValidator<T> {
	void validateParams(T validateDto);
}
