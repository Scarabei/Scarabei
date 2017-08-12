
package com.jfixby.scarabei.red.flutter.plugins.android.sys;

import java.lang.reflect.Method;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.flutter.plugins.FlutterPluginSpecs;
import com.jfixby.scarabei.api.json.Json;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.sys.settings.SystemSettings;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;

public class SharedPreferencesPlugin implements MethodCallHandler {

	public static FlutterPluginSpecs filloutRegistration (final FlutterPluginSpecs specs) {
		specs.methodCallHandler = new SharedPreferencesPlugin();
		return specs;
	}

	@Override
	public void onMethodCall (final MethodCall call, final MethodChannel.Result result) {
		final String jsonString = (String)call.argument("jsonString");
		L.d("jsonString", jsonString);
		final FlutterMethodCall flutterCall = Json.deserializeFromString(FlutterMethodCall.class, jsonString);
		L.d("flutterCall.arguments", flutterCall.arguments);
		final Class<SystemSettings> klass = SystemSettings.class;
		final Class<?>[] argTypes = flutterCall.listArgumetTypes();
		final Object[] argValues = flutterCall.listArgumetValues();
		L.d("argTypes", Collections.newList(argTypes));
		L.d("argValues", Collections.newList(argValues));
		final String methodName = flutterCall.methodName;
		try {
			if (argTypes.length == 0) {
				final Method registerWith = klass.getMethod(methodName);
				final Object invokeResult = registerWith.invoke(null);
			} else {
				final Method registerWith = klass.getMethod(methodName, argTypes);
				final Object invokeResult = registerWith.invoke(null, argValues);
			}
			L.d("invokeResult", invokeResult);
			result.success(invokeResult);
		} catch (final Exception e) {
			result.error(e.toString(), L.stackTraceToString(e), e);
			return;
		}

	}

}
