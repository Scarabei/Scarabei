
package com.jfixby.scarabei.red.taskman.java;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.taskman.Job;
import com.jfixby.scarabei.api.taskman.Task;
import com.jfixby.scarabei.red.taskman.RedTaskManager;

public class JavaTaskManager extends RedTaskManager {

	@Override
	public Task newTask (final String debugName, final Collection<Job> jobs) {
		return new AsyncTask(debugName, jobs);
	}

}
