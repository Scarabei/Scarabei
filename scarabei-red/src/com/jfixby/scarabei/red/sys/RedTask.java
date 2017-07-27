
package com.jfixby.scarabei.red.sys;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.sys.Sys;
import com.jfixby.scarabei.api.taskman.Job;
import com.jfixby.scarabei.api.taskman.TASK_STATE;
import com.jfixby.scarabei.api.taskman.TASK_TYPE;
import com.jfixby.scarabei.api.taskman.Task;
import com.jfixby.scarabei.api.taskman.TaskSpecs;
import com.jfixby.scarabei.api.util.JUtils;
import com.jfixby.scarabei.api.util.StateSwitcher;

public class RedTask implements Task, Runnable {

	private final List<Job> jobs = Collections.newList();

	int job_to_do = -1;

	private final StateSwitcher<TASK_STATE> switcher;
	private String name;
	boolean threadStarted = false;

	private final TASK_TYPE type;
	final Thread t;

	@Override
	public String toString () {
		return "Task[" + this.name + "]";
	}

	public RedTask (final String name, final Job job) {
		this(name, Collections.newList(job));
	}

	public RedTask (final String name, final Collection<Job> jobs) {
		this.name = name;
		if (name == null) {
			this.name = super.toString();
		}
		this.jobs.addAll(jobs);
		this.type = TASK_TYPE.PSEUDO_PARALEL;
		this.switcher = JUtils.newStateSwitcher(TASK_STATE.ACTIVE);
		this.t = null;
		// listNames();
	}

	public RedTask (final TaskSpecs specs) {
		this.name = specs.getName();
		if (this.name == null) {
			this.name = super.toString();
		}

		this.type = Debug.checkNull("TASK_TYPE", specs.getType());

		this.jobs.addAll(specs.listJobs());

		this.switcher = JUtils.newStateSwitcher(TASK_STATE.ACTIVE);

		if (this.type == TASK_TYPE.SEPARATED_THREAD) {
			this.t = new Thread(this.runner);
			this.threadStarted = false;
		} else if (this.type == TASK_TYPE.PSEUDO_PARALEL) {
			this.t = null;
		} else if (this.type == TASK_TYPE.BACKGROUND) {
			this.t = null;
		} else {
			this.t = null;
			Err.throwNotImplementedYet();
		}
	}

	private final Runnable runner = this;

	boolean first_call = false;
	Job current_job;

	public void push () {
		if (this.type == TASK_TYPE.PSEUDO_PARALEL) {
			this.pushTask();
			return;
		}
		if (this.type == TASK_TYPE.SEPARATED_THREAD) {
			if (!this.threadStarted) {
				this.threadStarted = true;
				this.t.start();
			} else {
				Sys.yeld();
			}
			return;
		}

		if (this.type == TASK_TYPE.BACKGROUND) {
			Err.throwNotImplementedYet();
			return;
		}
	}

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
				e.printStackTrace();
				this.switcher.switchState(TASK_STATE.FAILED);
				return;
			}
		}

		try {
			this.current_job.doPush();
		} catch (final Throwable e) {
			Err.reportError(e);
			this.switcher.switchState(TASK_STATE.FAILED);
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

}
