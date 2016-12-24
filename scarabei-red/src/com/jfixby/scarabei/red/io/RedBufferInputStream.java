
package com.jfixby.scarabei.red.io;

import com.jfixby.scarabei.api.io.Buffer;
import com.jfixby.scarabei.api.io.BufferInputStream;
import com.jfixby.scarabei.api.io.InputStream;

public class RedBufferInputStream extends AbstractRedInputStream implements BufferInputStream, InputStream {

	private final Buffer buffer;

	@SuppressWarnings("unchecked")
	public RedBufferInputStream (final Buffer buffer) {
		super(new RedBufferInputStreamOperator(buffer));
		this.buffer = buffer;
	}

}
