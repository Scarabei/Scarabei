
package com.jfixby.red.io;

import java.io.IOException;
import java.io.OutputStream;

import com.jfixby.cmns.api.file.FileOutputStream;
import com.jfixby.cmns.api.io.Data;
import com.jfixby.cmns.api.io.JavaOutputStreamOperator;
import com.jfixby.cmns.api.io.STREAM_STATE;
import com.jfixby.cmns.api.java.ByteArray;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.cmns.api.util.StateSwitcher;

public class AbstractRedOutputStream<T extends JavaOutputStreamOperator> implements FileOutputStream {
// private final OutputStream os;
	private final StateSwitcher<STREAM_STATE> state;
	private final T operator;

	public AbstractRedOutputStream (final T operator) {
		this.operator = operator;
		this.state = JUtils.newStateSwitcher(STREAM_STATE.CLOSED);
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
		this.operator.closeStream();
	}

	@Override
	public void flush () throws IOException {
		this.state.expectState(STREAM_STATE.OPEN);
		this.javaStream().flush();
	}

	@Override
	public void write (final ByteArray bytes) throws IOException {
		this.state.expectState(STREAM_STATE.OPEN);
		for (int i = 0; i < bytes.size(); i++) {
			this.javaStream().write(bytes.getByte(i));
		}
		this.javaStream().flush();
		// this.os.flush();
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
}
