
package com.jfixby.red.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.jfixby.cmns.api.io.IO;
import com.jfixby.cmns.api.io.JavaOutputStreamOperator;

public class JavaFileOutputStreamOperator implements JavaOutputStreamOperator {

	private final File file;
	private FileOutputStream os;

	public JavaFileOutputStreamOperator (final File file) {
		this.file = file;
	}

	@Override
	public void closeStream () {
		IO.forceClose(this.os);
		this.os = null;
	}

	@Override
	public OutputStream getJavaStream () throws IOException {
		if (this.os == null) {
			this.os = new FileOutputStream(this.file);
		}
		return this.os;
	}

}
