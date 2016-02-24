package com.jfixby.red.sys;

import java.util.ArrayList;

import com.jfixby.cmns.api.assets.AssetID;
import com.jfixby.cmns.api.assets.Names;
import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.Map;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.sys.ExecutionMode;
import com.jfixby.cmns.api.sys.Job;
import com.jfixby.cmns.api.sys.SysExecutor;
import com.jfixby.cmns.api.sys.SystemComponent;
import com.jfixby.cmns.api.sys.Task;
import com.jfixby.cmns.api.time.TimeStream;

public abstract class RedSystem implements SystemComponent {

	private RedSystemExecutor executor;
	final ArrayList<RedTask> new_tasks;
	final ArrayList<RedTask> active_tasks;
	final ArrayList<RedTask> delete_candidates;
	private boolean print_tasks;

	public RedSystem() {
		executor = new RedSystemExecutor(this);
		active_tasks = new ArrayList<RedTask>();
		new_tasks = new ArrayList<RedTask>();
		delete_candidates = new ArrayList<RedTask>();
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

	// --------

	final Map<String, Boolean> flags = Collections.newMap();
	final Map<String, String> strings = Collections.newMap();
	final Map<String, AssetID> assets = Collections.newMap();

	private ExecutionMode execution_mode;

	@Override
	public void setExecutionMode(ExecutionMode execution_mode) {
		// L.d("ExecutionMode", execution_mode);
		this.execution_mode = execution_mode;
	}

	@Override
	public void setFlag(String flag_name, boolean flag_value) {
		flags.put(flag_name, flag_value);
	}

	@Override
	public boolean getFlag(String flag_name) {
		Boolean value = flags.get(flag_name);
		if (value == null) {
			L.d("Flag not found", flag_name);
			return false;
		}
		return value;
	}

	@Override
	public String getStringParameter(String parameter_name) {
		String value = strings.get(parameter_name);
		if (value == null) {
			L.d("Parameter not found", parameter_name);
			return null;
		}
		return value;
	}

	@Override
	public void setStringParameter(String parameter_name, String parameter_value) {
		strings.put(parameter_name, parameter_value);
	}

	@Override
	public void setSystemAssetID(String parameter_name, AssetID parameter_value) {
		assets.put(parameter_name, parameter_value);
	}

	@Override
	public AssetID getSystemAssetID(String parameter_name) {
		AssetID value = assets.get(parameter_name);
		if (value == null) {
			L.d("Parameter not found", parameter_name);
			return Names.newAssetID("com.jfixby.redtriplane.fokker.render.raster_is_missing");
		}
		return value;
	}

}
