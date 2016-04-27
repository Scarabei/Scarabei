
package com.jfixby.red.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.jfixby.cmns.api.io.IO;
import com.jfixby.cmns.api.io.JavaInputStreamOperator;

public class JavaFileInputStreamOperator implements JavaInputStreamOperator {

	private final File file;
	private InputStream is;

	public JavaFileInputStreamOperator (final File file) {
		this.file = file;
	}

	@Override
	public void closeStream () {
		IO.forceClose(this.is);
		this.is = null;
	}

	@Override
	public InputStream getJavaStream () throws IOException {
		if (this.is == null) {
			this.is = new FileInputStream(this.file);
			this.is = new BufferedInputStream(this.is);
		}
		return this.is;
	}

}
