
package com.jfixby.red.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.jfixby.cmns.api.io.IO;
import com.jfixby.cmns.api.io.JavaOutputStreamOperator;

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
		if (this.os == null) {
			this.file.getParentFile().mkdirs();
			this.os = new FileOutputStream(this.file, this.append);
			this.os = new BufferedOutputStream(this.os);
		}
		return this.os;
	}

}
