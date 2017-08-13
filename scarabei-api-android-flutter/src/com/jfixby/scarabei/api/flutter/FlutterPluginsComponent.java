
package com.jfixby.scarabei.api.flutter;

import com.jfixby.scarabei.api.collections.LambdaMap;
import com.jfixby.scarabei.api.flutter.call.FlutterMethodCall;
import com.jfixby.scarabei.api.flutter.call.JavaMethodCall;

public interface FlutterPluginsComponent {

	FlutterPluginSpecs newPluginSpecs ();

	FlutterPlugin registerPlugin (FlutterPluginSpecs specs);

	Object decode (Object encodedObject, LambdaMap<Object, String> objectTypeNames);

	Object encode (Object javaObject);

	void registerDecoder (CrossLanguageToJavaDecoder flutterToScarabeiDecoder);

	void registerEncoder (JavaToCrossLanguageEncoder encoder);

	JavaMethodCall decodeMethodCall (FlutterMethodCall flutterCall);

}
