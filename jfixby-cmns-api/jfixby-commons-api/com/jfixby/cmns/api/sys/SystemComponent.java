package com.jfixby.cmns.api.sys;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.time.TimeStream;

public interface SystemComponent {

	TimeStream SystemTime();

	void exit();

	boolean sleep(long period);

	Task newTask(String task_name, Job job);

	Task newTask(String task_name, Job... jobs_sequence);

	Task newTask(String task_name, Collection<Job> jobs_sequence);

	Task newTask(Job job);

	Task newTask(Job... jobs_sequence);

	Task newTask(Collection<Job> jobs_sequence);

	// Task newSerialTask(AnyCollectionType<Task> tasks);

}
