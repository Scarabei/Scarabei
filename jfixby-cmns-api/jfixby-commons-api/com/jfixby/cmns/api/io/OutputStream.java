package com.jfixby.cmns.api.io;

import java.io.IOException;

public interface OutputStream {

	void write(Data data) throws IOException;

	void close() throws IOException;

	void flush() throws IOException;

	void write(byte[] bytes) throws IOException;

	java.io.OutputStream toJavaOutputStream();

}
