
package com.jfixby.scarabei.red.taskman.java;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.promise.Promise;
import com.jfixby.scarabei.api.taskman.Job;
import com.jfixby.scarabei.api.taskman.Task;
import com.jfixby.scarabei.red.taskman.RedTaskManager;

public class JavaTaskManager extends RedTaskManager {

	public JavaTaskManager () {
		super();
		Err.reportError("Component disabled due to the deadlocks");
	}

	@Override
	public Task newTask (final String debugName, final Collection<Job> jobs) {
		return new AsyncTask(debugName, jobs);
	}

	@Override
	public Promise<Void> executeAsynchronously (final String debugName, final Job job) {
		return null;
	}

}
