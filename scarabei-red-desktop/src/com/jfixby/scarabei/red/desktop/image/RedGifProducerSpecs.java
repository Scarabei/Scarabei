
package com.jfixby.scarabei.red.desktop.image;

import com.jfixby.scarabei.api.desktop.GifProducerSpecs;
import com.jfixby.scarabei.api.io.OutputStream;

public class RedGifProducerSpecs implements GifProducerSpecs {

	private OutputStream os;
	private int bufferSize = 10;

	@Override
	public void setOutputStream (final OutputStream os) {
		this.os = os;
	}

	@Override
	public OutputStream getOutputStream () {
		return this.os;
	}

	@Override
	public int getFrameBufferSize () {
		return this.bufferSize;
	}

	@Override
	public void setFrameBufferSize (final int size) {
		this.bufferSize = size;
	}

}
