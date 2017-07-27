
package com.jfixby.scarabei.red.filesystem.virtual;

import java.io.IOException;
import java.io.OutputStream;

import com.jfixby.scarabei.api.io.BufferOutputStream;
import com.jfixby.scarabei.api.io.IO;
import com.jfixby.scarabei.api.io.JavaOutputStreamOperator;
import com.jfixby.scarabei.api.java.ByteArray;

public class RedInMemoryFileOutputStreamOperator implements JavaOutputStreamOperator {
	private ContentLeaf leaf;
	private BufferOutputStream os;
	private final InMemoryFile v_file;
	private final boolean append;

	public RedInMemoryFileOutputStreamOperator (final InMemoryFile output_file, final boolean append) {
		this.v_file = output_file;
		this.append = append;
	}

	@Override
	public void closeStream () {
		if (this.os == null) {
			return;
		}
		this.os.close();
		final ByteArray data = this.os.getBytes();
		this.leaf.setData(data);
		this.os = null;
	}

	@Override
	public OutputStream getJavaStream () throws IOException {
		if (this.os == null) {
			this.leaf = this.v_file.getContent();
			if (this.leaf == null) {
				this.leaf = this.v_file.createFile();
				if (this.leaf == null) {
					throw new IOException("Unable to write to the file: " + this.v_file);
				}
			}
			this.os = IO.newBufferOutputStream();
			this.os.open();
			if (this.append) {
				this.os.write(this.leaf.getData());
// this.leaf.setData(null);
			}
		}
		return this.os.toJavaOutputStream();
	}

	@Override
	public boolean isBulkWriteSupported () {
		return true;
	}

	@Override
	public void writeAll (final ByteArray bytes) throws IOException {
		this.leaf = this.v_file.getContent();
		if (this.leaf == null) {
			this.leaf = this.v_file.createFile();
			if (this.leaf == null) {
				throw new IOException("Unable to write to the file: " + this.v_file);
			}
		}
		this.leaf.setData(bytes);
	}

}
