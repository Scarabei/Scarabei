
package com.jfixby.cmns.api.taskman;

import com.jfixby.cmns.api.collections.Collection;

public interface TaskSpecs {

	void setName (String string);

	String getName ();

	void setRunInSeparatedThread (boolean runInSeparatedThread);

	boolean runInSeparatedThread ();

	void addJobs (Iterable<Job> jobs);

	Collection<Job> listJobs ();

	void addJob (Job job);

}
