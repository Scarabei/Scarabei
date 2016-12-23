
package com.jfixby.red.io;

import com.jfixby.cmns.api.io.BufferOutputStream;
import com.jfixby.cmns.api.java.ByteArray;

public class RedBufferOutputStream extends AbstractRedOutputStream<RedBufferOutputStreamOperator> implements BufferOutputStream {

	RedBufferOutputStream me = this;

	public RedBufferOutputStream () {
		super(new RedBufferOutputStreamOperator());
	}

	@Override
	public ByteArray getBytes () {
		return this.getOperator().getBytes();
	}

}
