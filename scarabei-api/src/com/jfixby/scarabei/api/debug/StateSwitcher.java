
package com.jfixby.scarabei.api.debug;

import com.jfixby.scarabei.api.util.EvaluationResult;

public interface StateSwitcher<T> {

	EvaluationResult expectState (T expected_state);

	EvaluationResult doesNotExpectState (T unexpected_state);

	void switchState (T next_state);

	T currentState ();

	void setDebugName (String string);

	void setDebugFlag (boolean b);

	void setThrowErrorOnUnexpectedState (boolean b);

	boolean stateIs (T state);

}
