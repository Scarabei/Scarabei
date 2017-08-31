
package com.jfixby.scarabei.red.reflect;

import com.jfixby.scarabei.api.codecs.Codecs;
import com.jfixby.scarabei.api.codecs.calls.io.CrossLanguageMethodCall;
import com.jfixby.scarabei.api.codecs.calls.io.CrossLanguageMethodCallResult;
import com.jfixby.scarabei.api.codecs.io.EncodedObject;
import com.jfixby.scarabei.api.json.Json;
import com.jfixby.scarabei.api.json.JsonString;
import com.jfixby.scarabei.api.log.L;

public class CrossLanguageCallAdaptor {
	public static final JsonString processCrossLanguageMethodCall (final JsonString inputJson) {
// L.d("inputJson", inputJson);

		final EncodedObject encodedFlutterCall = Json.deserializeFromString(EncodedObject.class, inputJson);

		final CrossLanguageMethodCall crossCall = Codecs.decode(encodedFlutterCall);

		CrossLanguageMethodCallResult crossResult;
		try {
			final JavaMethodCall javaCall = JavaMethodCall.fromCrossLanguageMethodCall(crossCall);
			final JavaMethodCallResult javaCallResult = JavaCallExecutor.executeCall(javaCall);
			crossResult = javaCallResult.toCrossLanguageCallResult();
		} catch (final ReflectiveOperationException e) {
			e.printStackTrace();
			crossResult = new CrossLanguageMethodCallResult();
			crossResult.methodName = crossCall.methodName;
			crossResult.success = false;
			crossResult.error = L.stackTraceToString(e);
		}
		final EncodedObject encodedCallResult = Codecs.encode(crossResult);

		final JsonString jsonRsult = Json.serializeToString(encodedCallResult);
		return jsonRsult;

	}

}
