
package com.jfixby.red.sys;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.taskman.Job;
import com.jfixby.cmns.api.taskman.TASK_STATE;
import com.jfixby.cmns.api.taskman.Task;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.cmns.api.util.StateSwitcher;

public class RedTask implements Task {

	private final List<Job> jobs = Collections.newList();
	// private List<String> names = JUtils.newList();

	// private void listNames() {
	// for (int i = 0; i < this.jobs.size(); i++) {
	// Job job = this.jobs.getElementAt(i);
	// names.add(job.toString());
	// }
	// }

	int job_to_do = -1;

	private final StateSwitcher<TASK_STATE> switcher;
	private String name;

	@Override
	public String toString () {
		return "Task[" + this.name + "]";
	}

	// private String job_name() {
	// return this.names.toString();
	// }

	public RedTask (final String name, final Job job) {
		this.jobs.add(job);
		this.switcher = JUtils.newStateSwitcher(TASK_STATE.ACTIVE);
		// listNames();
	}

	public RedTask (final String name, final Collection<Job> jobs) {
		this.name = name;
		if (name == null) {
			this.name = super.toString();
		}
		this.jobs.addAll(jobs);
		this.switcher = JUtils.newStateSwitcher(TASK_STATE.ACTIVE);
		// listNames();
	}

	boolean first_call = false;
	Job current_job;

	public void push () {
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
