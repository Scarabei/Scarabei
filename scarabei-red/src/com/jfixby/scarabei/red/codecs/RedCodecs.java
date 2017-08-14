
package com.jfixby.scarabei.red.codecs;

import java.util.ArrayList;

import com.jfixby.scarabei.api.codecs.CodecsComponent;
import com.jfixby.scarabei.api.codecs.CrossLanguageToJavaDecoder;
import com.jfixby.scarabei.api.codecs.JavaMethodCall;
import com.jfixby.scarabei.api.codecs.JavaToCrossLanguageEncoder;
import com.jfixby.scarabei.api.codecs.calls.io.CrossLanguageMethodCall;
import com.jfixby.scarabei.api.codecs.calls.io.CrossLanguageMethodCallArgument;
import com.jfixby.scarabei.api.codecs.io.EncodedObject;

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
	public <T> T decode (final EncodedObject encodedObject) {
		return this.decoders.decode(encodedObject);
	}

	@Override
	public JavaMethodCall decodeMethodCall (final CrossLanguageMethodCall flutterCall) {
		final JavaMethodCall result = new JavaMethodCall();
		result.methodName = this.decode(flutterCall.methodName);
		if (flutterCall.arguments == null) {
			flutterCall.arguments = this.encode(new ArrayList());
		}
		result.argumentNames = new String[flutterCall.arguments.value.size()];
		result.argumentValues = new Object[flutterCall.arguments.value.size()];
		result.argumentTypes = new Class[flutterCall.arguments.value.size()];
		int i = 0;

		for (final CrossLanguageMethodCallArgument flutterArgument : flutterCall.arguments.value) {
			result.argumentNames[i] = this.decode(flutterArgument.argumentName);
			result.argumentValues[i] = this.decode(flutterArgument.argumentValue);
			if (result.argumentValues[i] == null) {
				throw new Error("Argument <" + result.argumentNames[i] + "> is null.");
			}
			result.argumentTypes[i] = result.argumentValues[i].getClass();
			i++;
		}
		return result;
	}

}
