
package com.jfixby.cmns.api.io;

import java.io.IOException;

import com.jfixby.cmns.api.java.ByteArray;

public interface InputStream extends Stream {

	boolean hasData () throws IOException;

	Data read () throws IOException;

	int available () throws IOException;

	public STREAM_STATE getState ();

	ByteArray readAll () throws IOException;

	java.io.InputStream toJavaInputStream ();

}
