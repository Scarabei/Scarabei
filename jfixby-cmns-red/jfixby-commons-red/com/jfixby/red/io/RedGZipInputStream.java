
package com.jfixby.red.io;

import java.io.IOException;

import com.jfixby.cmns.api.io.GZipInputStream;
import com.jfixby.cmns.api.io.InputStream;

public class RedGZipInputStream extends AbstractRedInputStream<RedGZipInputStreamOperator> implements GZipInputStream {

	public RedGZipInputStream (final InputStream input_stream) throws IOException {
		super(new RedGZipInputStreamOperator(input_stream));
	}

}
