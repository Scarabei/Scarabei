
package com.jfixby.scarabei.red.sys;

import com.jfixby.scarabei.api.taskman.Job;

public class RedPromiseJob implements Job {

	private final RedPromise redPromise;

	public RedPromiseJob (final RedPromise redPromise) {
		this.redPromise = redPromise;
	}

	@Override
	public void doStart () throws Throwable {
	}

	@Override
	public void doPush () throws Throwable {
		this.redPromise.deliver();
	}

	@Override
	public boolean isDone () {
		return this.redPromise.isDone;
	}

}
