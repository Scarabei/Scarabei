package com.jfixby.cmns.api.sys;

public interface Job {

	public void doStart() throws Throwable;

	public void doDo() throws Throwable;

	public boolean isDone();

}
