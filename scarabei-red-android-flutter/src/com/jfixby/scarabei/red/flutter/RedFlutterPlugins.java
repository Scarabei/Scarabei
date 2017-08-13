
package com.jfixby.scarabei.red.flutter;

import java.lang.reflect.Method;

import com.jfixby.scarabei.api.assets.ID;
import com.jfixby.scarabei.api.assets.Names;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.LambdaMap;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.flutter.CrossLanguageToJavaDecoder;
import com.jfixby.scarabei.api.flutter.FlutterPlugin;
import com.jfixby.scarabei.api.flutter.FlutterPluginSpecs;
import com.jfixby.scarabei.api.flutter.FlutterPluginsComponent;
import com.jfixby.scarabei.api.flutter.JavaToCrossLanguageEncoder;
import com.jfixby.scarabei.api.flutter.call.FlutterMethodCall;
import com.jfixby.scarabei.api.flutter.call.FlutterMethodCallArgument;
import com.jfixby.scarabei.api.flutter.call.JavaMethodCall;
import com.jfixby.scarabei.api.log.L;

import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugin.common.PluginRegistry.Registrar;

public class RedFlutterPlugins implements FlutterPluginsComponent {

	private final PluginRegistry registry;
	final Encoders encoders = new Encoders();
	final Decoders decoders = new Decoders();

	public RedFlutterPlugins (final PluginRegistry registry) {
		this.registry = registry;

		this.registerPlugin(FlutterJavaCallProxy.filloutRegistration(this.newPluginSpecs()));

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

	void registerPlugin (final ID methodCallHandlerClassName, final ID channelName, MethodCallHandler instance) {
		final ID className = methodCallHandlerClassName;
		final Registrar registrar = this.registry.registrarFor(className.toString());

		final ClassLoader classLoader = this.getClass().getClassLoader();
		Class<MethodCallHandler> klass;
		try {
			klass = (Class<MethodCallHandler>)Class.forName(methodCallHandlerClassName.toString(), true, classLoader);
		} catch (final ClassNotFoundException e) {
			klass = null;
			L.e("FlutterPlugin failed[" + channelName + "]");
			Err.reportError(e);
		}

		if (instance == null) {
			try {
				instance = klass.newInstance();
			} catch (final InstantiationException | IllegalAccessException e) {
				L.e("failed to create instance of the ", klass);
			}
		}

		if (instance != null) {
			final MethodChannel channel = new MethodChannel(registrar.messenger(), channelName.toString());
			channel.setMethodCallHandler(instance);
		} else {

			final String methodname = "registerWith";
			Method registerWith;
			try {
				registerWith = klass.getMethod(methodname, Registrar.class);
			} catch (final NoSuchMethodException e) {
				L.e("FlutterPlugin failed[" + channelName + "]");
				Err.reportError(e);
				registerWith = null;
			}

			try {
				registerWith.invoke(null, registrar);
// /*
// * public static void registerWith(Registrar registrar) {
// * MethodChannel channel = new MethodChannel(registrar.messenger(), "plugins.flutter.io/url_launcher");
// * UrlLauncherPlugin instance = new UrlLauncherPlugin(registrar.activity());
// * channel.setMethodCallHandler(instance);
// * }
// */
			} catch (final Exception e) {
				L.e("FlutterPlugin failed[" + channelName + "]");
				Err.reportError(e);
			}

		}

		L.d("FlutterPlugin registered[" + channelName + "]");

	}

	@Override
	public FlutterPluginSpecs newPluginSpecs () {
		return new FlutterPluginSpecs();
	}

	@Override
	public FlutterPlugin registerPlugin (final FlutterPluginSpecs specs) {
		return new RedFlutterPlugin(this, specs);
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
