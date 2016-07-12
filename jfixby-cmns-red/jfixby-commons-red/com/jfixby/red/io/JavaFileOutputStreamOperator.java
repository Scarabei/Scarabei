
package com.jfixby.red.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import com.jfixby.cmns.api.io.IO;
import com.jfixby.cmns.api.io.JavaOutputStreamOperator;
import com.jfixby.cmns.api.java.ByteArray;

public class JavaFileOutputStreamOperator implements JavaOutputStreamOperator {

	private final File file;
	private OutputStream os;
	private final boolean append;

	public JavaFileOutputStreamOperator (final File file, final boolean append) {
		this.file = file;
		this.append = append;
	}

	@Override
	public void closeStream () {
		IO.forceClose(this.os);
		this.os = null;
	}

	@Override
	public OutputStream getJavaStream () throws IOException {
		this.file.getParentFile().mkdirs();
		if (this.os == null) {

			this.os = new FileOutputStream(this.file, this.append);
			this.os = new BufferedOutputStream(this.os);
		}
		return this.os;
	}

	@Override
	public boolean isBulkWriteSupported () {
		return true;
	}

	@Override
	public void writeAll (final ByteArray bytes) throws IOException {
		this.file.getParentFile().mkdirs();
		final Path path = this.file.toPath();

		if (this.append & this.file.exists()) {
			Files.write(path, bytes.toArray(), StandardOpenOption.APPEND);
		} else {
			this.file.createNewFile();
			Files.write(path, bytes.toArray(), StandardOpenOption.WRITE);
		}
	}

}
