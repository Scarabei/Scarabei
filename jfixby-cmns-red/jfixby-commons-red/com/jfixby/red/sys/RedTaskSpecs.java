
package com.jfixby.red.sys;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.taskman.Job;
import com.jfixby.cmns.api.taskman.TaskSpecs;

public class RedTaskSpecs implements TaskSpecs {
	final List<Job> jobs = Collections.newList();
	private boolean runInSeparatedThread = false;
	private String name;

	@Override
	public void setName (final String string) {
		this.name = string;
	}

	@Override
	public String getName () {
		return this.name;
	}

	@Override
	public void setRunInSeparatedThread (final boolean runInSeparatedThread) {
		this.runInSeparatedThread = runInSeparatedThread;
	}

	@Override
	public boolean runInSeparatedThread () {
		return this.runInSeparatedThread;
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

}
