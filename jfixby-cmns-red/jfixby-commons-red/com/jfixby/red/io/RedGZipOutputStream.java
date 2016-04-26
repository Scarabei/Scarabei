
package com.jfixby.red.io;

import java.io.IOException;
import java.io.OutputStream;

import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.io.GZipOutputStream;
import com.jfixby.cmns.api.io.JavaOutputStreamOperator;

public class RedGZipOutputStream extends AbstractRedOutputStream implements GZipOutputStream {

	public RedGZipOutputStream (final com.jfixby.cmns.api.io.OutputStream os) throws IOException {
		super(newGZIPOutputStream(os.toJavaOutputStream()));
	}

	private static JavaOutputStreamOperator newGZIPOutputStream (final OutputStream javaOutputStream) {
		Err.reportError("Not implemented yet!");
		return null;
	}

}
