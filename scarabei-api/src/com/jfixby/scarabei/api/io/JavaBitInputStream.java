
package com.jfixby.scarabei.api.io;

import java.io.IOException;

public interface JavaBitInputStream {

	void setFrameSize (int frameSize);

	int read () throws IOException;

}
