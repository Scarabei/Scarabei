
package com.jfixby.scarabei.api.taskman;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.promise.Future;
import com.jfixby.scarabei.api.promise.Promise;

public interface TaskManagerComponent {

	Task newTask (String debugName, Job... jobs);

	Task newTask (String debugName, Collection<Job> jobs);

	<O> Promise<O> executeAsynchronously (String debugName, Future<Void, O> future);

	Promise<Void> executeAsynchronously (String debugName);

}
