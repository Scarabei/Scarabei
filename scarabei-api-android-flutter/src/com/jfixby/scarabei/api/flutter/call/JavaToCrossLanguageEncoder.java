
package com.jfixby.scarabei.api.flutter.call;

public interface JavaToCrossLanguageEncoder {

	boolean canEncode (Object javaObject);

	Object encode (Object javaObject);

}
