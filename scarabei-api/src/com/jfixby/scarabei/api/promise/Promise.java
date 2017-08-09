
package com.jfixby.scarabei.api.promise;

public interface Promise<P> {

	P await () throws Throwable;

	<N> Promise<N> then (Future<P, N> receiver);

}
