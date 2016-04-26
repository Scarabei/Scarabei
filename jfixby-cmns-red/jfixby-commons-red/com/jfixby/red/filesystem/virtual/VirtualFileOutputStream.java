
package com.jfixby.red.filesystem.virtual;

import java.io.IOException;
import java.io.OutputStream;

import com.jfixby.cmns.api.file.FileOutputStream;
import com.jfixby.cmns.api.io.BufferOutputStream;
import com.jfixby.cmns.api.io.Data;
import com.jfixby.cmns.api.io.IO;
import com.jfixby.cmns.api.io.STREAM_STATE;
import com.jfixby.cmns.api.java.ByteArray;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.cmns.api.util.StateSwitcher;

public class VirtualFileOutputStream implements FileOutputStream {
	private final StateSwitcher<STREAM_STATE> state;
	private ContentLeaf leaf;
	private final BufferOutputStream os;

	public VirtualFileOutputStream (final VirtualFile output_file) throws IOException {
		final VirtualFile v_file = output_file;
		this.leaf = v_file.getContent();
		if (this.leaf == null) {
			this.leaf = v_file.createFile();
			if (this.leaf == null) {
				throw new IOException("Unable to write to the file: " + output_file);
			}
		}
		this.os = IO.newBufferOutputStream();
		this.state = JUtils.newStateSwitcher(STREAM_STATE.OPEN);
	}

	@Override
	public void write (final Data data) throws IOException {
		this.os.write(data);
	}

	@Override
	public void write (final byte[] bytes) throws IOException {
		this.write(JUtils.newByteArray(bytes));
	}

	@Override
	public void close () {
		this.os.close();
		final ByteArray data = this.os.getBytes();
		this.leaf.setData(data);
		this.state.expectState(STREAM_STATE.OPEN);
		this.state.switchState(STREAM_STATE.CLOSED);
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
	public STREAM_STATE getState () {
		return this.state.currentState();
	}

}
