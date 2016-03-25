package com.jfixby.red.io;

import java.io.IOException;
import java.io.OutputStream;

import com.jfixby.cmns.api.file.FileOutputStream;
import com.jfixby.cmns.api.io.Data;
import com.jfixby.cmns.api.io.IO;

public class AbstractRedOutputStream implements FileOutputStream {
    private OutputStream os;

    public AbstractRedOutputStream(OutputStream os) {
	this.os = os;
    }

    @Override
    public void write(Data data) throws IOException {
	final RedData di = (RedData) data;
	os.write(di.integer);
    }

    @Override
    public void close() throws IOException {
	os.flush();
	os.close();
    }

    @Override
    public void flush() throws IOException {
	os.flush();
    }

    @Override
    public void write(byte[] bytes) throws IOException {
	for (int i = 0; i < bytes.length; i++) {
	    this.os.write(bytes[i]);
	}
	this.os.flush();
	// this.os.flush();
    }

    @Override
    public OutputStream toJavaOutputStream() {
	return os;
    }

    @Override
    public void forceClose() {
	IO.forceClose(os);
    }
}
