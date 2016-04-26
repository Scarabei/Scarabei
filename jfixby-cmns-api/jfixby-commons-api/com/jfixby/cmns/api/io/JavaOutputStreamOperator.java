
package com.jfixby.cmns.api.io;

import java.io.IOException;

public interface JavaOutputStreamOperator {
	void closeStream ();

	java.io.OutputStream getJavaStream () throws IOException;
}
