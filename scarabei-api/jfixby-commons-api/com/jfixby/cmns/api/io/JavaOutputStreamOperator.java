
package com.jfixby.cmns.api.io;

import java.io.IOException;

import com.jfixby.cmns.api.java.ByteArray;

public interface JavaOutputStreamOperator {
	void closeStream ();

	java.io.OutputStream getJavaStream () throws IOException;

	boolean isBulkWriteSupported ();

	void writeAll (ByteArray bytes) throws IOException;

}
