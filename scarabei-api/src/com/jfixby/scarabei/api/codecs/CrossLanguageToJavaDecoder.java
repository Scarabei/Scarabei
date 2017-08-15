
package com.jfixby.scarabei.api.codecs;

import com.jfixby.scarabei.api.codecs.io.EncodedObject;
import com.jfixby.scarabei.api.collections.Collection;

public interface CrossLanguageToJavaDecoder {

	<T> T decode (final EncodedObject encodedObject);

	Collection<String> listSupportedTypeNames ();

	Class<?> resolveType (String objectTypeName);

}
