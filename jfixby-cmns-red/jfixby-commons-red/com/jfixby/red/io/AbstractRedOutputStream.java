
package com.jfixby.red.io;

import java.io.IOException;
import java.io.OutputStream;

import com.jfixby.cmns.api.file.FileOutputStream;
import com.jfixby.cmns.api.io.Data;
import com.jfixby.cmns.api.io.IO;
import com.jfixby.cmns.api.io.STREAM_STATE;
import com.jfixby.cmns.api.java.ByteArray;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.cmns.api.util.StateSwitcher;

public class AbstractRedOutputStream implements FileOutputStream {
	private final OutputStream os;
	private final StateSwitcher<STREAM_STATE> state;

	public AbstractRedOutputStream (final OutputStream os) {
		this.os = os;
		this.state = JUtils.newStateSwitcher(STREAM_STATE.OPEN);
	}

	@Override
	public void write (final Data data) throws IOException {
		final RedData di = (RedData)data;
		this.os.write(di.integer);
	}

	@Override
	public void close () {
		IO.forceClose(this.os);
		this.state.expectState(STREAM_STATE.OPEN);
		this.state.switchState(STREAM_STATE.CLOSED);
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

	@Override
	public STREAM_STATE getState () {
		return this.state.currentState();
	}
}
