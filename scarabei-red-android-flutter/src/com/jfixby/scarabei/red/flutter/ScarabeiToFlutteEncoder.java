
package com.jfixby.scarabei.red.flutter;

import java.util.ArrayList;

import com.jfixby.scarabei.api.assets.ID;
import com.jfixby.scarabei.api.collections.Mapping;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.flutter.JavaToCrossLanguageEncoder;
import com.jfixby.scarabei.api.promise.Promise;
import com.jfixby.scarabei.api.sys.settings.ExecutionMode;

public class ScarabeiToFlutteEncoder implements JavaToCrossLanguageEncoder {

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
		if (javaObject instanceof Promise) {
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
	public Object encode (final Object javaObject) {
		if (javaObject == null) {
			return null;
		}
		if (javaObject instanceof ExecutionMode) {
			return javaObject + "";
		}
		if (javaObject instanceof ID) {
			return javaObject + "";
		}
		if (javaObject instanceof String) {
			return javaObject;
		}
		if (javaObject instanceof Boolean) {
			return javaObject;
		}
		if (javaObject instanceof Long) {
			return javaObject;
		}
		if (javaObject instanceof Integer) {
			return javaObject;
		}
		if (javaObject instanceof Byte) {
			return javaObject;
		}
		if (javaObject instanceof Mapping) {
			return ((Mapping)javaObject).toJavaMap();
		}
		if (javaObject instanceof java.util.Map) {
			return javaObject;
		}
		if (javaObject instanceof Iterable) {
			final Iterable i = (Iterable)javaObject;
			final ArrayList result = new ArrayList();
			for (final Object e : i) {
				result.add(e);
			}
			return result;
		}

		Err.reportError("Failed to encode <" + javaObject + ">");
		return null;

	}

}
