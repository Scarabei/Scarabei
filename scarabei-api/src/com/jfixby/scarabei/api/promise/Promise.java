
package com.jfixby.scarabei.api.promise;

import com.jfixby.scarabei.api.taskman.PromiseSpecs;

public interface Promise<P> {

	P await () throws Throwable;

	<N> Promise<N> then (final String name, Future<P, N> receiver);

	<N> Promise<N> then (final PromiseSpecs specs, Future<P, N> receiver);

}
