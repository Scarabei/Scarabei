
package com.jfixby.scarabei.api.codecs;

import com.jfixby.scarabei.api.codecs.calls.io.CrossLanguageMethodCall;
import com.jfixby.scarabei.api.codecs.io.EncodedObject;

public interface CodecsComponent {

	Object decode (EncodedObject object);

	EncodedObject encode (Object javaObject);

	void registerDecoder (CrossLanguageToJavaDecoder flutterToScarabeiDecoder);

	void registerEncoder (JavaToCrossLanguageEncoder encoder);

	JavaMethodCall decodeMethodCall (CrossLanguageMethodCall flutterCall);

}
