
package com.jfixby.scarabei.red.flutter.plugins.android.sys;

import java.lang.reflect.Method;

import com.jfixby.scarabei.api.assets.ID;
import com.jfixby.scarabei.api.assets.Names;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.flutter.plugins.FlutterPluginSpecs;
import com.jfixby.scarabei.api.json.Json;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.util.Utils;

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
		try {
			final FlutterMethodCall flutterCall = Json.deserializeFromString(FlutterMethodCall.class, jsonString);
			final ID methodFullName = Names.newID(flutterCall.methodName);

			final String methodName = methodFullName.getLastStep();
			final ID className = methodFullName.parent();
			final Class<?> klass = Utils.classForName(className);
			L.d("flutterCall.class", klass);
			L.d("flutterCall.method", methodName);
			L.d("flutterCall.arguments", flutterCall.arguments);

			final Class<?>[] argTypes = flutterCall.listArgumetTypes();
			final Object[] argValues = flutterCall.listArgumetValues();
			L.d("argTypes", Collections.newList(argTypes));
			L.d("argValues", Collections.newList(argValues));

			final Object invokeResult;
			if (argTypes.length == 0) {
				final Method method = klass.getMethod(methodName);
				L.d("method", method);
				invokeResult = method.invoke(null);
			} else {
				final Method method = klass.getMethod(methodName, argTypes);
				L.d("method", method);
				invokeResult = method.invoke(null, argValues);
			}
			L.d("invokeResult", invokeResult);
			L.e("invokeResult", invokeResult);

			result.success(invokeResult);
		} catch (final Exception e) {
			L.e(e);
			result.error(e.toString(), L.stackTraceToString(e), L.stackTraceToString(e));
			return;
		}

	}

}
