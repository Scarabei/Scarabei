
package com.jfixby.red.io;

import java.io.File;

public class JavaFileInputStream extends AbstractRedInputStream<JavaFileInputStreamOperator> {

	public JavaFileInputStream (final File file) {
		super(new JavaFileInputStreamOperator(file));
	}

}
