
package com.jfixby.scarabei.red.sys;

import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.promise.Future;
import com.jfixby.scarabei.api.promise.Promise;
import com.jfixby.scarabei.api.taskman.Task;
import com.jfixby.scarabei.api.taskman.TaskManager;

public class RedPromise<X, Y> implements Promise<X, Y> {

	private final Future<X, Y> future;
	boolean delivered;
	Y result;
	private final RedPromiseJob job;
	private final Task task;

	public RedPromise (final Future<X, Y> future) {
		Debug.checkNull("future", future);
		this.future = future;
		this.job = new RedPromiseJob(this);
		this.task = TaskManager.newTask(this.job);
	}

	@Override
	public Y await () throws Throwable {
		if (this.delivered) {
			return this.result;
		}
		return this.deliver();
	}

	private Y deliver () throws Throwable {
		this.result = this.future.deliver(input);
		this.delivered = true;
		return this.result;
	}

}
