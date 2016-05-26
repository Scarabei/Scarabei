
package com.jfixby.red.filesystem.virtual;

import com.jfixby.cmns.api.file.FileOutputStream;
import com.jfixby.red.io.AbstractRedOutputStream;

public class InMemoryFileOutputStream extends AbstractRedOutputStream<RedVirtualFileOutputStreamOperator>
	implements FileOutputStream {

	public InMemoryFileOutputStream (final InMemoryFile output_file) {
		super(new RedVirtualFileOutputStreamOperator(output_file));
	}

}
