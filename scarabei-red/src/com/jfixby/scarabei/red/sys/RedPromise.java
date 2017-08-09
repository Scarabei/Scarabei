
package com.jfixby.scarabei.red.sys;

import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.promise.Future;
import com.jfixby.scarabei.api.promise.Promise;
import com.jfixby.scarabei.api.sys.Sys;
import com.jfixby.scarabei.api.taskman.PromiseSpecs;
import com.jfixby.scarabei.api.taskman.SysExecutor;
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
	private final boolean runInMainThread;

	public RedPromise (final Future<Void, P> future, final PromiseSpecs specs) {
		Debug.checkNull("future", future);
		this.future = future;
		this.job = new RedPromiseJob(this);
		this.runInMainThread = specs.executeInMainThread;

		final TaskSpecs taskSpec = TaskManager.newTaskSpecs();
		if (!this.runInMainThread) {
			taskSpec.setType(TASK_TYPE.SEPARATED_THREAD);
		} else {
			taskSpec.setType(TASK_TYPE.PSEUDO_PARALEL);
		}
		taskSpec.setName(specs.name);
		this.name = specs.name;
		taskSpec.addJob(this.job);
		this.task = TaskManager.newTask(taskSpec);
	}

	final Object lock = new Object();
	boolean isDone;
	private Throwable err;
	private final String name;

	@Override
	public P await () throws Throwable {
		if (SysExecutor.isMainThread()) {
// SysExecutor.pushTasks();
			this.deliver();
		} else {
			while (!this.isDone) {
				L.d(Thread.currentThread() + "", "sleep " + this.name);
				Sys.sleep(15);
			}
		}
		if (!this.delivered) {
			throw this.err;
		}
		return this.result;
	}

	public void deliver () throws Throwable {

		if (this.delivered) {
			return;
		}
		try {
			this.result = this.future.deliver(null);
			this.delivered = true;
			this.isDone = true;
		} catch (final Throwable e) {
			e.printStackTrace();
			this.err = e;
			this.delivered = false;
			this.isDone = true;
			throw e;
		}

	}

	@Override
	public <N> Promise<N> then (final String name, final Future<P, N> receiver) {
		final PromiseSpecs specs = new PromiseSpecs();
		specs.name = name;
		return this.then(specs, receiver);
	}

	@Override
	public <N> Promise<N> then (final PromiseSpecs specs, final Future<P, N> receiver) {
		final Future<Void, N> waitForParentAndProcess = new Future<Void, N>() {
			@Override
			public N deliver (final Void input) throws Throwable {
				final P parentResult = RedPromise.this.await();
				final N next = receiver.deliver(parentResult);
				return next;
			}

		};
		return TaskManager.newPromise(specs, waitForParentAndProcess);
	}

}
