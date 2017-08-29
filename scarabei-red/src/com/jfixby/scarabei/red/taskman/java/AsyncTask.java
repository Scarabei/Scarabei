
package com.jfixby.scarabei.red.taskman.java;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.debug.StateSwitcher;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.sys.Sys;
import com.jfixby.scarabei.api.sys.settings.ExecutionMode;
import com.jfixby.scarabei.api.sys.settings.SystemSettings;
import com.jfixby.scarabei.api.taskman.Job;
import com.jfixby.scarabei.api.taskman.TASK_STATE;
import com.jfixby.scarabei.api.taskman.Task;

public class AsyncTask implements Task, Runnable {

	private final List<Job> jobs = Collections.newList();

	int job_to_do = -1;

	private final StateSwitcher<TASK_STATE> switcher;
	final String name;

	final Thread t;

	@Override
	public String toString () {
		return "Task[" + this.name + "]";
	}

	public AsyncTask (final String name, final Collection<Job> jobs) {
		this.name = name;
		this.jobs.addAll(jobs);
		this.switcher = Debug.newStateSwitcher(TASK_STATE.ACTIVE);
		this.t = new Thread(this.runner);
		this.t.start();
	}

	private final Runnable runner = this;

	boolean first_call = false;
	Job current_job;

	@Override
	public void run () {
		while (this.isActive()) {
			this.pushTask();
		}
	}

	private void pushTask () {
		this.switcher.expectState(TASK_STATE.ACTIVE);
		if (this.job_to_do == -1) {
			this.job_to_do++;
			this.first_call = true;
		}

		this.current_job = this.jobs.getElementAt(this.job_to_do);
		if (this.first_call) {
			try {
				this.current_job.doStart();
				this.first_call = false;
			} catch (final Throwable e) {
				this.onFail(e);
				return;
			}
		}

		try {
			this.current_job.doPush();
		} catch (final Throwable e) {
			this.onFail(e);
			return;
		}

		if (this.current_job.isDone()) {
			this.job_to_do++;
			this.first_call = true;
		}
		if (this.job_to_do >= this.jobs.size()) {
			// L.d("task done", this);
			this.switcher.switchState(TASK_STATE.SUCCESSFULLY_COMPLETED);
			return;
		}

	}

	private void onFail (final Throwable x) {
		final Exception e = new Exception("Task failed" + this.name, x);
		if (SystemSettings.getExecutionMode().isBelow(ExecutionMode.EARLY_DEVELOPMENT)) {
			L.e(e);
		} else {
			Err.reportError(e);
			Sys.exit();
		}
		this.switcher.switchState(TASK_STATE.FAILED);
	}

	@Override
	public boolean isActive () {
		return this.switcher.currentState() == TASK_STATE.ACTIVE;
	}

	@Override
	public boolean isFailed () {
		return this.switcher.currentState() == TASK_STATE.FAILED;
	}

	@Override
	public boolean isSuccessfullyCompleted () {
		return this.switcher.currentState() == TASK_STATE.SUCCESSFULLY_COMPLETED;
	}

	@Override
	public TASK_STATE getState () {
		return this.switcher.currentState();
	}

	@Override
	public String getName () {
		return this.name;
	}

}
