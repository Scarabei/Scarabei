
package com.jfixby.scarabei.red.sys;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.taskman.Job;
import com.jfixby.scarabei.api.taskman.TASK_TYPE;
import com.jfixby.scarabei.api.taskman.TaskSpecs;

public class RedTaskSpecs implements TaskSpecs {
	final List<Job> jobs = Collections.newList();
	private String name;
	private TASK_TYPE type = TASK_TYPE.PSEUDO_PARALEL;

	@Override
	public void setName (final String string) {
		this.name = string;
	}

	@Override
	public String getName () {
		return this.name;
	}

	@Override
	public void addJobs (final Iterable<Job> jobs) {
		this.jobs.addAll(jobs);
	}

	@Override
	public Collection<Job> listJobs () {
		return this.jobs;
	}

	@Override
	public void addJob (final Job job) {
		this.jobs.add(job);
	}

	@Override
	public void setType (final TASK_TYPE type) {
		this.type = type;
	}

	@Override
	public TASK_TYPE getType () {
		return this.type;
	}

}
