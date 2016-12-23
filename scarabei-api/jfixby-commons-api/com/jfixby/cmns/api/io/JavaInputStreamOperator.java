
package com.jfixby.cmns.api.io;

import java.io.IOException;

import com.jfixby.cmns.api.java.ByteArray;

public interface JavaInputStreamOperator {

	void closeStream ();

	java.io.InputStream getJavaStream () throws IOException;

	boolean isReadAllSupported ();

	ByteArray readAll () throws IOException;

}
