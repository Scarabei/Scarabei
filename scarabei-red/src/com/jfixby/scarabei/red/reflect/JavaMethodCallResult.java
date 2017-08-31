
package com.jfixby.scarabei.red.reflect;

import com.jfixby.scarabei.api.codecs.calls.io.CrossLanguageMethodCallResult;
import com.jfixby.scarabei.api.names.ID;

public class JavaMethodCallResult {

	public Object result;
	public boolean success;
	public Throwable error;
	public ID methodName;

	@Override
	public String toString () {
		return "JavaMethodCallResult [result=" + this.result + ", success=" + this.success + ", error=" + this.error + "]";
	}

	public CrossLanguageMethodCallResult toCrossLanguageCallResult () {
		final CrossLanguageMethodCallResult result = new CrossLanguageMethodCallResult();
		result.methodName = this.methodName;
		if (this.error != null) {
			result.error = this.error;
		}
		result.success = this.success;
		result.result = this.result;
		return result;
	}

}
