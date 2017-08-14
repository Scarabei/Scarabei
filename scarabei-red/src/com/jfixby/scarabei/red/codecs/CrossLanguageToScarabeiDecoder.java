
package com.jfixby.scarabei.red.codecs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jfixby.scarabei.api.codecs.Codecs;
import com.jfixby.scarabei.api.codecs.CrossLanguageClassNames;
import com.jfixby.scarabei.api.codecs.CrossLanguageToJavaDecoder;
import com.jfixby.scarabei.api.codecs.io.EncodedObject;
import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.names.ID;
import com.jfixby.scarabei.api.names.Names;
import com.jfixby.scarabei.api.sys.settings.ExecutionMode;

public class CrossLanguageToScarabeiDecoder implements CrossLanguageToJavaDecoder {

	final Map<String, Class<?>> classes = Collections.newMap();

	@Override
	public Collection<String> listSupportedTypeNames () {
		return this.classes.keys();
	}

	CrossLanguageToScarabeiDecoder () {
		this.classes.put(CrossLanguageClassNames.STRING, String.class);
		this.classes.put(CrossLanguageClassNames.BOOL, Boolean.class);
		this.classes.put(CrossLanguageClassNames.INTEGER, Long.class);
		this.classes.put(CrossLanguageClassNames.LIST, List.class);
		this.classes.put(CrossLanguageClassNames.MAP, Map.class);
		this.classes.put(ID.class.getCanonicalName(), ID.class);
		this.classes.put(ExecutionMode.class.getCanonicalName(), ExecutionMode.class);

	}

	@Override
	public Object decode (final EncodedObject encodedObject) {
		if (encodedObject == null) {
			return null;
		}

		final String objectTypeName = encodedObject.type;

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
			final List<EncodedObject> list = (List<EncodedObject>)encodedObject.value;
			final ArrayList<Object> result = new ArrayList<Object>();
			for (final EncodedObject Ei : list) {
				final Object Ri = Codecs.decode(Ei);
				result.add(Ri);
			}
			return result;
		}
		if (objectType == Map.class) {
			final Map<EncodedObject, EncodedObject> map = (Map<EncodedObject, EncodedObject>)encodedObject.value;
			final HashMap<Object, Object> result = new HashMap<Object, Object>();
			for (final EncodedObject Ki : map.keys()) {
				final EncodedObject Vi = map.get(Ki);

				final Object RKi = Codecs.decode(Ki);
				final Object RVi = Codecs.decode(Vi);
				result.put(RKi, RVi);
			}
			return result;
		}

		return encodedObject.value;
	}

}
