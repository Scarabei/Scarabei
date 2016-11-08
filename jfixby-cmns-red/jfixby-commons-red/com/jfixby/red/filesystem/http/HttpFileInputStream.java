
package com.jfixby.red.filesystem.http;

import java.io.IOException;

import com.jfixby.cmns.api.file.FileInputStream;
import com.jfixby.red.io.AbstractRedInputStream;

public class HttpFileInputStream extends AbstractRedInputStream<HttpFileInputStreamOperator> implements FileInputStream {

	private final HttpFile file;

	public HttpFileInputStream (final HttpFile file) {
		super(new HttpFileInputStreamOperator(file));
		this.file = file;
	}

	@Override
	public long getFileSize () throws IOException {
		return this.file.getSize();
	}

}
