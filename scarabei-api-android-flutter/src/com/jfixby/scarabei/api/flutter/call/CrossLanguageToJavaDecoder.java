
package com.jfixby.scarabei.api.flutter.call;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.LambdaMap;

public interface CrossLanguageToJavaDecoder {

	Object decode (final Object encodedObject, final LambdaMap<Object, String> objectTypeNames);

	Collection<String> listSupportedTypeNames ();

}
