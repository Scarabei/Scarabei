
package com.jfixby.red.util;

import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.util.EvaluationResult;
import com.jfixby.cmns.api.util.StateSwitcher;

public class RedStateSwitcher<T> implements StateSwitcher<T> {
	T state;
	private String debug_name;

	public RedStateSwitcher (T default_state) {
		debug_name = "StateSwitcher<?>";
		this.switchState(default_state);
	}

	final RedStateSwitcherEvaluationResult result = new RedStateSwitcherEvaluationResult();

	@Override
	public EvaluationResult expectState (final T expected_state) {
		if (!this.state.equals(expected_state)) {
			String message = "Wrong state=" + this.state + ", expected: " + expected_state;
			if (throw_error) {
				Err.reportError(message);
			} else {
				result.setErrorFlag(true);
				result.setErrorMessage(message);
				return result;
			}
		}
		this.result.setErrorFlag(false);
		this.result.setErrorMessage(null);
		return result;
	}

	@Override
	public void switchState (final T next_state) {
		if (next_state == null) {
			Err.reportError("Null state detected");
		}
		if (debug) {
			L.d(this.debug_name + ": " + this.state + " -", next_state);
		}
		this.state = next_state;
	}

	@Override
	public T currentState () {
		return this.state;
	}

	@Override
	public void setDebugName (final String string) {
		this.debug_name = string;
	}

	boolean debug = false;

	@Override
	public void setDebugFlag (final boolean b) {
		debug = b;
	}

	@Override
	public String toString () {
		return "<" + state + ">";
	}

	boolean throw_error = true;

	@Override
	public void setThrowErrorOnUnexpectedState (final boolean throw_error) {
		this.throw_error = throw_error;
	}

	@Override
	public EvaluationResult doesNotExpectState (final T unexpected_state) {
		if (this.state.equals(unexpected_state)) {
			String message = "Unexpected state=" + this.state;
			if (throw_error) {
				Err.reportError(message);
			} else {
				result.setErrorFlag(true);
				result.setErrorMessage(message);
				return result;
			}
		}
		this.result.setErrorFlag(false);
		this.result.setErrorMessage(null);
		return result;
	}

}
