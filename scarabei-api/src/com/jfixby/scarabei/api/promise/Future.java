
package com.jfixby.scarabei.api.promise;

public interface Future<X, Y> {

	public Y deliver (X input) throws Throwable;

}
