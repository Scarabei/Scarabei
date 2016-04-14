
package com.jfixby.red.io;

import java.io.IOException;

import com.jfixby.cmns.api.io.GZipInputStream;
import com.jfixby.cmns.api.io.InputStream;

public class RedGZipInputStream extends AbstractRedInputStream implements GZipInputStream {

	public RedGZipInputStream (InputStream input_stream) throws IOException {
		super(is(input_stream));
	}

	private static java.io.InputStream is (InputStream input_file) throws IOException {
		java.io.InputStream jis = input_file.toJavaInputStream();
		java.util.zip.GZIPInputStream zip = new java.util.zip.GZIPInputStream(jis);
		return zip;

	}

}
