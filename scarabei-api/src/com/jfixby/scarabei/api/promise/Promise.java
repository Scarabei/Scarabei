
package com.jfixby.scarabei.api.promise;

public interface Promise<X, Y> {

	Y await () throws Throwable;

	<Z> Promise<Y, Z> then (Future<Y, Z> future);

}
