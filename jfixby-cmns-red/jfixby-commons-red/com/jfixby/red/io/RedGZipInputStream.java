
package com.jfixby.red.io;

import java.io.IOException;

import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.io.GZipInputStream;
import com.jfixby.cmns.api.io.InputStream;
import com.jfixby.cmns.api.io.JavaInputStreamOperator;

public class RedGZipInputStream extends AbstractRedInputStream implements GZipInputStream {

	public RedGZipInputStream (final InputStream input_stream) throws IOException {
		super(is(input_stream));
	}

	private static JavaInputStreamOperator is (final InputStream input_file) throws IOException {
		final java.io.InputStream jis = input_file.toJavaInputStream();
		final java.util.zip.GZIPInputStream zip = new java.util.zip.GZIPInputStream(jis);
// return zip;
		Err.reportError("Not implemented yet!");
		return null;

	}

}
