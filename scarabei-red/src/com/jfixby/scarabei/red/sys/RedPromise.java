
package com.jfixby.scarabei.red.sys;

import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.promise.Future;
import com.jfixby.scarabei.api.promise.Promise;
import com.jfixby.scarabei.api.taskman.Task;
import com.jfixby.scarabei.api.taskman.TaskManager;

public class RedPromise<T> implements Promise<T> {

	private final Future<T> future;
	boolean delivered;
	T result;
	private final RedPromiseJob job;
	private final Task task;

	public RedPromise (final Future<T> future) {
		Debug.checkNull("future", future);
		this.future = future;
		this.job = new RedPromiseJob(this);
		this.task = TaskManager.newTask(this.job);
	}

	@Override
	public T await () throws Throwable {
		if (this.delivered) {
			return this.result;
		}
		return this.deliver();
	}

	private T deliver () throws Throwable {
		this.result = this.future.deliver();
		this.delivered = true;
		return this.result;
	}

}
