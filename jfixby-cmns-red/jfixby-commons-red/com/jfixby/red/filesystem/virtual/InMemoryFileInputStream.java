
package com.jfixby.red.filesystem.virtual;

import com.jfixby.cmns.api.file.FileInputStream;
import com.jfixby.red.io.AbstractRedInputStream;

public class InMemoryFileInputStream extends AbstractRedInputStream<RedInMemoryFileInputStreamOperator> implements FileInputStream {

	public InMemoryFileInputStream (final InMemoryFile output_file) {
		super(new RedInMemoryFileInputStreamOperator(output_file));
	}

}
