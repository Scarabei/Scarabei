
package com.jfixby.cmns.api.util;

public interface StateSwitcher<T> {

	EvaluationResult expectState (T expected_state);

	EvaluationResult doesNotExpectState (T unexpected_state);

	void switchState (T next_state);

	T currentState ();

	void setDebugName (String string);

	void setDebugFlag (boolean b);

	void setThrowErrorOnUnexpectedState (boolean b);

}
