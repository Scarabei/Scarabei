
package com.jfixby.scarabei.api.desktop;

import com.jfixby.scarabei.api.io.OutputStream;

public interface GifProducerSpecs {

	void setOutputStream (OutputStream os);

	OutputStream getOutputStream ();

	int getFrameBufferSize ();

	void setFrameBufferSize (int size);

}
