
package com.jfixby.red.io;

import com.jfixby.cmns.api.file.FileInputStream;
import com.jfixby.cmns.api.io.Buffer;
import com.jfixby.cmns.api.io.BufferInputStream;

public class RedBufferInputStream extends AbstractRedInputStream implements BufferInputStream, FileInputStream {

	private final Buffer buffer;

	public RedBufferInputStream (final Buffer buffer) {
		super(new RedBufferInputStreamOperator(buffer));
		this.buffer = buffer;
	}

}
