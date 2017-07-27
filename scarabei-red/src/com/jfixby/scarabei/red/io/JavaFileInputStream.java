
package com.jfixby.scarabei.red.io;

import java.io.File;

import com.jfixby.scarabei.api.file.FileInputStream;

public class JavaFileInputStream extends AbstractRedInputStream<JavaFileInputStreamOperator> implements FileInputStream {

	private final File file;

	public JavaFileInputStream (final File file) {
		super(new JavaFileInputStreamOperator(file));
		this.file = file;
// if (this.toString().contains("GenericFont.otf")) {
// Err.reportError();
// }
	}

	@Override
	public String toString () {
		return "JavaFileInputStream=" + this.file + "";
	}

	@Override
	public long getFileSize () {
		return this.file.length();
	}

}
