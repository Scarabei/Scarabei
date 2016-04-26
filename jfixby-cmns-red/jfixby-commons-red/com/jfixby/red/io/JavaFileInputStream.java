
package com.jfixby.red.io;

import java.io.File;

public class JavaFileInputStream extends AbstractRedInputStream<JavaFileInputStreamOperator> {

	private final File file;

	public JavaFileInputStream (final File file) {
		super(new JavaFileInputStreamOperator(file));
		this.file = file;
// if (this.toString().contains("GenericFont.otf")) {
// throw new Error();
// }
	}

	@Override
	public String toString () {
		return "JavaFileInputStream=" + this.file + "";
	}

}
