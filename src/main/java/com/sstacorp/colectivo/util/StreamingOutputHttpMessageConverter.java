package com.sstacorp.colectivo.util;

import java.io.IOException;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;



public class StreamingOutputHttpMessageConverter extends AbstractHttpMessageConverter<StreamingOutput> {

    public StreamingOutputHttpMessageConverter() {
    	super(MediaType.ALL);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return StreamingOutput.class.isAssignableFrom(clazz);
    }

    @Override
    protected void writeInternal(StreamingOutput streamingOutput, HttpOutputMessage output) throws IOException, HttpMessageNotWritableException {
        streamingOutput.write(output.getBody());
    }

	@Override
	protected StreamingOutput readInternal(Class<? extends StreamingOutput> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
		return null;
	}
}