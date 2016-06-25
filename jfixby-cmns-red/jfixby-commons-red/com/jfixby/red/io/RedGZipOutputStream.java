
package com.jfixby.red.io;

import com.jfixby.cmns.api.io.GZipOutputStream;
import com.jfixby.cmns.api.io.OutputStream;

public class RedGZipOutputStream extends AbstractRedOutputStream<RedGZipOutputStreamOperator> implements GZipOutputStream {

	RedGZipOutputStream me = this;

	public RedGZipOutputStream (final OutputStream os) {
		super(new RedGZipOutputStreamOperator(os));
	}

}
