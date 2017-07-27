
package com.jfixby.scarabei.red.io;

import java.io.IOException;
import java.io.OutputStream;

import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.file.FileOutputStream;
import com.jfixby.scarabei.api.io.Data;
import com.jfixby.scarabei.api.io.JavaOutputStreamOperator;
import com.jfixby.scarabei.api.io.STREAM_STATE;
import com.jfixby.scarabei.api.java.ByteArray;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.sys.settings.ExecutionMode;
import com.jfixby.scarabei.api.sys.settings.SystemSettings;
import com.jfixby.scarabei.api.util.JUtils;
import com.jfixby.scarabei.api.util.StateSwitcher;

public class AbstractRedOutputStream<T extends JavaOutputStreamOperator> implements FileOutputStream {
// private final OutputStream os;
	private final StateSwitcher<STREAM_STATE> state;
	private final T operator;
	private Exception source;

	public AbstractRedOutputStream (final T operator) {
		this.operator = operator;
		this.state = JUtils.newStateSwitcher(STREAM_STATE.CLOSED);
		if (SystemSettings.executionModeCovers(ExecutionMode.EARLY_DEVELOPMENT)) {
			this.source = new Exception();
		}
	}

	public T getOperator () {
		return this.operator;
	}

	private java.io.OutputStream javaStream () throws IOException {
		return this.operator.getJavaStream();
	}

	@Override
	public void write (final Data data) throws IOException {
		this.state.expectState(STREAM_STATE.OPEN);
		final RedData di = (RedData)data;
		this.javaStream().write(di.integer);
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

	@Override
	public void flush () throws IOException {
		this.state.expectState(STREAM_STATE.OPEN);
		this.javaStream().flush();
	}

	@Override
	public void write (final ByteArray bytes) throws IOException {
		this.state.expectState(STREAM_STATE.OPEN);
		final JavaOutputStreamOperator op = this.operator;
		if (op.isBulkWriteSupported()) {
			op.writeAll(bytes);
			return;
		}
		this.javaStream().write(bytes.toArray());
		this.javaStream().flush();
	}

	@Override
	public OutputStream toJavaOutputStream () throws IOException {
		this.state.expectState(STREAM_STATE.OPEN);
		return this.javaStream();
	}

	@Override
	public void write (final byte[] bytes) throws IOException {
		this.state.expectState(STREAM_STATE.OPEN);
		this.write(JUtils.newByteArray(bytes));
	}

	@Override
	public STREAM_STATE getState () {
		return this.state.currentState();
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

	@Override
	public boolean isOpen () {
		return this.getState() == STREAM_STATE.OPEN;
	}

	@Override
	public boolean isClosed () {
		return this.getState() == STREAM_STATE.CLOSED;
	}

}
