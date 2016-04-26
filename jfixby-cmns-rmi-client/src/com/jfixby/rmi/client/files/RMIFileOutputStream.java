
package com.jfixby.rmi.client.files;

import java.io.IOException;
import java.io.OutputStream;
import java.rmi.NotBoundException;
import java.util.List;

import com.jfixby.cmns.api.file.FileOutputStream;
import com.jfixby.cmns.api.io.BufferOutputStream;
import com.jfixby.cmns.api.io.Data;
import com.jfixby.cmns.api.io.IO;
import com.jfixby.cmns.api.io.STREAM_STATE;
import com.jfixby.cmns.api.java.ByteArray;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.cmns.api.util.StateSwitcher;
import com.jfixby.cmns.api.util.path.RelativePath;

public class RMIFileOutputStream implements FileOutputStream {

	private final BufferOutputStream os;
	private final RMIDataContainer rmiDataContainer;
	private final List<String> relativePath;
	private final StateSwitcher<STREAM_STATE> state;

	public RMIFileOutputStream (final RMIDataContainer rmiDataContainer, final RelativePath relativePath) throws IOException {
		this.rmiDataContainer = rmiDataContainer;
		this.relativePath = relativePath.steps().toJavaList();
		this.os = IO.newBufferOutputStream();
		this.state = JUtils.newStateSwitcher(STREAM_STATE.OPEN);
		try {
			rmiDataContainer.lookup().ping();
		} catch (final NotBoundException e) {
			throw new IOException(e);
		}
	}

	@Override
	public void write (final Data data) throws IOException {
		this.os.write(data);
	}

	@Override
	public void close () {
		this.os.close();
		final ByteArray data = this.os.getBytes();
		this.state.expectState(STREAM_STATE.OPEN);
		this.state.switchState(STREAM_STATE.CLOSED);
		try {
			this.rmiDataContainer.lookup().writeDataToFile(this.relativePath, data);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void flush () throws IOException {
		this.os.flush();
	}

	@Override
	public void write (final ByteArray bytes) throws IOException {
		this.os.write(bytes);
	}

	@Override
	public OutputStream toJavaOutputStream () {
		return this.os.toJavaOutputStream();
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
