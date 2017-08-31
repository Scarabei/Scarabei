
package com.jfixby.scarabei.api.codecs.calls.io;

import com.jfixby.scarabei.api.names.ID;

public class CrossLanguageMethodCallResult {
	public ID methodName;
	public Throwable error;
	public boolean success;
	public Object result;

}
