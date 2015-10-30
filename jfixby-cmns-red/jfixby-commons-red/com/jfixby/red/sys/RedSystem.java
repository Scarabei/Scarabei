package com.jfixby.red.sys;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.collections.JUtils;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.sys.Job;
import com.jfixby.cmns.api.sys.SysExecutor;
import com.jfixby.cmns.api.sys.SystemComponent;
import com.jfixby.cmns.api.sys.Task;
import com.jfixby.cmns.api.time.TimeStream;

public abstract class RedSystem implements SystemComponent {

	private RedSystemExecutor executor;
	final List<RedTask> new_tasks;
	final List<RedTask> active_tasks;
	final List<RedTask> delete_candidates;

	public RedSystem() {
		executor = new RedSystemExecutor(this);
		active_tasks = JUtils.newList();
		new_tasks = JUtils.newList();
		delete_candidates = JUtils.newList();
		SysExecutor.installComponent(executor);
	}

	// @Override
	// public Task newSerialTask(AnyCollectionType<Task> tasks) {
	// SerialTasksBatch batch = new SerialTasksBatch(tasks);
	//
	// Job job = batch.getJob();
	// return this.newTask(job);
	// }

	static final private SystemClock system_clock = new SystemClock();

	@Override
	public TimeStream SystemTime() {
		return system_clock;
	}

	@Override
	public Task newTask(String name, Job job) {
		RedTask task = new RedTask(name, job);
		new_tasks.add(task);
		return task;
	}

	@Override
	public Task newTask(String name, Collection<Job> jobs) {
		RedTask task = new RedTask(name, jobs);
		new_tasks.add(task);
		return task;
	}

	@Override
	public Task newTask(String name, Job... jobs) {
		RedTask task = new RedTask(name, JUtils.newList(jobs));
		new_tasks.add(task);
		return task;
	}

	@Override
	public Task newTask(Job job) {
		return this.newTask(null, job);
	}

	@Override
	public Task newTask(Collection<Job> jobs) {
		return this.newTask(null, jobs);
	}

	@Override
	public Task newTask(Job... jobs) {
		return this.newTask(null, jobs);
	}

	public void push() {

		this.active_tasks.addAll(this.new_tasks);
		boolean print_tasks = false;
		if (this.new_tasks.size() > 0) {
			// this.new_tasks.print("new tasks");
			print_tasks = true;
		}
		this.new_tasks.clear();
		this.delete_candidates.clear();
		// this.temp_list.addAll(active_tasks);
		for (int i = 0; i < this.active_tasks.size(); i++) {
			final RedTask task = this.active_tasks.getElementAt(i);
			if (!task.isActive()) {
				delete_candidates.add(task);
			} else {
				task.push();
			}
		}
		if (this.delete_candidates.size() > 0) {
//			this.delete_candidates.print("closed tasks");
		}
		this.active_tasks.removeAll(delete_candidates);
		delete_candidates.clear();

		if (print_tasks) {
			// this.active_tasks.print("active tasks");
		}
	}

}
