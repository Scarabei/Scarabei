package com.jfixby.cmns.api.sys;

public interface Task {

	boolean isActive();

	boolean isFailed();

	boolean isSuccessfullyCompleted();

	TASK_STATE getState();

}
