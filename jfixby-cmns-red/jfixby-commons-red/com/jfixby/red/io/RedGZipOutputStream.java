package com.jfixby.red.io;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import com.jfixby.cmns.api.io.Data;
import com.jfixby.cmns.api.io.GZipOutputStream;
import com.jfixby.cmns.api.io.IO;

public class RedGZipOutputStream implements GZipOutputStream {

    private GZIPOutputStream zip;
    private OutputStream java_os;

    public RedGZipOutputStream(com.jfixby.cmns.api.io.OutputStream os) throws IOException {
	java_os = os.toJavaOutputStream();
	zip = new GZIPOutputStream(java_os);
    }

    @Override
    public void write(Data data) throws IOException {
	final RedData di = (RedData) data;
	zip.write(di.integer);
    }

    @Override
    public void close() throws IOException {
	zip.flush();
	zip.close();
    }

    @Override
    public void flush() throws IOException {
	zip.flush();
    }

    @Override
    public void write(byte[] bytes) throws IOException {
	for (int i = 0; i < bytes.length; i++) {
	    this.zip.write(bytes[i]);
	}
	this.zip.flush();
	// this.zip.close();
    }

    @Override
    public OutputStream toJavaOutputStream() {
	return zip;
    }

    @Override
    public void forceClose() {
	IO.forceClose(zip);
    }

}
