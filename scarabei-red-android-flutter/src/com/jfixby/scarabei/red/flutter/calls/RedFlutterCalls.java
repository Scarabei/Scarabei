
package com.jfixby.scarabei.red.flutter.calls;

import com.jfixby.scarabei.api.assets.Names;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.LambdaMap;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.flutter.call.CrossLanguageToJavaDecoder;
import com.jfixby.scarabei.api.flutter.call.FlutterCallsComponent;
import com.jfixby.scarabei.api.flutter.call.FlutterMethodCall;
import com.jfixby.scarabei.api.flutter.call.FlutterMethodCallArgument;
import com.jfixby.scarabei.api.flutter.call.JavaMethodCall;
import com.jfixby.scarabei.api.flutter.call.JavaToCrossLanguageEncoder;
import com.jfixby.scarabei.api.flutter.plugins.FlutterPluginSpecs;
import com.jfixby.scarabei.api.flutter.plugins.FlutterPlugins;

public class RedFlutterCalls implements FlutterCallsComponent {
	final Encoders encoders = new Encoders();
	final Decoders decoders = new Decoders();
	final FlutterJavaCallListener flutterListener = new FlutterJavaCallListener();

	public RedFlutterCalls () {
		final FlutterPluginSpecs specs = FlutterPlugins.newPluginSpecs();
		specs.methodCallHandler = this.flutterListener;
		FlutterPlugins.registerPlugin(specs);

		this.registerDecoder(new FlutterToScarabeiDecoder());
		this.registerEncoder(new ScarabeiToFlutteEncoder());

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
	public Object encode (final Object javaObject) {
		return this.encoders.encode(javaObject);
	}

	@Override
	public Object decode (final Object encodedObject, final LambdaMap<Object, String> objectTypeNames) {
		return this.decoders.decode(encodedObject, objectTypeNames);
	}

	@Override
	public JavaMethodCall decodeMethodCall (final FlutterMethodCall flutterCall) {
		final JavaMethodCall result = new JavaMethodCall();
		result.methodName = Names.newID(flutterCall.methodName);
		result.argumentNames = new String[flutterCall.arguments.length];
		int i = 0;

		final Map<Object, String> objectTypeNames = Collections.newMap();

		for (final FlutterMethodCallArgument flutterArgument : flutterCall.arguments) {
			objectTypeNames.put(flutterArgument.argumentValue, flutterArgument.argumentType);
		}

		for (final FlutterMethodCallArgument flutterArgument : flutterCall.arguments) {
			result.argumentNames[i] = flutterArgument.argumentName;
			result.argumentValues[i] = this.decode(flutterArgument.argumentName, objectTypeNames);
			result.argumentTypes[i] = result.argumentValues[i].getClass();
			i++;
		}
		return result;
	}

}
