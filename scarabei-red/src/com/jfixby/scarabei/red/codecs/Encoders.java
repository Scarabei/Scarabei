
package com.jfixby.scarabei.red.codecs;

import com.jfixby.scarabei.api.codecs.JavaToCrossLanguageEncoder;
import com.jfixby.scarabei.api.codecs.io.EncodedObject;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.err.Err;

public class Encoders {

	final List<JavaToCrossLanguageEncoder> encoders = Collections.newList();

	public EncodedObject encode (final Object javaObject) {
		for (final JavaToCrossLanguageEncoder e : this.encoders) {
			if (e.canEncode(javaObject)) {
				return e.encode(javaObject);
			}
		}
		Err.reportError("Encoder not found <" + javaObject.getClass() + "> for " + javaObject);
		return null;
	}

	public void register (final JavaToCrossLanguageEncoder encoder) {
		this.encoders.add(encoder);
	}

}
