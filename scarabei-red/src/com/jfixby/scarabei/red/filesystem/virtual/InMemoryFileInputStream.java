
package com.jfixby.scarabei.red.filesystem.virtual;

import com.jfixby.scarabei.api.file.FileInputStream;
import com.jfixby.scarabei.red.io.AbstractRedInputStream;

public class InMemoryFileInputStream extends AbstractRedInputStream<RedInMemoryFileInputStreamOperator>
	implements FileInputStream {

	private final InMemoryFile file;

	public InMemoryFileInputStream (final InMemoryFile file) {
		super(new RedInMemoryFileInputStreamOperator(file));
		this.file = file;
	}

	@Override
	public long getFileSize () {
		return this.file.getSize();
	}

}
