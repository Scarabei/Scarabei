
package com.jfixby.scarabei.api.codecs.calls.io;

import java.util.List;

import com.jfixby.scarabei.api.codecs.io.EncodedObject;

public class CrossLanguageMethodCall {
	public EncodedObject methodName;
	public EncodedObject<List<CrossLanguageMethodCallArgument>> arguments;

}
