
package com.jfixby.red.io;

import com.jfixby.cmns.api.io.Buffer;
import com.jfixby.cmns.api.io.BufferInputStream;
import com.jfixby.cmns.api.io.InputStream;

public class RedBufferInputStream extends AbstractRedInputStream implements BufferInputStream, InputStream {

	private final Buffer buffer;

	@SuppressWarnings("unchecked")
	public RedBufferInputStream (final Buffer buffer) {
		super(new RedBufferInputStreamOperator(buffer));
		this.buffer = buffer;
	}

}
