
package com.jfixby.scarabei.api.flutter;

public interface JavaToCrossLanguageEncoder {

	boolean canEncode (Object javaObject);

	Object encode (Object javaObject);

}
