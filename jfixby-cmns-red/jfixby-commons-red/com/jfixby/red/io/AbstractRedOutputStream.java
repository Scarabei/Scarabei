
package com.jfixby.red.io;

import java.io.IOException;
import java.io.OutputStream;

import com.jfixby.cmns.api.file.FileOutputStream;
import com.jfixby.cmns.api.io.Data;
import com.jfixby.cmns.api.io.IO;
import com.jfixby.cmns.api.java.ByteArray;
import com.jfixby.cmns.api.util.JUtils;

public class AbstractRedOutputStream implements FileOutputStream {
	private final OutputStream os;

	public AbstractRedOutputStream (final OutputStream os) {
		this.os = os;
	}

	@Override
	public void write (final Data data) throws IOException {
		final RedData di = (RedData)data;
		this.os.write(di.integer);
	}

	@Override
	public void close () {
		IO.forceClose(this.os);
	}

	@Override
	public void flush () throws IOException {
		this.os.flush();
	}

	@Override
	public void write (final ByteArray bytes) throws IOException {
		for (int i = 0; i < bytes.size(); i++) {
			this.os.write(bytes.getByte(i));
		}
		this.os.flush();
		// this.os.flush();
	}

	@Override
	public OutputStream toJavaOutputStream () {
		return this.os;
	}

	@Override
	public void forceClose () {
		IO.forceClose(this.os);
	}

	@Override
	public void write (final byte[] bytes) throws IOException {
		this.write(JUtils.newByteArray(bytes));
	}
}
