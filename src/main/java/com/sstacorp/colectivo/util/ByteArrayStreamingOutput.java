package com.sstacorp.colectivo.util;

import java.io.IOException;
import java.io.OutputStream;


public class ByteArrayStreamingOutput implements StreamingOutput{

    private byte[] bytes;

    public ByteArrayStreamingOutput(byte[] bytes) {
        this.bytes = bytes;
    }

    public void write(OutputStream os) throws IOException {
        if (bytes != null) {
            os.write(bytes);
        }
    }

}
