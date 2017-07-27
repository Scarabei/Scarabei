
package com.jfixby.scarabei.red.filesystem.virtual;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.jfixby.scarabei.api.io.IO;
import com.jfixby.scarabei.api.io.JavaInputStreamOperator;
import com.jfixby.scarabei.api.java.ByteArray;

public class RedInMemoryFileInputStreamOperator implements JavaInputStreamOperator {

	private final InMemoryFile v_file;
	private InputStream is;

	public RedInMemoryFileInputStreamOperator (final InMemoryFile input_file) {
		this.v_file = input_file;
	}

	@Override
	public void closeStream () {
		IO.forceClose(this.is);
		this.is = null;
	}

	@Override
	public InputStream getJavaStream () throws IOException {
		if (this.is == null) {
			final ContentLeaf leaf = this.v_file.getContent();
			if (leaf == null) {
				throw new IOException("File not found: " + this.v_file);
			}
			final ByteArray data = leaf.getData();
			this.is = new ByteArrayInputStream(data.toArray());
		}
		return this.is;
	}

	@Override
	public boolean isReadAllSupported () {
		return true;
	}

	@Override
	public ByteArray readAll () throws IOException {
		final ContentLeaf leaf = this.v_file.getContent();
		if (leaf == null) {
			throw new IOException("File not found: " + this.v_file);
		}
		final ByteArray data = leaf.getData();
		return data;
	}

}
