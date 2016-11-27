package com.jfixby.red.sys;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.taskman.Job;
import com.jfixby.cmns.api.taskman.TaskSpecs;

public class RedTaskSpecs implements TaskSpecs {

	@Override
	public void setName (String string) {
	}

	@Override
	public String getName () {
		return null;
	}

	@Override
	public void setRunInSeparatedThread (boolean runInSeparatedThread) {
	}

	@Override
	public boolean runInSeparatedThread () {
		return false;
	}

	@Override
	public void addJobs (Iterable<Job> jobs) {
	}

	@Override
	public Collection<Job> listJobs () {
		return null;
	}

}
