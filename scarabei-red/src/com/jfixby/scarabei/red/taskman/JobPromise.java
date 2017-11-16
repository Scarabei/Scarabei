package com.jfixby.scarabei.red.taskman;

import com.jfixby.scarabei.api.promise.Future;
import com.jfixby.scarabei.api.promise.Promise;
import com.jfixby.scarabei.api.taskman.TaskManagerComponent;

public class JobPromise implements Promise<Void> {

	@Override
	public <N> Promise<N> then (String debugName, Future<Void, N> receiver) {
		return null;
	}

	@Override
	public <N> Promise<N> then (String debugName, Future<Void, N> receiver, TaskManagerComponent executor) {
		return null;
	}

	@Override
	public boolean isDelivered () {
		return false;
	}

	@Override
	public Void getResult () {
		return null;
	}

	@Override
	public boolean isFailed () {
		return false;
	}

	@Override
	public Throwable getError () {
		return null;
	}

	@Override
	public boolean isOver () {
		return false;
	}

	@Override
	public Void await () throws Throwable {
		return null;
	}

}
