
package com.jfixby.scarabei.api.io;

import java.io.IOException;

import com.jfixby.scarabei.api.java.ByteArray;

public interface InputStream extends Stream {

	boolean hasData () throws IOException;

	Data read () throws IOException;

	int available () throws IOException;

	ByteArray readAll () throws IOException;

	String readAllToString () throws IOException;

	String readAllToString (String encoding) throws IOException;

	java.io.InputStream toJavaInputStream () throws IOException;;

}
