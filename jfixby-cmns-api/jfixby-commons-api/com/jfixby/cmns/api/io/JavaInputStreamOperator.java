
package com.jfixby.cmns.api.io;

import java.io.IOException;

public interface JavaInputStreamOperator {

	void closeStream ();

	java.io.InputStream getJavaStream () throws IOException;

}
