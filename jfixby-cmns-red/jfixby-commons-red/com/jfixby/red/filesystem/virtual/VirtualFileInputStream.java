
package com.jfixby.red.filesystem.virtual;

import com.jfixby.cmns.api.file.FileInputStream;
import com.jfixby.red.io.AbstractRedInputStream;

public class VirtualFileInputStream extends AbstractRedInputStream<RedVirtualFileInputStreamOperator> implements FileInputStream {

	public VirtualFileInputStream (final InMemoryFile output_file) {
		super(new RedVirtualFileInputStreamOperator(output_file));
	}

}
