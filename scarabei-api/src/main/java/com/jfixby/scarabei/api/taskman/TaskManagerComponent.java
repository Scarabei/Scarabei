
package com.jfixby.scarabei.api.taskman;

import com.jfixby.scarabei.api.collections.Collection;

public interface TaskManagerComponent {

	Task newTask (String task_name, Job job);

	Task newTask (String task_name, Job... jobs_sequence);

	Task newTask (String task_name, Collection<Job> jobs_sequence);

	Task newTask (Job job);

	Task newTask (Job... jobs_sequence);

	Task newTask (Collection<Job> jobs_sequence);

	TaskSpecs newTaskSpecs ();

	Task newTask (TaskSpecs specs);

	SimpleProgress newSimpleProgress ();

	boolean executeImmediately (Job job);

}
