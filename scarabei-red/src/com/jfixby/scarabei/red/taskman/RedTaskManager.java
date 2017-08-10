
package com.jfixby.scarabei.red.taskman;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.promise.Future;
import com.jfixby.scarabei.api.promise.Promise;
import com.jfixby.scarabei.api.taskman.Job;
import com.jfixby.scarabei.api.taskman.Task;
import com.jfixby.scarabei.api.taskman.TaskManagerComponent;
import com.jfixby.scarabei.red.promise.RedPromise;

public abstract class RedTaskManager implements TaskManagerComponent {

	@Override
	public Task newTask (final String name, final Job... jobs) {
		return this.newTask(name, Collections.newList(jobs));
	}

	@Override
	public <O> Promise<O> executeAsynchronously (final String name, final Future<Void, O> future) {
		return new RedPromise<Void, O>(name, future, null, this);
	}

	@Override
	public Promise<Void> executeAsynchronously (final String name) {
		return this.executeAsynchronously(name, this.voidFuture);
	}

	private final Future<Void, Void> voidFuture = new Future<Void, Void>() {
		@Override
		public Void deliver (final Void input) throws Throwable {
			return input;
		}
	};

}
