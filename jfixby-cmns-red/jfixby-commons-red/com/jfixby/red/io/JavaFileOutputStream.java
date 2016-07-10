
package com.jfixby.red.io;

import java.io.File;

public class JavaFileOutputStream extends AbstractRedOutputStream<JavaFileOutputStreamOperator> {

	public JavaFileOutputStream (final File file, final boolean append) {
		super(new JavaFileOutputStreamOperator(file, append));
	}

}
