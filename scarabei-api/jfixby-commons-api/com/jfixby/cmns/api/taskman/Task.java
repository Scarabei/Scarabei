
package com.jfixby.cmns.api.taskman;

public interface Task {

	boolean isActive ();

	boolean isFailed ();

	boolean isSuccessfullyCompleted ();

	TASK_STATE getState ();

}
