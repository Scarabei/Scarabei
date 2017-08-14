
package com.jfixby.scarabei.api.codecs.io;

import com.jfixby.scarabei.api.codecs.CrossLanguageClassNames;
import com.jfixby.scarabei.api.names.ID;
import com.jfixby.scarabei.api.sys.settings.ExecutionMode;

public class EncodedObject<T> {

	public String type;
	public T value;

	public static EncodedObject<String> encodeString (final String string) {
		return encodeObject(string, CrossLanguageClassNames.STRING);
	}

	public static <T> EncodedObject<T> encodeObject (final T value, final String typeName) {
		final EncodedObject<T> encoded = new EncodedObject<T>();
		encoded.value = value;
		encoded.type = typeName;
		return encoded;
	}

	public static EncodedObject<String> encodeID (final ID id) {
		return encodeObject(id.toString(), ID.class.getCanonicalName());
	}

	public static EncodedObject<String> encodeExecutionMode (final ExecutionMode javaObject) {
		return encodeObject(javaObject.toString(), ExecutionMode.class.getCanonicalName());
	}

	public static EncodedObject<Long> encodeLong (final Long javaObject) {
		return encodeObject(javaObject, CrossLanguageClassNames.INTEGER);
	}

	public static EncodedObject<Boolean> encodeBoolean (final Boolean javaObject) {
		return encodeObject(javaObject, CrossLanguageClassNames.BOOL);
	}

}
