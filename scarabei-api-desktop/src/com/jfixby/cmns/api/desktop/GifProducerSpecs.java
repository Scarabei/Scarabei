
package com.jfixby.cmns.api.desktop;

import com.jfixby.cmns.api.io.OutputStream;

public interface GifProducerSpecs {

	void setOutputStream (OutputStream os);

	OutputStream getOutputStream ();

	int getFrameBufferSize ();

	void setFrameBufferSize (int size);

}
