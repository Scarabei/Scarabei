
package com.jfixby.scarabei.red.promise;

import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.promise.Future;
import com.jfixby.scarabei.api.promise.Promise;
import com.jfixby.scarabei.api.sys.Sys;
import com.jfixby.scarabei.api.taskman.Task;
import com.jfixby.scarabei.api.taskman.TaskManagerComponent;
import com.jfixby.scarabei.api.ui.UIThread;

public class RedPromise<I, R> implements Promise<R> {
	final Promise<I> parent;
	final Future<I, R> future;
	R result;
	boolean isDelivered = false;
	boolean isOver = false;
	boolean isFailed = false;
	Throwable err;
	final Task task;

	@Override
	public String toString () {
		return "Promise[" + this.task.getName() + "]";
	}

	final RedPromiseJob<I, R> job = new RedPromiseJob<I, R>(this);
	private final TaskManagerComponent executor;

	@Override
	public boolean isDelivered () {
		return this.isDelivered;
	}

	@Override
	public R getResult () {
		return this.result;
	}

	@Override
	public boolean isFailed () {
		return this.isFailed;
	}

	@Override
	public Throwable getError () {
		return this.err;
	}

	@Override
	public boolean isOver () {
		return this.isOver;
	}

	public RedPromise (final String debugName, final Future<I, R> future, final Promise<I> parent,
		final TaskManagerComponent executor) {
		Debug.checkNull("future", future);
		this.parent = parent;
		this.future = future;
		this.executor = executor;
		this.task = executor.newTask(debugName, this.job);

	}

	public void deliver (final I input) {
		Debug.checkTrue("isOver", !this.isOver);
		try {
			if (this.parent == null) {
				this.result = this.future.deliver(null);
			} else {
				this.result = this.future.deliver(input);
			}
			this.success();
		} catch (final Throwable e) {
			this.fail(e);
		}
	}

	public String getName () {
		return this.task.getName();
	}

	@Override
	public R await () throws Throwable {
		if (UIThread.invoke().isUIThread()) {
			Err.reportError("Heavy call in UI thread <" + this.task.getName() + ">");
		}
		while (!this.isOver()) {
// L.d(" sleep " + Thread.currentThread() + " waits for", this.task.getName());
// Sys.sleep(505);
			Sys.yeld();
		}
		if (this.isFailed) {
			throw this.err;
		}
		return this.result;
	}

	public void fail (final Throwable e) {
		e.printStackTrace();
		this.err = e;
		this.isDelivered = false;
		this.isFailed = true;
		this.isOver = true;
	}

	public void success () {
		this.isDelivered = true;
		this.isFailed = false;
		this.err = null;
		this.isOver = true;
	}

	@Override
	public <N> Promise<N> then (final String debugName, final Future<R, N> receiver, final TaskManagerComponent executor) {
		return new RedPromise<R, N>(debugName, receiver, this, executor);
	}

	@Override
	public <N> Promise<N> then (final String debugName, final Future<R, N> receiver) {
		return new RedPromise<R, N>(debugName, receiver, this, this.executor);
	}

//
// private static <W, N> Promise<N> waitFor (final RedPromise<R, N> waitingFor, final String name, final Future<W, N> receiver) {
// final Future<Void, N> waitForParentAndProcess = new Future<Void, N>() {
// @Override
// public N deliver (final Void input) throws Throwable {
// final W parentResult = waitingFor.await();
// final N next = receiver.deliver(parentResult);
// return next;
// }
//
// };
// return TaskManager.executeAsynchronously(name, waitForParentAndProcess);
// }
//
// @Override
// public <J> Promise<Void> join (final Promise<J> promise) {
// final Promise<R> waitingFor1 = RedPromise.this;
// final Promise<J> waitingFor2 = promise;
// final Future<Void, Void> waitForBoth = new Future<Void, Void>() {
// @Override
// public Void deliver (final Void input) throws Throwable {
// final J result2 = waitingFor2.await();
// final R result1 = waitingFor1.await();
// return input;
// }
//
// };
// return TaskManager.executeAsynchronously("wait for " + waitingFor2 + " and " + waitingFor1, waitForBoth);
// }

}
