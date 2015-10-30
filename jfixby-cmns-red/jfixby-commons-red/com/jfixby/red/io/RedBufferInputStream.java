package com.jfixby.red.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.jfixby.cmns.api.filesystem.FileInputStream;
import com.jfixby.cmns.api.io.Buffer;
import com.jfixby.cmns.api.io.BufferInputStream;
import com.jfixby.cmns.api.io.Data;

public class RedBufferInputStream implements BufferInputStream, FileInputStream {

	private ByteArrayInputStream bis;
	final RedData di = new RedData();
	private Buffer buffer;

	public RedBufferInputStream(Buffer buffer) {
		this.buffer = buffer;
		bis = new ByteArrayInputStream(buffer.getBytes());
	}

	@Override
	public boolean hasData() throws IOException {
		return bis.available() > 0;
	}

	@Override
	public Data read() throws IOException {
		di.integer = bis.read();
		return di;
	}

	@Override
	public int available() throws IOException {
		return this.bis.available();
	}

	@Override
	public void close() throws IOException {
		bis.close();
	}

	@Override
	public byte[] readAll() throws IOException {
		return buffer.getBytes();
	}

	@Override
	public InputStream toJavaInputStream() {
		return bis;
	}

}
