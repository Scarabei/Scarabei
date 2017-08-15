
package com.jfixby.scarabei.red.codecs;

import com.jfixby.scarabei.api.codecs.CodecsComponent;
import com.jfixby.scarabei.api.codecs.CrossLanguageToJavaDecoder;
import com.jfixby.scarabei.api.codecs.JavaToCrossLanguageEncoder;
import com.jfixby.scarabei.api.codecs.io.EncodedObject;

public class RedCodecs implements CodecsComponent {
	final Encoders encoders = new Encoders();
	final Decoders decoders = new Decoders();

	public RedCodecs () {

		this.registerDecoder(new CrossLanguageToScarabeiDecoder());
		this.registerEncoder(new ScarabeiToCrossLanguageEncoder());

	}

	@Override
	public void registerEncoder (final JavaToCrossLanguageEncoder encoder) {
		this.encoders.register(encoder);
	}

	@Override
	public void registerDecoder (final CrossLanguageToJavaDecoder decoder) {
		this.decoders.registerDecoder(decoder);
	}

	@Override
	public EncodedObject encode (final Object javaObject) {
		return this.encoders.encode(javaObject);
	}

	@Override
	public <T> T decode (final EncodedObject encodedObject) {
		return this.decoders.decode(encodedObject);
	}

}
