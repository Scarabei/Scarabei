package com.jfixby.cmns.api.collections;

public interface StateSwitcher<T> {

	EvaluationResult expectsState(T expected_state);

	EvaluationResult doesNotExpectState(T unexpected_state);

	void switchState(T next_state);

	T currentState();

	void setDebugName(String string);

	void setDebugFlag(boolean b);

	void setThrowErrorOnUnexpectedState(boolean b);

}
