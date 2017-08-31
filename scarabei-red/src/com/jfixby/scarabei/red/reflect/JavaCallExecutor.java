
package com.jfixby.scarabei.red.reflect;

import com.jfixby.scarabei.api.log.L;

public class JavaCallExecutor {

	public static final JavaMethodCallResult executeCall (final JavaMethodCall javaCall) {
		final JavaMethodCallResult javaCallResult = new JavaMethodCallResult();
		javaCallResult.methodName = javaCall.methodName;
		try {
			javaCallResult.result = javaCall.invoke();
			javaCallResult.success = true;
			javaCallResult.error = null;
		} catch (final Throwable e) {// report java call failure
			L.e("JavaMethodCallResult error", e.toString());
			e.printStackTrace();
			javaCallResult.success = false;
			javaCallResult.error = e;
			javaCallResult.result = null;
		}
// L.d("javaCallResult", javaCallResult);
		return javaCallResult;
	}
}
