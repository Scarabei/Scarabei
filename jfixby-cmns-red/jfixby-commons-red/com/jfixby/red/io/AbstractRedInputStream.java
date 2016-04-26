
package com.jfixby.red.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.file.FileInputStream;
import com.jfixby.cmns.api.io.Data;
import com.jfixby.cmns.api.io.JavaInputStreamOperator;
import com.jfixby.cmns.api.io.STREAM_STATE;
import com.jfixby.cmns.api.java.ByteArray;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.sys.settings.ExecutionMode;
import com.jfixby.cmns.api.sys.settings.SystemSettings;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.cmns.api.util.StateSwitcher;

public class AbstractRedInputStream<T extends JavaInputStreamOperator> implements FileInputStream {
	private final StateSwitcher<STREAM_STATE> state;
	private final T operator;
	private Exception source;

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
		try {
			this.operator.closeStream();
		} catch (final Throwable e) {
		}
	}

	public AbstractRedInputStream (final T operator) {
		this.operator = operator;
		// bis = new BufferedInputStream(is, 1024 * 1024 * 4);
		this.state = JUtils.newStateSwitcher(STREAM_STATE.CLOSED);
		if (SystemSettings.executionModeCovers(ExecutionMode.EARLY_DEVELOPMENT)) {
			this.source = new Exception();
		}
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

	@Override
	protected void finalize () throws Throwable {
		super.finalize();
		if (this.state.currentState() != STREAM_STATE.CLOSED) {
			final String msg = "Stream leak detected: " + this + " state=" + this.state;
			L.e(msg);
			if (SystemSettings.executionModeCovers(ExecutionMode.EARLY_DEVELOPMENT)) {
// this.source.printStackTrace(System.out);
				Err.reportError(new Error(msg, this.source));
			}
			this.close();
		}
	}

}
