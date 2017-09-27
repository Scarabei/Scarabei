
package com.jfixby.scarabei.api.codecs.io;

import com.jfixby.scarabei.api.codecs.CrossLanguageClassNames;
import com.jfixby.scarabei.api.file.LocalFile;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.names.ID;
import com.jfixby.scarabei.api.sys.settings.ExecutionMode;

public class EncodedObject<T> {

	public String type;
	public T value;

	public static <T> EncodedObject<T> encodeObject(final T value, final String typeName) {
		final EncodedObject<T> encoded = new EncodedObject<T>();
		encoded.value = value;
		encoded.type = typeName;
		return encoded;
	}

	public static EncodedObject<String> encodeString(final String string) {
		return EncodedObject.encodeObject(string, CrossLanguageClassNames.STRING);
	}

	public static EncodedObject<LocalFile> encodeLocalFile(final LocalFile file) {
		return EncodedObject.encodeObject(file, CrossLanguageClassNames.LOCAL_FILE);
	}

	public static EncodedObject<String> encodeID(final ID id) {
		return EncodedObject.encodeObject(id.toString(), ID.class.getCanonicalName());
	}

	public static EncodedObject<String> encodeExecutionMode(final ExecutionMode javaObject) {
		return EncodedObject.encodeObject(javaObject.toString(), ExecutionMode.class.getCanonicalName());
	}

	public static EncodedObject<Long> encodeLong(final Long javaObject) {
		return EncodedObject.encodeObject(javaObject, CrossLanguageClassNames.INTEGER);
	}

	public static EncodedObject<Double> encodeDouble(final Double javaObject) {
		return EncodedObject.encodeObject(javaObject, CrossLanguageClassNames.DOUBLE);
	}

	public static EncodedObject<String> encodeException(final Throwable javaObject) {
		return EncodedObject.encodeObject(L.stackTraceToString(javaObject), CrossLanguageClassNames.EXCEPTION);
	}

	public static EncodedObject<Boolean> encodeBoolean(final Boolean javaObject) {
		return EncodedObject.encodeObject(javaObject, CrossLanguageClassNames.BOOL);
	}

	public static EncodedObject encodeNull() {
		return EncodedObject.encodeObject(null, CrossLanguageClassNames.Null);
	}

}
