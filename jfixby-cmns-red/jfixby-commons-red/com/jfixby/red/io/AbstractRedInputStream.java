
package com.jfixby.red.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.jfixby.cmns.api.file.FileInputStream;
import com.jfixby.cmns.api.io.Data;
import com.jfixby.cmns.api.io.JavaInputStreamOperator;
import com.jfixby.cmns.api.io.STREAM_STATE;
import com.jfixby.cmns.api.java.ByteArray;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.cmns.api.util.StateSwitcher;

public class AbstractRedInputStream<T extends JavaInputStreamOperator> implements FileInputStream {
	private final StateSwitcher<STREAM_STATE> state;
	private final T operator;

	public T getOperator () {
		return this.operator;
	}

	// private BufferedInputStream bis;

	@Override
	public STREAM_STATE getState () {
		return this.state.currentState();
	}

	@Override
	public void open () {
		this.state.expectState(STREAM_STATE.CLOSED);
		this.state.switchState(STREAM_STATE.OPEN);
	}

	@Override
	public void close () {
		this.state.expectState(STREAM_STATE.OPEN);
		this.state.switchState(STREAM_STATE.CLOSED);
		this.operator.closeStream();
	}

	public AbstractRedInputStream (final T operator) {
		this.operator = operator;
		// bis = new BufferedInputStream(is, 1024 * 1024 * 4);
		this.state = JUtils.newStateSwitcher(STREAM_STATE.CLOSED);
	}

	@Override
	public boolean hasData () throws IOException {
		this.state.expectState(STREAM_STATE.OPEN);
		if (this.javaStream().available() > 0) {
			return true;
		}
		return false;

	}

	RedData data = new RedData();

	@Override
	public Data read () throws IOException {
		this.state.expectState(STREAM_STATE.OPEN);
		this.data.integer = this.javaStream().read();
		return this.data;
	}

	private InputStream javaStream () throws IOException {
		this.state.expectState(STREAM_STATE.OPEN);
		return this.operator.getJavaStream();
	}

	@Override
	public int available () throws IOException {
		this.state.expectState(STREAM_STATE.OPEN);
		return this.javaStream().available();
	}

	@Override
	public ByteArray readAll () throws IOException {
		this.state.expectState(STREAM_STATE.OPEN);
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final byte[] buf = new byte[10 * 4096];
		while (true) {
			final int n = this.javaStream().read(buf);
			// L.d("n", n + " : " + (char) n);
			if (n < 0) {
				break;
			}
			baos.write(buf, 0, n);
		}
		// bis.close();
// this.javaStream().close();
		final byte data[] = baos.toByteArray();
		return JUtils.newByteArray(data);
	}

	@Override
	public InputStream toJavaInputStream () throws IOException {
		this.state.expectState(STREAM_STATE.OPEN);
		return this.javaStream();
	}

}
