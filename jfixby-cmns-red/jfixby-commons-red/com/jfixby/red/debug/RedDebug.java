package com.jfixby.red.debug;

import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.debug.DebugComponent;
import com.jfixby.cmns.api.sys.Sys;
import com.jfixby.cmns.api.util.JUtils;

public class RedDebug implements DebugComponent {

	@Override
	public void printCallStack(final boolean condition) {
		if (!condition) {
			return;
		}
		printStack();
	}

	final static private void printStack() {
		CallStack stack = new CallStack();
		List<StackTraceElement> list = JUtils.newList(stack.getStackTrace());
		list.reverse();
		list.removeLast();
		list.removeLast();
		list.removeLast();
		list.print("call stack");
	}

	@Override
	public <T> T checkNull(String parameter_name, T value) {
		if (value == null) {
			throw new Error("<" + parameter_name + "> is null.");
		}
		return value;
	}

	@Override
	public <T> T checkNull(T value) {
		if (value == null) {
			throw new Error("Paremeter is null.");
		}
		return value;
	}

	@Override
	public void checkEmpty(String parameter_name, String value) {
		if ("".equals(value)) {
			throw new Error("<" + parameter_name + "> is empty.");
		}
	}

	@Override
	public void exit(boolean condition) {
		if (condition) {
			Sys.exit();
		}
	}

	@Override
	public void printCallStack() {
		printStack();
	}

}
