
package com.jfixby.scarabei.red.flutter.calls;

import java.lang.reflect.Method;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.flutter.call.FlutterJavaCalls;
import com.jfixby.scarabei.api.flutter.call.FlutterMethodCall;
import com.jfixby.scarabei.api.flutter.call.JavaMethodCall;
import com.jfixby.scarabei.api.json.Json;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.names.ID;
import com.jfixby.scarabei.api.util.Utils;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;

public class FlutterJavaCallListener implements MethodCallHandler {

	@Override
	public void onMethodCall (final MethodCall call, final MethodChannel.Result result) {
		final String jsonString = (String)call.argument("invoke");
		L.d("jsonString", jsonString);
		try {
			final FlutterMethodCall flutterCall = Json.deserializeFromString(FlutterMethodCall.class, jsonString);

			final JavaMethodCall javaCall = FlutterJavaCalls.decodeMethodCall(flutterCall);

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

			final Object flutterInvokeResult = FlutterJavaCalls.encode(javaInvokeResult);

			result.success(flutterInvokeResult);
		} catch (final Exception e) {
			L.e(e);
			result.error(e.toString(), L.stackTraceToString(e), L.stackTraceToString(e));
			return;
		}

	}

}
