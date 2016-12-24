
package com.jfixby.scarabei.api.taskman;

public interface Task {

	boolean isActive ();

	boolean isFailed ();

	boolean isSuccessfullyCompleted ();

	TASK_STATE getState ();

}
