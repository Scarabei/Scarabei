
package com.jfixby.scarabei.red.flutter.calls;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.flutter.call.JavaToCrossLanguageEncoder;

public class Encoders {

	final List<JavaToCrossLanguageEncoder> encoders = Collections.newList();

	public Object encode (final Object javaObject) {
		if (javaObject == null) {
			return null;
		}
		for (final JavaToCrossLanguageEncoder e : this.encoders) {
			if (e.canEncode(javaObject)) {
				return e.encode(javaObject);
			}
		}
		Err.reportError("Encoder not found <" + javaObject.getClass() + "> for " + javaObject);
		return javaObject;
	}

	public void register (final JavaToCrossLanguageEncoder encoder) {
		this.encoders.add(encoder);
	}

}
