
package com.jfixby.cmns.api.desktop;

import java.io.IOException;

import com.jfixby.cmns.api.image.ColorMap;

public interface GifProducer {

	void append (ColorMap image) throws IOException;

	void close ();

	void open ();

}
