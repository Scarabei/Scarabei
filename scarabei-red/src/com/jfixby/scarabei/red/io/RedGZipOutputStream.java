
package com.jfixby.scarabei.red.io;

import com.jfixby.scarabei.api.io.GZipOutputStream;
import com.jfixby.scarabei.api.io.OutputStream;

public class RedGZipOutputStream extends AbstractRedOutputStream<RedGZipOutputStreamOperator> implements GZipOutputStream {

	RedGZipOutputStream me = this;

	public RedGZipOutputStream (final OutputStream os) {
		super(new RedGZipOutputStreamOperator(os));
	}

}
