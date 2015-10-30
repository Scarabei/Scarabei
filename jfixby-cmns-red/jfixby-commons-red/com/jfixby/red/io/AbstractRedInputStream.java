package com.jfixby.red.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.jfixby.cmns.api.filesystem.FileInputStream;
import com.jfixby.cmns.api.io.Data;

public class AbstractRedInputStream implements FileInputStream {
	InputStream is;

	// private BufferedInputStream bis;

	public AbstractRedInputStream(InputStream input_stream) throws IOException {
		is = input_stream;
		// bis = new BufferedInputStream(is, 1024 * 1024 * 4);
	}

	@Override
	public boolean hasData() throws IOException {
		if (is.available() > 0) {
			return true;
		}
		return false;

	}

	RedData data = new RedData();

	@Override
	public Data read() throws IOException {
		data.integer = this.is.read();
		return data;
	}

	@Override
	public int available() throws IOException {
		return is.available();
	}

	@Override
	public void close() throws IOException {
		// bis.close();
		is.close();
	}

	@Override
	public byte[] readAll() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buf = new byte[10 * 4096];
		while (true) {
			int n = is.read(buf);
			// L.d("n", n + " : " + (char) n);
			if (n < 0)
				break;
			baos.write(buf, 0, n);
		}
		// bis.close();
		is.close();
		byte data[] = baos.toByteArray();
		return data;
	}

	@Override
	public InputStream toJavaInputStream() {
		return is;
	}
}
