package com.sstacorp.colectivo.exceptions;


import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ ErrorMessageException.class })
    protected ResponseEntity<Object> handleGeneralExceptions(RuntimeException e, WebRequest request) {
    	ErrorMessageException exception = (ErrorMessageException) e;
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return handleExceptionInternal(e, exception.getErrors(), headers, exception.getStatus(), request);
    }

}
