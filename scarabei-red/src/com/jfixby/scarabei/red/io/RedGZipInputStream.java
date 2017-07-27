
package com.jfixby.scarabei.red.io;

import java.io.IOException;

import com.jfixby.scarabei.api.io.GZipInputStream;
import com.jfixby.scarabei.api.io.InputStream;

public class RedGZipInputStream extends AbstractRedInputStream<RedGZipInputStreamOperator> implements GZipInputStream {

	public RedGZipInputStream (final InputStream input_stream) throws IOException {
		super(new RedGZipInputStreamOperator(input_stream));
	}

}
