
package com.jfixby.scarabei.red.codecs;

import com.jfixby.scarabei.api.codecs.CodecsComponent;
import com.jfixby.scarabei.api.codecs.CrossLanguageToJavaDecoder;
import com.jfixby.scarabei.api.codecs.JavaMethodCall;
import com.jfixby.scarabei.api.codecs.JavaToCrossLanguageEncoder;
import com.jfixby.scarabei.api.codecs.calls.crosslang.MethodCallArgument;
import com.jfixby.scarabei.api.codecs.calls.io.CrossLanguageMethodCall;
import com.jfixby.scarabei.api.codecs.io.EncodedObject;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.names.Names;

public class RedCodecs implements CodecsComponent {
	final Encoders encoders = new Encoders();
	final Decoders decoders = new Decoders();

	public RedCodecs () {

		this.registerDecoder(new CrossLanguageToScarabeiDecoder());
		this.registerEncoder(new ScarabeiToCrossLanguageEncoder());

	}

	@Override
	public void registerEncoder (final JavaToCrossLanguageEncoder encoder) {
		this.encoders.register(encoder);
	}

	@Override
	public void registerDecoder (final CrossLanguageToJavaDecoder decoder) {
		this.decoders.registerDecoder(decoder);
	}

	@Override
	public EncodedObject encode (final Object javaObject) {
		return this.encoders.encode(javaObject);
	}

	@Override
	public Object decode (final EncodedObject encodedObject) {
		return this.decoders.decode(encodedObject);
	}

	@Override
	public JavaMethodCall decodeMethodCall (final CrossLanguageMethodCall flutterCall) {
		final JavaMethodCall result = new JavaMethodCall();
		result.methodName = Names.newID(flutterCall.methodName);
		result.argumentNames = new String[flutterCall.arguments.length];
		int i = 0;

		final Map<Object, String> objectTypeNames = Collections.newMap();

		for (final MethodCallArgument flutterArgument : flutterCall.arguments) {
			objectTypeNames.put(flutterArgument.argumentValue, flutterArgument.argumentType);
		}

		for (final MethodCallArgument flutterArgument : flutterCall.arguments) {
			result.argumentNames[i] = flutterArgument.argumentName;
			result.argumentValues[i] = this.decode(flutterArgument.argumentName, objectTypeNames);
			result.argumentTypes[i] = result.argumentValues[i].getClass();
			i++;
		}
		return result;
	}

}
