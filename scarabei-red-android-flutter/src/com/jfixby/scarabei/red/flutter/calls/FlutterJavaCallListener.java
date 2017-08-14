
package com.jfixby.scarabei.red.flutter.calls;

import java.lang.reflect.Method;

import com.jfixby.scarabei.api.codecs.Codecs;
import com.jfixby.scarabei.api.codecs.JavaMethodCall;
import com.jfixby.scarabei.api.codecs.calls.io.CrossLanguageMethodCall;
import com.jfixby.scarabei.api.codecs.calls.io.CrossLanguageMethodCallResult;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.json.Json;
import com.jfixby.scarabei.api.json.JsonString;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.names.ID;
import com.jfixby.scarabei.api.util.Utils;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;

public class FlutterJavaCallListener implements MethodCallHandler {

	public FlutterJavaCallListener () {
// final FlutterPluginSpecs specs = FlutterPlugins.newPluginSpecs();
// specs.methodCallHandler = this;
// specs.channelName = Names.newID(this.getClass().getCanonicalName());
// FlutterPlugins.registerPlugin(specs);

	}

	@Override
	public void onMethodCall (final MethodCall call, final MethodChannel.Result result) {
// final Object argument = call.argument("invoke");
		final Object argument = call.argument("json");
		L.d("argument", argument);
		final String json = (String)argument;
		L.d("json", json);
		try {
			final CrossLanguageMethodCall encodedFlutterCall = Json.deserializeFromString(CrossLanguageMethodCall.class, json);

			final JavaMethodCall javaCall = Codecs.decodeMethodCall(encodedFlutterCall);

			final ID methodFullName = javaCall.methodName;

			final String methodName = methodFullName.getLastStep();
			final ID className = methodFullName.parent();
			final Class<?> klass = Utils.classForName(className);
			L.d("javaCall.class", klass);
			L.d("javaCall.method", methodName);
// L.d("javaCall.arguments", javaCall.arguments);

			final Class<?>[] argTypes = javaCall.argumentTypes;
			final Object[] argValues = javaCall.argumentValues;
			L.d("argTypes", Collections.newList(argTypes));
			L.d("argValues", Collections.newList(argValues));
			Throwable E = null;
			Object javaInvokeResult;
			final Method method;
			final CrossLanguageMethodCallResult callResult = new CrossLanguageMethodCallResult();
			try {
				if (argTypes.length == 0) {
					method = klass.getMethod(methodName);
					L.d("method", method);
					javaInvokeResult = method.invoke(null);
				} else {
					method = klass.getMethod(methodName, argTypes);
					L.d("method", method);
					javaInvokeResult = method.invoke(null, argValues);
				}
				callResult.success = true;
			} catch (final Throwable e) {
				callResult.success = false;
				javaInvokeResult = null;
				L.e(e);
				E = e;
			}
			L.d("javaInvokeResult", javaInvokeResult);
			if (callResult.success) {
				callResult.result = Codecs.encode(javaInvokeResult);
			} else {
				callResult.errorMessage = L.stackTraceToString(E);
			}

			final JsonString jsonRsult = Json.serializeToString(callResult);

			result.success(jsonRsult.toString());
		} catch (final Exception e) {
			L.e(e);
			result.error(e.toString(), L.stackTraceToString(e), L.stackTraceToString(e));
			return;
		}

	}

}
