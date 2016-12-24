
package com.jfixby.scarabei.api.taskman;

public interface Job {

	public void doStart () throws Throwable;

	public void doPush () throws Throwable;

	public boolean isDone ();

}
