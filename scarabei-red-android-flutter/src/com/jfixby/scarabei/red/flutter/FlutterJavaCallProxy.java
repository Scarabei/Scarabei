
package com.jfixby.scarabei.red.flutter;

import java.lang.reflect.Method;

import com.jfixby.scarabei.api.assets.ID;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.flutter.FlutterPluginSpecs;
import com.jfixby.scarabei.api.flutter.FlutterPlugins;
import com.jfixby.scarabei.api.flutter.call.FlutterMethodCall;
import com.jfixby.scarabei.api.flutter.call.JavaMethodCall;
import com.jfixby.scarabei.api.json.Json;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.util.Utils;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;

public class FlutterJavaCallProxy implements MethodCallHandler {
	public static FlutterPluginSpecs filloutRegistration (final FlutterPluginSpecs specs) {
		specs.methodCallHandler = new FlutterJavaCallProxy();
		return specs;
	}

	@Override
	public void onMethodCall (final MethodCall call, final MethodChannel.Result result) {
		final String jsonString = (String)call.argument("jsonString");
		L.d("jsonString", jsonString);
		try {
			final FlutterMethodCall flutterCall = Json.deserializeFromString(FlutterMethodCall.class, jsonString);

			final JavaMethodCall javaCall = FlutterPlugins.decodeMethodCall(flutterCall);

			final ID methodFullName = javaCall.methodName;

			final String methodName = methodFullName.getLastStep();
			final ID className = methodFullName.parent();
			final Class<?> klass = Utils.classForName(className);
			L.d("javaCall.class", klass);
			L.d("javaCall.method", methodName);
			L.d("javaCall.arguments", flutterCall.arguments);

			final Class<?>[] argTypes = javaCall.argumentTypes;
			final Object[] argValues = javaCall.argumentValues;
			L.d("argTypes", Collections.newList(argTypes));
			L.d("argValues", Collections.newList(argValues));

			final Object javaInvokeResult;
			final Method method;
			if (argTypes.length == 0) {
				method = klass.getMethod(methodName);
				L.d("method", method);
				javaInvokeResult = method.invoke(null);
			} else {
				method = klass.getMethod(methodName, argTypes);
				L.d("method", method);
				javaInvokeResult = method.invoke(null, argValues);
			}
			L.d("javaInvokeResult", javaInvokeResult);

			final Object flutterInvokeResult = FlutterPlugins.encode(javaInvokeResult);

			result.success(flutterInvokeResult);
		} catch (final Exception e) {
			L.e(e);
			result.error(e.toString(), L.stackTraceToString(e), L.stackTraceToString(e));
			return;
		}

	}

}
