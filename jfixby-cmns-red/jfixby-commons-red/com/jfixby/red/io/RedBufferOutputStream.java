package com.jfixby.red.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.jfixby.cmns.api.io.BufferOutputStream;
import com.jfixby.cmns.api.io.Data;

public class RedBufferOutputStream implements BufferOutputStream {

	private ByteArrayOutputStream os;

	public RedBufferOutputStream() {
		os = new ByteArrayOutputStream();
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
		this.os.close();
	}

	@Override
	public byte[] getBytes() {
		return this.os.toByteArray();
	}

	@Override
	public OutputStream toJavaOutputStream() {
		return os;
	}

}
