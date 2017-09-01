
package com.jfixby.scarabei.red.codecs;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.jfixby.scarabei.api.codecs.Codecs;
import com.jfixby.scarabei.api.codecs.CrossLanguageClassNames;
import com.jfixby.scarabei.api.codecs.CrossLanguageToJavaDecoder;
import com.jfixby.scarabei.api.codecs.calls.io.CrossLanguageMethodCall;
import com.jfixby.scarabei.api.codecs.calls.io.CrossLanguageMethodCallArgument;
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
		this.classes.put(CrossLanguageClassNames.BOOL, boolean.class);
		this.classes.put(CrossLanguageClassNames.INTEGER, long.class);
		this.classes.put(CrossLanguageClassNames.LIST, List.class);
		this.classes.put(CrossLanguageClassNames.Null, Void.class);
		this.classes.put(CrossLanguageClassNames.MethodCall, CrossLanguageMethodCall.class);
		this.classes.put(CrossLanguageClassNames.MethodCallArgument, CrossLanguageMethodCallArgument.class);
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

		if (objectType == null) {
			Err.reportError("Missing object type for <" + objectTypeName + ">");
			return null;
		}

		if (objectType == ID.class) {
			return Names.newID(encodedObject.value + "");
		}

		if (objectType == ExecutionMode.class) {
			return ExecutionMode.resolve(encodedObject.value + "");
		}

		if (objectType == CrossLanguageMethodCall.class) {

			final java.util.Map<String, Object> flutterCall = (java.util.Map<String, Object>)encodedObject.value;
			final java.util.Map<String, Object> eName = (java.util.Map<String, Object>)flutterCall
				.get(CrossLanguageMethodCall.METHOD_NAME);
			final java.util.Map<String, Object> eArguments = (java.util.Map<String, Object>)flutterCall
				.get(CrossLanguageMethodCall.METHOD_ARGUMENTS);

			final EncodedObject EName = new EncodedObject();
			EName.type = (String)eName.get("type");
			EName.value = eName.get("value");

			final EncodedObject EArguments = new EncodedObject();
			EArguments.type = (String)eArguments.get("type");
			EArguments.value = eArguments.get("value");

			final CrossLanguageMethodCall call = new CrossLanguageMethodCall();
			call.methodName = Codecs.decode(EName);
			call.arguments = Codecs.decode(EArguments);

			return call;

		}

		if (objectType == Void.class) {
			return null;
		}

		if (objectType == CrossLanguageMethodCallArgument.class) {
			final java.util.Map<String, java.util.Map<String, Object>> argument = (java.util.Map<String, java.util.Map<String, Object>>)(encodedObject.value);
			final CrossLanguageMethodCallArgument result = new CrossLanguageMethodCallArgument();
			final java.util.Map<String, Object> eName = argument.get(CrossLanguageMethodCallArgument.METHOD_ARGUMENT_NAME);
			final EncodedObject EName = new EncodedObject();
			EName.type = (String)eName.get("type");
			EName.value = eName.get("value");

			final java.util.Map<String, Object> eValue = argument.get(CrossLanguageMethodCallArgument.METHOD_ARGUMENT_VALUE);

			final EncodedObject EValue = new EncodedObject();
			EValue.type = (String)eValue.get("type");
			EValue.value = eValue.get("value");

			result.argumentName = Codecs.decode(EName);
			result.argumentValue = Codecs.decode(EValue);
			result.argumentType = Codecs.resolveType(EValue.type);
			return result;
		}

		if (objectType == long.class) {
			return (long)Long.parseLong(encodedObject.value + "");
		}

		if (objectType == String.class) {
			return encodedObject.value + "";
		}

		if (objectType == boolean.class) {
			return (boolean)Boolean.parseBoolean(encodedObject.value + "");
		}

		if (objectType == List.class) {
			final List<java.util.Map<String, Object>> list = (List<java.util.Map<String, Object>>)encodedObject.value;
			final ArrayList<Object> result = new ArrayList<Object>();
			for (final java.util.Map<String, Object> Ei : list) {
				final EncodedObject toDecode = new EncodedObject();
				if (Ei != null) {
					toDecode.type = (String)Ei.get("type");
					toDecode.value = Ei.get("value");
					final Object Ri = Codecs.decode(toDecode);
					result.add(Ri);
				} else {
					result.add(null);
				}
			}
			return result;
		}

		if (objectType == Map.class) {
			final LinkedHashMap result = new LinkedHashMap();
			final List<List<java.util.Map>> pairs = (List<List<java.util.Map>>)encodedObject.value;
			for (final List<java.util.Map> p : pairs) {
				final java.util.Map eKey = p.get(0);
				final java.util.Map eValue = p.get(1);
				final Object key = (this.toDecode(eKey));
				final Object value = (this.toDecode(eValue));
				result.put(key, value);
			}
			return result;
		}

		Err.reportError("Missing object type for <" + encodedObject.type + "> = " + encodedObject.value);

		return encodedObject.value;
	}

	private Object toDecode (final java.util.Map Ei) {

		final EncodedObject toDecode = new EncodedObject();
		if (Ei != null) {
			toDecode.type = (String)Ei.get("type");
			toDecode.value = Ei.get("value");
			final Object Ri = Codecs.decode(toDecode);
			return Ri;
		} else {
			return null;
		}
	}

	@Override
	public Class<?> resolveType (final String objectTypeName) {
		return this.classes.get(objectTypeName);
	}

}
