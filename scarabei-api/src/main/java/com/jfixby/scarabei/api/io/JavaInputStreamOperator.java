
package com.jfixby.scarabei.api.io;

import java.io.IOException;

import com.jfixby.scarabei.api.java.ByteArray;

public interface JavaInputStreamOperator {

	void closeStream ();

	java.io.InputStream getJavaStream () throws IOException;

	boolean isReadAllSupported ();

	ByteArray readAll () throws IOException;

}
