
package com.jfixby.scarabei.red.promise;

import com.jfixby.scarabei.api.promise.Promise;
import com.jfixby.scarabei.api.taskman.Job;

public class RedPromiseJob<I, R> implements Job {

	private final RedPromise<I, R> redPromise;

	public RedPromiseJob (final RedPromise<I, R> redPromise) {
		this.redPromise = redPromise;
	}

	@Override
	public void doStart () throws Throwable {

	}

	@Override
	public void doPush () throws Throwable {

		final Promise<I> parent = this.redPromise.parent;
		if (parent == null) {
			this.redPromise.deliver(null);
			return;
		}
		if (parent.isDelivered()) {
			final I result = parent.getResult();
			this.redPromise.deliver(result);
			return;
		}
		if (parent.isFailed()) {
			final Throwable e = parent.getError();
			final Exception x = new Exception("Failed promise: " + parent, e);
			this.redPromise.fail(x);
			return;
		}
// L.d(this.redPromise.getName(), "waiting for " + parent);
	}

	@Override
	public boolean isDone () {
		return this.redPromise.isOver();
	}

}
