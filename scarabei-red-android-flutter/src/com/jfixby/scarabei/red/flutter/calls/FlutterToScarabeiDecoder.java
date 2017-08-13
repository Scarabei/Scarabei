
package com.jfixby.scarabei.red.flutter.calls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jfixby.scarabei.api.assets.ID;
import com.jfixby.scarabei.api.assets.Names;
import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.LambdaMap;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.flutter.call.CrossLanguageClassNames;
import com.jfixby.scarabei.api.flutter.call.CrossLanguageToJavaDecoder;
import com.jfixby.scarabei.api.flutter.call.FlutterJavaCalls;
import com.jfixby.scarabei.api.flutter.call.ScarabeiClassNames;
import com.jfixby.scarabei.api.sys.settings.ExecutionMode;

public class FlutterToScarabeiDecoder implements CrossLanguageToJavaDecoder {

	final Map<String, Class<?>> classes = Collections.newMap();

	@Override
	public Collection<String> listSupportedTypeNames () {
		return this.classes.keys();
	}

	FlutterToScarabeiDecoder () {
		this.classes.put(CrossLanguageClassNames.STRING, String.class);
		this.classes.put(CrossLanguageClassNames.BOOL, Boolean.class);
		this.classes.put(CrossLanguageClassNames.INTEGER, Integer.class);
		this.classes.put(CrossLanguageClassNames.LONG, Long.class);
		this.classes.put(CrossLanguageClassNames.BYTE, Byte.class);
		this.classes.put(CrossLanguageClassNames.LIST, List.class);
		this.classes.put(CrossLanguageClassNames.MAP, Map.class);

		this.classes.put(ScarabeiClassNames.ID, ID.class);
		this.classes.put(ScarabeiClassNames.ExecutionMode, ExecutionMode.class);

	}

	@Override
	public Object decode (final Object encodedObject, final LambdaMap<Object, String> objectTypeNames) {
		if (encodedObject == null) {
			return null;
		}

		final String objectTypeName = objectTypeNames.get(encodedObject);

		if (objectTypeName == null) {
			Err.reportError("Missing object type for <" + encodedObject + ">");
			return null;
		}

		final Class<?> objectType = this.classes.get(objectTypeName);

		if (objectType == ID.class) {
			return Names.newID(encodedObject + "");
		}

		if (objectType == ExecutionMode.class) {
			return ExecutionMode.resolve(encodedObject + "");
		}

		if (objectType == List.class) {
			final List<?> list = (List<?>)encodedObject;
			final ArrayList<Object> result = new ArrayList<>();
			for (final Object Ei : list) {
				final Object Ri = FlutterJavaCalls.decode(Ei, objectTypeNames);
				result.add(Ri);
			}
			return result;
		}
		if (objectType == List.class) {
			final Map<?, ?> map = (Map<?, ?>)encodedObject;
			final HashMap<Object, Object> result = new HashMap<>();
			for (final Object Ki : map.keys()) {
				final Object Vi = map.get(Ki);

				final Object RKi = FlutterJavaCalls.decode(Ki, objectTypeNames);
				final Object RVi = FlutterJavaCalls.decode(Vi, objectTypeNames);
				result.put(RKi, RVi);
			}
			return result;
		}

		return encodedObject;
	}

}
