
package com.jfixby.red.filesystem.virtual;

import com.jfixby.cmns.api.file.FileOutputStream;
import com.jfixby.red.io.AbstractRedOutputStream;

public class InMemoryFileOutputStream extends AbstractRedOutputStream<RedInMemoryFileOutputStreamOperator>
	implements FileOutputStream {

	public InMemoryFileOutputStream (final InMemoryFile output_file, final boolean append) {
		super(new RedInMemoryFileOutputStreamOperator(output_file, append));
	}

}
