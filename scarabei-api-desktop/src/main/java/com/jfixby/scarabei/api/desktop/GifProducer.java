
package com.jfixby.scarabei.api.desktop;

import java.io.IOException;

import com.jfixby.scarabei.api.image.ColorMap;

public interface GifProducer {

	void append (ColorMap image) throws IOException;

	void close ();

	void open ();

}
