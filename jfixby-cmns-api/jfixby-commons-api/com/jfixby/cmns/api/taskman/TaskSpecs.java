
package com.jfixby.cmns.api.taskman;

import com.jfixby.cmns.api.collections.Collection;

public interface TaskSpecs {

	void setName (String string);

	String getName ();

	void addJobs (Iterable<Job> jobs);

	Collection<Job> listJobs ();

	void addJob (Job job);

	public void setType (TASK_TYPE type);

	TASK_TYPE getType ();

}
