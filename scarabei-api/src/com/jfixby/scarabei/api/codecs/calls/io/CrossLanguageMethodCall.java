
package com.jfixby.scarabei.api.codecs.calls.io;

import java.util.List;

import com.jfixby.scarabei.api.names.ID;

public class CrossLanguageMethodCall {
	public static final String METHOD_NAME = "methodName";
	public static final String METHOD_ARGUMENTS = "arguments";

	public ID methodName;
	public List<CrossLanguageMethodCallArgument> arguments;

}
