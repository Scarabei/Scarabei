package com.jfixby.red.sys;

import java.util.ArrayList;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.taskman.Job;
import com.jfixby.cmns.api.taskman.SysExecutor;
import com.jfixby.cmns.api.taskman.Task;
import com.jfixby.cmns.api.taskman.TaskManagerComponent;

public class RedTaskManager implements TaskManagerComponent {
    private final RedSystemExecutor executor = new RedSystemExecutor(this);

    public RedTaskManager() {
	super();
	active_tasks = new ArrayList<RedTask>();
	new_tasks = new ArrayList<RedTask>();
	delete_candidates = new ArrayList<RedTask>();

	SysExecutor.installComponent(executor);
    }

    final ArrayList<RedTask> new_tasks;
    final ArrayList<RedTask> active_tasks;
    final ArrayList<RedTask> delete_candidates;
    private boolean print_tasks;

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
	RedTask task = new RedTask(name, Collections.newList(jobs));
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
	print_tasks = !false;
	if (this.new_tasks.size() > 0) {
	    // this.new_tasks.print("new tasks");
	    print_tasks = true;
	}
	this.new_tasks.clear();
	this.delete_candidates.clear();
	// this.temp_list.addAll(active_tasks);
	for (int i = 0; i < this.active_tasks.size(); i++) {
	    final RedTask task = this.active_tasks.get(i);
	    if (!task.isActive()) {
		delete_candidates.add(task);
	    } else {
		task.push();
	    }
	}
	if (this.delete_candidates.size() > 0) {
	    // this.delete_candidates.print("closed tasks");
	}
	this.active_tasks.removeAll(delete_candidates);
	delete_candidates.clear();

	if (print_tasks) {
	    // this.active_tasks.print("active tasks");
	}
    }
}
