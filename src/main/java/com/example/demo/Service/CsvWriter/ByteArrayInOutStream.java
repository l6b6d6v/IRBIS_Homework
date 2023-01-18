package com.example.demo.Service.CsvWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class ByteArrayInOutStream extends ByteArrayOutputStream {
    public ByteArrayInOutStream() {
        super();
    }

    public ByteArrayInOutStream(int size) {
        super(size);
    }

    public ByteArrayInputStream getInputStream() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(this.buf, 0, this.count);
        this.buf = null;
        return inputStream;
    }
}
