
package com.jfixby.scarabei.api.promise;

public interface Future<T> {

	public T deliver () throws Throwable;

}
