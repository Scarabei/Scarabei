
package com.jfixby.scarabei.red.reflect;

import com.jfixby.scarabei.api.codecs.Codecs;
import com.jfixby.scarabei.api.codecs.calls.io.CrossLanguageMethodCall;
import com.jfixby.scarabei.api.codecs.calls.io.CrossLanguageMethodCallResult;
import com.jfixby.scarabei.api.codecs.io.EncodedObject;
import com.jfixby.scarabei.api.json.Json;
import com.jfixby.scarabei.api.json.JsonString;

public class CrossLanguageCallAdaptor {
	public static final JsonString processCrossLanguageMethodCall (final JsonString inputJson)
		throws ReflectiveOperationException {
// L.d("inputJson", inputJson);

		final EncodedObject encodedFlutterCall = Json.deserializeFromString(EncodedObject.class, inputJson);

		final CrossLanguageMethodCall crossCall = Codecs.decode(encodedFlutterCall);

		final JavaMethodCall javaCall;
		JavaMethodCallResult javaCallResult;

		javaCall = JavaMethodCall.fromCrossLanguageMethodCall(crossCall);

		javaCallResult = JavaCallExecutor.executeCall(javaCall);

		final CrossLanguageMethodCallResult crossResult = javaCallResult.toCrossLanguageCallResult();

		final EncodedObject encodedCallResult = Codecs.encode(crossResult);

		final JsonString jsonRsult = Json.serializeToString(encodedCallResult);

// L.d("jsonRsult", jsonRsult);

		return jsonRsult;

	}
}
