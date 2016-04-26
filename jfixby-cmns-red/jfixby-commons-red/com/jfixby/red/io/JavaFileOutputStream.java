
package com.jfixby.red.io;

import java.io.File;

public class JavaFileOutputStream extends AbstractRedOutputStream<JavaFileOutputStreamOperator> {

	public JavaFileOutputStream (final File file) {
		super(new JavaFileOutputStreamOperator(file));
	}

}
