
package com.jfixby.red.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.jfixby.cmns.api.file.FileInputStream;
import com.jfixby.cmns.api.io.Buffer;
import com.jfixby.cmns.api.io.BufferInputStream;
import com.jfixby.cmns.api.io.Data;
import com.jfixby.cmns.api.io.IO;
import com.jfixby.cmns.api.io.STREAM_STATE;
import com.jfixby.cmns.api.java.ByteArray;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.cmns.api.util.StateSwitcher;

public class RedBufferInputStream implements BufferInputStream, FileInputStream {

	private final ByteArrayInputStream bis;
	final RedData di = new RedData();
	private final Buffer buffer;
	private final StateSwitcher<STREAM_STATE> state;

	public RedBufferInputStream (final Buffer buffer) {
		this.buffer = buffer;
		this.bis = new ByteArrayInputStream(buffer.getBytes().toArray());
		this.state = JUtils.newStateSwitcher(STREAM_STATE.OPEN);
	}

	@Override
	public boolean hasData () throws IOException {
		return this.bis.available() > 0;
	}

	@Override
	public Data read () throws IOException {
		this.di.integer = this.bis.read();
		return this.di;
	}

	@Override
	public int available () throws IOException {
		return this.bis.available();
	}

	@Override
	public void close () {
		IO.forceClose(this.bis);
		this.state.expectState(STREAM_STATE.OPEN);
		this.state.switchState(STREAM_STATE.CLOSED);
	}

	@Override
	public ByteArray readAll () throws IOException {
		return this.buffer.getBytes();
	}

	@Override
	public InputStream toJavaInputStream () {
		return this.bis;
	}

	@Override
	public STREAM_STATE getState () {
		return this.state.currentState();
	}

}
