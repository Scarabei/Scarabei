
package com.jfixby.scarabei.api.promise;

public interface Promise<T> {

	T await () throws Throwable;

}
