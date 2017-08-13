
package com.jfixby.scarabei.api.flutter.call;

import com.jfixby.scarabei.api.collections.LambdaMap;

public interface FlutterCallsComponent {

	Object decode (Object encodedObject, LambdaMap<Object, String> objectTypeNames);

	Object encode (Object javaObject);

	void registerDecoder (CrossLanguageToJavaDecoder flutterToScarabeiDecoder);

	void registerEncoder (JavaToCrossLanguageEncoder encoder);

	JavaMethodCall decodeMethodCall (FlutterMethodCall flutterCall);

}
