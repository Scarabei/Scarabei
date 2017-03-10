
package com.jfixby.scarabei.api.io;

import java.io.IOException;

import com.jfixby.scarabei.api.java.ByteArray;

public interface OutputStream extends Stream {

	void write (Data data) throws IOException;

	void flush () throws IOException;

	void write (ByteArray bytes) throws IOException;

	void write (byte[] bytes) throws IOException;

	java.io.OutputStream toJavaOutputStream () throws IOException;

}
