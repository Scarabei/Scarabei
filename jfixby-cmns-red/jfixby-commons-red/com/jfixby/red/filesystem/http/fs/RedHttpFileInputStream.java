
package com.jfixby.red.filesystem.http.fs;

import java.io.IOException;

import com.jfixby.cmns.api.file.FileInputStream;
import com.jfixby.red.io.AbstractRedInputStream;

public class RedHttpFileInputStream extends AbstractRedInputStream<RedHttpFileInputStreamOperator> implements FileInputStream {

	private final RedHttpFile file;

	public RedHttpFileInputStream (final RedHttpFile file) {
		super(new RedHttpFileInputStreamOperator(file));
		this.file = file;
	}

	@Override
	public long getFileSize () throws IOException {
		return this.file.getSize();
	}

}
