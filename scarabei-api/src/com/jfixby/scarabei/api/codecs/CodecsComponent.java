
package com.jfixby.scarabei.api.codecs;

import com.jfixby.scarabei.api.codecs.io.EncodedObject;

public interface CodecsComponent {

	<T> T decode (EncodedObject object);

	EncodedObject encode (Object javaObject);

	void registerDecoder (CrossLanguageToJavaDecoder flutterToScarabeiDecoder);

	void registerEncoder (JavaToCrossLanguageEncoder encoder);

	Class<?> resolveType (String type);

}
