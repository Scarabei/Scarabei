
package com.jfixby.cmns.api.io;

import java.io.IOException;

public interface JavaBitInputStream {

	void setFrameSize (int frameSize);

	int read () throws IOException;

}
