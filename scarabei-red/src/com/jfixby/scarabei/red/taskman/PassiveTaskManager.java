
package com.jfixby.scarabei.red.taskman;

import java.util.ArrayList;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.promise.Promise;
import com.jfixby.scarabei.api.taskman.Job;
import com.jfixby.scarabei.red.promise.RedPromise;

public class PassiveTaskManager extends RedTaskManager {

	public PassiveTaskManager () {
		super();
		this.active_tasks = new ArrayList<PassiveTask>();
		this.new_tasks = new ArrayList<PassiveTask>();
		this.delete_candidates = new ArrayList<PassiveTask>();
	}

	final ArrayList<PassiveTask> new_tasks;
	final ArrayList<PassiveTask> active_tasks;
	final ArrayList<PassiveTask> delete_candidates;
	private boolean print_tasks;

	public void pushAllTasks () {
// L.d("PUSH");
		this.active_tasks.addAll(this.new_tasks);
		this.print_tasks = !false;
		if (this.new_tasks.size() > 0) {
			// this.new_tasks.print("new tasks");
			this.print_tasks = true;
		}
		this.new_tasks.clear();
		this.delete_candidates.clear();
		// this.temp_list.addAll(active_tasks);
		for (int i = 0; i < this.active_tasks.size(); i++) {
			final PassiveTask task = this.active_tasks.get(i);
			if (!task.isActive()) {
				this.delete_candidates.add(task);
			} else {
				task.push();
			}
		}
		if (this.delete_candidates.size() > 0) {
			// this.delete_candidates.print("closed tasks");
		}
		this.active_tasks.removeAll(this.delete_candidates);
		this.delete_candidates.clear();

		if (this.print_tasks) {
			// this.active_tasks.print("active tasks");
		}
	}

	@Override
	public PassiveTask newTask (final String debugName, final Collection<Job> jobs) {
		final PassiveTask task = new PassiveTask(debugName, jobs);
		this.new_tasks.add(task);
		return task;
	}

	@Override
	public Promise<Void> executeAsynchronously (final String debugName, final Job job) {
		return new RedPromise<Void, Void>(debugName, job, this);
	}

}
