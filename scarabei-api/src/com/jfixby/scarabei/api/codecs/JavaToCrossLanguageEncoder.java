
package com.jfixby.scarabei.api.codecs;

import com.jfixby.scarabei.api.codecs.io.EncodedObject;

public interface JavaToCrossLanguageEncoder {

	boolean canEncode (Object javaObject);

	EncodedObject encode (Object javaObject);

}
