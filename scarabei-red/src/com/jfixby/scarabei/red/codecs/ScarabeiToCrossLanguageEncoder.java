
package com.jfixby.scarabei.red.codecs;

import java.util.ArrayList;
import java.util.HashMap;

import com.jfixby.scarabei.api.codecs.Codecs;
import com.jfixby.scarabei.api.codecs.CrossLanguageClassNames;
import com.jfixby.scarabei.api.codecs.JavaToCrossLanguageEncoder;
import com.jfixby.scarabei.api.codecs.io.EncodedObject;
import com.jfixby.scarabei.api.collections.Mapping;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.names.ID;
import com.jfixby.scarabei.api.sys.settings.ExecutionMode;

public class ScarabeiToCrossLanguageEncoder implements JavaToCrossLanguageEncoder {

	@Override
	public boolean canEncode (final Object javaObject) {
		if (javaObject == null) {
			return true;
		}
		if (javaObject instanceof ExecutionMode) {
			return true;
		}
		if (javaObject instanceof ID) {
			return true;
		}
		if (javaObject instanceof String) {
			return true;
		}
		if (javaObject instanceof Boolean) {
			return true;
		}
		if (javaObject instanceof Long) {
			return true;
		}
		if (javaObject instanceof Integer) {
			return true;
		}
		if (javaObject instanceof Byte) {
			return true;
		}
		if (javaObject instanceof java.util.Map) {
			return true;
		}
		if (javaObject instanceof Mapping) {
			return true;
		}
		if (javaObject instanceof Iterable) {
			return true;
		}

		return false;
	}

	@Override
	public EncodedObject encode (final Object javaObject) {
		if (javaObject == null) {
			return null;
		}
		if (javaObject instanceof ExecutionMode) {
			return EncodedObject.encodeExecutionMode((ExecutionMode)javaObject);
		}
		if (javaObject instanceof ID) {
			return EncodedObject.encodeID((ID)javaObject);
		}
		if (javaObject instanceof String) {
			return EncodedObject.encodeString((String)javaObject);
		}
		if (javaObject instanceof Boolean) {
			return EncodedObject.encodeBoolean((Boolean)javaObject);
		}
		if (javaObject instanceof Long) {
			return EncodedObject.encodeLong((Long)javaObject);
		}
		if (javaObject instanceof Integer) {
			return EncodedObject.encodeLong((Long)javaObject);
		}
		if (javaObject instanceof Byte) {
			return EncodedObject.encodeLong((Long)javaObject);
		}
		if (javaObject instanceof Mapping) {
			return Codecs.encode(((Mapping)javaObject).toJavaMap());
		}
		if (javaObject instanceof java.util.Map) {
			final java.util.Map map = (java.util.Map)javaObject;
			final java.util.Map<EncodedObject, EncodedObject> result = new HashMap<EncodedObject, EncodedObject>();
			for (final Object k : map.keySet()) {
				final Object v = map.get(k);
				final EncodedObject eK = Codecs.encode(k);
				final EncodedObject eV = Codecs.encode(v);
				result.put(eK, eV);
			}

			final EncodedObject encodedMap = new EncodedObject();
			encodedMap.value = result;
			encodedMap.type = CrossLanguageClassNames.MAP;
			return encodedMap;
		}
		if (javaObject instanceof Iterable) {
			final Iterable i = (Iterable)javaObject;
			final ArrayList<EncodedObject> result = new ArrayList<EncodedObject>();
			for (final Object e : i) {
				result.add(Codecs.encode(e));
			}
			final EncodedObject encodedList = new EncodedObject();
			encodedList.value = result;
			encodedList.type = CrossLanguageClassNames.LIST;
			return encodedList;
		}

		Err.reportError("Failed to encode <" + javaObject + ">");
		return null;

	}

}
