
package com.jfixby.red.sys;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.taskman.Job;
import com.jfixby.cmns.api.taskman.TASK_TYPE;
import com.jfixby.cmns.api.taskman.TaskSpecs;

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
