
package com.jfixby.scarabei.red.flutter.calls;

import com.jfixby.scarabei.api.json.Json;
import com.jfixby.scarabei.api.json.JsonString;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.red.reflect.CrossLanguageCallAdaptor;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;

public class FlutterJavaCallListener implements MethodCallHandler {

	public FlutterJavaCallListener () {
	}

	@Override
	public void onMethodCall (final MethodCall call, final MethodChannel.Result result) {
		try {
			final Object argument = call.argument("json");
// L.d("argument", argument);
			final String json = (String)argument;
			final JsonString inputJson = Json.newJsonString(json);
			final JsonString jsonRsult = CrossLanguageCallAdaptor.processCrossLanguageMethodCall(inputJson);
			result.success(jsonRsult.toString());
		} catch (final Exception e) {// report MethodCallHandler failure //should never happen!
			L.e(e);
			result.error(e.toString(), L.stackTraceToString(e), L.stackTraceToString(e));
			return;
		}

	}

}
