
package com.jfixby.scarabei.red.codecs;

import java.util.ArrayList;
import java.util.HashMap;

import com.jfixby.scarabei.api.codecs.Codecs;
import com.jfixby.scarabei.api.codecs.CrossLanguageClassNames;
import com.jfixby.scarabei.api.codecs.JavaToCrossLanguageEncoder;
import com.jfixby.scarabei.api.codecs.calls.io.CrossLanguageMethodCallResult;
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
		if (javaObject instanceof Float) {
			return true;
		}
		if (javaObject instanceof Double) {
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

		if (javaObject instanceof CrossLanguageMethodCallResult) {
			return true;
		}

		return false;
	}

	@Override
	public EncodedObject encode (final Object javaObject) {
		if (javaObject == null) {
			return EncodedObject.encodeNull();
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
			return EncodedObject.encodeLong(((Integer)javaObject) * 1L);
		}

		if (javaObject instanceof Float) {
			return EncodedObject.encodeDouble(((Float)javaObject) * 1D);
		}
		if (javaObject instanceof Double) {
			return EncodedObject.encodeDouble(((Double)javaObject));
		}

		if (javaObject instanceof Byte) {
			return EncodedObject.encodeLong(((Byte)javaObject) * 1L);
		}
		if (javaObject instanceof Mapping) {
			return Codecs.encode(((Mapping)javaObject).toJavaMap());
		}

		if (javaObject instanceof CrossLanguageMethodCallResult) {
			final CrossLanguageMethodCallResult res = (CrossLanguageMethodCallResult)javaObject;
			final EncodedObject e = new EncodedObject();

			e.type = CrossLanguageClassNames.MethodCallResult;
			final java.util.Map map = new HashMap();
			map.put("error", Codecs.encode(res.error));
			map.put("success", Codecs.encode(res.success));
			map.put("result", Codecs.encode(res.result));
			e.value = map;
			return e;
		}

		if (javaObject instanceof java.util.Map) {
			final java.util.Map map = (java.util.Map)javaObject;

			final ArrayList<ArrayList<EncodedObject>> pairsList = new ArrayList<ArrayList<EncodedObject>>();
			for (final Object k : map.keySet()) {
				final Object v = map.get(k);
				final EncodedObject encodedKey = Codecs.encode(k);
				final EncodedObject encodedVal = Codecs.encode(v);
				final ArrayList<EncodedObject> ePair = new ArrayList<EncodedObject>();
				ePair.add(encodedKey);
				ePair.add(encodedVal);
				pairsList.add(ePair);
			}
			final EncodedObject encodedMap = new EncodedObject();
			encodedMap.value = pairsList;
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
