
package com.jfixby.scarabei.red.codecs;

import com.jfixby.scarabei.api.codecs.CrossLanguageToJavaDecoder;
import com.jfixby.scarabei.api.codecs.io.EncodedObject;
import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.log.L;

public class Decoders {

	final Map<String, CrossLanguageToJavaDecoder> decoders = Collections.newMap();

	public Object decode (final EncodedObject encodedObject) {
		if (encodedObject == null) {
			return null;
		}

		final String objectTypeName = objectTypeNames.get(encodedObject);

		if (objectTypeName == null) {
			Err.reportError("Missing object type for <" + encodedObject + ">");
			return null;
		}

		final CrossLanguageToJavaDecoder decoder = this.decoders.get(objectTypeName);
		if (decoder == null) {
			Err.reportError("Decoder not found <" + objectTypeName + "> " + encodedObject);
			return null;
		}

		final Object result = decoder.decode(encodedObject, objectTypeNames);
		return result;
	}

	public void registerDecoder (final CrossLanguageToJavaDecoder flutterToScarabeiDecoder) {
		final Collection<String> list = flutterToScarabeiDecoder.listSupportedTypeNames();
		for (final String name : list) {
			final CrossLanguageToJavaDecoder prev = this.decoders.get(name);
			if (prev != null) {
				L.e("Replacing CrossLanguageToJavaDecoder on type <" + name + ">");
				L.e(" removing", prev);
				L.e(" registring", flutterToScarabeiDecoder);
			}

			this.decoders.put(name, flutterToScarabeiDecoder);
			L.d("Registered CrossLanguageToJavaDecoder[" + name + "]", flutterToScarabeiDecoder);
		}
	}

}
