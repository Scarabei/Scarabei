
package com.jfixby.scarabei.api.promise;

import com.jfixby.scarabei.api.taskman.TaskManagerComponent;

public interface Promise<P> {

	<N> Promise<N> then (String debugName, Future<P, N> receiver);

	<N> Promise<N> then (String debugName, Future<P, N> receiver, TaskManagerComponent executor);

	boolean isDelivered ();

	P getResult ();

	boolean isFailed ();

	Throwable getError ();

	boolean isOver ();

	public P await () throws Throwable;

}
