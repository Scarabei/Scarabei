
package com.jfixby.scarabei.red.sys;

import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.promise.Future;
import com.jfixby.scarabei.api.promise.Promise;
import com.jfixby.scarabei.api.taskman.TASK_TYPE;
import com.jfixby.scarabei.api.taskman.Task;
import com.jfixby.scarabei.api.taskman.TaskManager;
import com.jfixby.scarabei.api.taskman.TaskSpecs;

public class RedPromise<P> implements Promise<P> {

	private final Future<Void, P> future;
	boolean delivered = false;
	P result;
	private final RedPromiseJob job;
	private final Task task;

	public RedPromise (final Future<Void, P> future) {
		Debug.checkNull("future", future);
		this.future = future;
		this.job = new RedPromiseJob(this);
		final TaskSpecs taskSpec = TaskManager.newTaskSpecs();
		taskSpec.setType(TASK_TYPE.SEPARATED_THREAD);
		taskSpec.setName("PromiseThread<" + future + ">");
		taskSpec.addJob(this.job);
		this.task = TaskManager.newTask(taskSpec);
	}

	final Object lock = new Object();

	@Override
	public P await () throws Throwable {
		L.d("thread", Thread.currentThread());
		synchronized (this.lock) {
			if (this.delivered) {
				return this.result;
			}
			this.result = this.future.deliver(null);
			this.delivered = true;
			return this.result;
		}
	}

	@Override
	public <N> Promise<N> then (final Future<P, N> receiver) {
		final Future<Void, N> waitForParentAndProcess = new Future<Void, N>() {
			@Override
			public N deliver (final Void input) throws Throwable {
				final P parentResult = RedPromise.this.await();
				final N next = receiver.deliver(parentResult);
				return next;
			}

		};
		return TaskManager.invoke().newPromise(waitForParentAndProcess);
	}

}
