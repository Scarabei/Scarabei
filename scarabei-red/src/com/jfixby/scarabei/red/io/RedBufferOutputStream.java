
package com.jfixby.scarabei.red.io;

import com.jfixby.scarabei.api.io.BufferOutputStream;
import com.jfixby.scarabei.api.java.ByteArray;

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
