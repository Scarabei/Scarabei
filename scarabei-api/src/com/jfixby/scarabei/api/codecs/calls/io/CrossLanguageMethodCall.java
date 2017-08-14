
package com.jfixby.scarabei.api.codecs.calls.io;

import java.util.List;

import com.jfixby.scarabei.api.codecs.io.EncodedObject;
import com.jfixby.scarabei.api.names.ID;

public class CrossLanguageMethodCall {
	public EncodedObject<ID> methodName;
	public EncodedObject<List<CrossLanguageMethodCallArgument>> arguments;

}
