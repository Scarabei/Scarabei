
package com.jfixby.cmns.api.io;

import java.io.IOException;

import com.jfixby.cmns.api.java.ByteArray;

public interface OutputStream {

	public STREAM_STATE getState ();

	void write (Data data) throws IOException;

	void close ();

	void flush () throws IOException;

	void write (ByteArray bytes) throws IOException;

	void write (byte[] bytes) throws IOException;

	java.io.OutputStream toJavaOutputStream ();

}
