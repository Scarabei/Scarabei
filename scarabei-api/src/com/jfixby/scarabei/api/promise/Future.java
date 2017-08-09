
package com.jfixby.scarabei.api.promise;

public interface Future<I, O> {

	public O deliver (I input) throws Throwable;

}
