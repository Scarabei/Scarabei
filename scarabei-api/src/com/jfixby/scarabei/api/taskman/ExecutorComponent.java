
package com.jfixby.scarabei.api.taskman;

public interface ExecutorComponent {

	void onSystemStart ();

	void pushTasks ();

	boolean isMainThread ();

	void switchMainThread ();
}
