package com.jfixby.cmns.api.io;

import java.io.IOException;



public interface InputStream {

	boolean hasData() throws IOException;

	Data read() throws IOException;

	int available() throws IOException;

	void close() throws IOException;

	byte[] readAll() throws IOException;

	java.io.InputStream toJavaInputStream();

}
