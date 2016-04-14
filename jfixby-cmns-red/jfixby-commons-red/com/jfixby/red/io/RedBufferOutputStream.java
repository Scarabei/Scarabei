
package com.jfixby.red.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.jfixby.cmns.api.io.BufferOutputStream;
import com.jfixby.cmns.api.io.Data;
import com.jfixby.cmns.api.io.IO;
import com.jfixby.cmns.api.java.ByteArray;
import com.jfixby.cmns.api.util.JUtils;

public class RedBufferOutputStream implements BufferOutputStream {

	private ByteArrayOutputStream os;

	public RedBufferOutputStream () {
		os = new ByteArrayOutputStream();
	}

	@Override
	public void write (Data data) throws IOException {
		final RedData di = (RedData)data;
		os.write(di.integer);
	}

	@Override
	public void close () throws IOException {
		os.flush();
		os.close();
	}

	@Override
	public void flush () throws IOException {
		os.flush();
	}

	@Override
	public void write (ByteArray bytes) throws IOException {
		for (int i = 0; i < bytes.size(); i++) {
			this.os.write(bytes.getByte(i));
		}
		this.os.flush();
		// this.os.close();
	}

	@Override
	public ByteArray getBytes () {
		return JUtils.newByteArray(this.os.toByteArray());
	}

	@Override
	public OutputStream toJavaOutputStream () {
		return os;
	}

	@Override
	public void forceClose () {
		IO.forceClose(os);
	}

	@Override
	public void write (byte[] bytes) throws IOException {
		this.write(JUtils.newByteArray(bytes));
	}

}
