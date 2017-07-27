
package com.jfixby.scarabei.red.desktop.image;

import java.io.IOException;
import java.util.ArrayList;

import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.desktop.GifProducer;
import com.jfixby.scarabei.api.desktop.GifProducerSpecs;
import com.jfixby.scarabei.api.image.ColorMap;
import com.jfixby.scarabei.api.io.OutputStream;

public class RedGifProducer implements GifProducer {

	private AnimatedGifEncoder encoder;
	private java.io.OutputStream java_os;
	private final OutputStream os;
	final ArrayList<ColorMap> buffer = new ArrayList<ColorMap>();
	private int buffer_max_size;

	public RedGifProducer (final GifProducerSpecs producerSpecs) {
		this.os = Debug.checkNull("OutputStream", producerSpecs.getOutputStream());
		this.buffer_max_size = producerSpecs.getFrameBufferSize();
		if (this.buffer_max_size < 0) {
			this.buffer_max_size = 0;
		}
		this.buffer.ensureCapacity(this.buffer_max_size);
	}

	@Override
	public void append (final ColorMap image) throws IOException {
		if (this.java_os == null) {
			this.java_os = this.os.toJavaOutputStream();
			this.encoder.start(this.java_os);
			this.encoder.setRepeat(0);
		}

		this.buffer.add(image);
		if (this.buffer.size() >= this.buffer_max_size) {
			this.flushBuffer();
		}

	}

	private void flushBuffer () {
		for (int i = 0; i < this.buffer.size(); i++) {
			final ColorMap image = this.buffer.get(i);
			this.encoder.addFrame(image);
		}
		this.buffer.clear();
	}

	@Override
	public void close () {
		this.flushBuffer();
		this.encoder.finish();
	}

	@Override
	public void open () {
		this.encoder = new AnimatedGifEncoder();
		this.encoder.setDelay(1000 / 25);
	}

}
