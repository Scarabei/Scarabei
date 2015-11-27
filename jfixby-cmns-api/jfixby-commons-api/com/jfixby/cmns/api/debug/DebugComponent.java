package com.jfixby.cmns.api.debug;

public interface DebugComponent {

	void printCallStack(boolean condition);

	void exit(boolean condition);

	void printCallStack();


}
