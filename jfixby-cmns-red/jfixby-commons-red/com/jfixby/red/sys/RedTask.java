package com.jfixby.red.sys;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.collections.JUtils;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.collections.StateSwitcher;
import com.jfixby.cmns.api.sys.Job;
import com.jfixby.cmns.api.sys.TASK_STATE;
import com.jfixby.cmns.api.sys.Task;

public class RedTask implements Task {

	private List<Job> jobs = JUtils.newList();
	// private List<String> names = JUtils.newList();

	// private void listNames() {
	// for (int i = 0; i < this.jobs.size(); i++) {
	// Job job = this.jobs.getElementAt(i);
	// names.add(job.toString());
	// }
	// }

	int job_to_do = -1;

	private StateSwitcher<TASK_STATE> switcher;
	private String name;

	@Override
	public String toString() {
		return "Task[" + name + "]";
	}

	// private String job_name() {
	// return this.names.toString();
	// }

	public RedTask(String name, Job job) {
		jobs.add(job);
		switcher = JUtils.newStateSwitcher(TASK_STATE.ACTIVE);
		// listNames();
	}

	public RedTask(String name, Collection<Job> jobs) {
		this.name = name;
		if (name == null) {
			this.name = super.toString();
		}
		this.jobs.addAll(jobs);
		switcher = JUtils.newStateSwitcher(TASK_STATE.ACTIVE);
		// listNames();
	}

	boolean first_call = false;
	Job current_job;

	public void push() {
		switcher.expectsState(TASK_STATE.ACTIVE);
		if (this.job_to_do == -1) {
			this.job_to_do++;
			this.first_call = true;
		}

		this.current_job = this.jobs.getElementAt(this.job_to_do);
		if (this.first_call) {
			try {
				this.current_job.doStart();
				this.first_call = false;
			} catch (Throwable e) {
				e.printStackTrace();
				switcher.switchState(TASK_STATE.FAILED);
				return;
			}
		}

		try {
			this.current_job.doDo();
		} catch (Throwable e) {
			e.printStackTrace();
			switcher.switchState(TASK_STATE.FAILED);
			return;
		}

		if (this.current_job.isDone()) {
			this.job_to_do++;
			this.first_call = true;
		}
		if (this.job_to_do >= this.jobs.size()) {
			// L.d("task done", this);
			switcher.switchState(TASK_STATE.SUCCESSFULLY_COMPLETED);
			return;
		}

	}

	@Override
	public boolean isActive() {
		return switcher.currentState() == TASK_STATE.ACTIVE;
	}

	@Override
	public boolean isFailed() {
		return switcher.currentState() == TASK_STATE.FAILED;
	}

	@Override
	public boolean isSuccessfullyCompleted() {
		return switcher.currentState() == TASK_STATE.SUCCESSFULLY_COMPLETED;
	}

	@Override
	public TASK_STATE getState() {
		return switcher.currentState();
	}

}
