
package com.jfixby.red.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.jfixby.cmns.api.file.FileInputStream;
import com.jfixby.cmns.api.io.Data;
import com.jfixby.cmns.api.io.IO;
import com.jfixby.cmns.api.io.STREAM_STATE;
import com.jfixby.cmns.api.java.ByteArray;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.cmns.api.util.StateSwitcher;

public class AbstractRedInputStream implements FileInputStream {
	InputStream is;
	private final StateSwitcher<STREAM_STATE> state;

	// private BufferedInputStream bis;

	public AbstractRedInputStream (final InputStream input_stream) throws IOException {
		this.is = input_stream;
		// bis = new BufferedInputStream(is, 1024 * 1024 * 4);
		this.state = JUtils.newStateSwitcher(STREAM_STATE.OPEN);
	}

	@Override
	public boolean hasData () throws IOException {
		if (this.is.available() > 0) {
			return true;
		}
		return false;

	}

	RedData data = new RedData();

	@Override
	public Data read () throws IOException {
		this.data.integer = this.is.read();
		return this.data;
	}

	@Override
	public int available () throws IOException {
		return this.is.available();
	}

	@Override
	public void close () {
		// bis.close();
		IO.forceClose(this.is);
		this.state.expectState(STREAM_STATE.OPEN);
		this.state.switchState(STREAM_STATE.CLOSED);
	}

	@Override
	public ByteArray readAll () throws IOException {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final byte[] buf = new byte[10 * 4096];
		while (true) {
			final int n = this.is.read(buf);
			// L.d("n", n + " : " + (char) n);
			if (n < 0) {
				break;
			}
			baos.write(buf, 0, n);
		}
		// bis.close();
		this.is.close();
		final byte data[] = baos.toByteArray();
		return JUtils.newByteArray(data);
	}

	@Override
	public InputStream toJavaInputStream () {
		return this.is;
	}

	@Override
	public STREAM_STATE getState () {
		return this.state.currentState();
	}
}
