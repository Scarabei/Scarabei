package com.jfixby.red.util;

import com.jfixby.cmns.api.collections.EvaluationResult;
import com.jfixby.cmns.api.collections.StateSwitcher;
import com.jfixby.cmns.api.log.L;

public class RedStateSwitcher<T> implements StateSwitcher<T> {
	T state;
	private String debug_name;

	public RedStateSwitcher(T default_state) {
		debug_name = "StateSwitcher<?>";
		this.switchState(default_state);
	}

	final RedStateSwitcherEvaluationResult result = new RedStateSwitcherEvaluationResult();

	@Override
	public EvaluationResult expectsState(T expected_state) {
		if (!this.state.equals(expected_state)) {
			String message = "Wrong state=" + this.state + ", expected: "
					+ expected_state;
			if (throw_error) {
				throw new Error(message);
			} else {
				result.setErrorFlag(true);
				result.setErrorMessage(message);
				return result;
			}
		}
		result.setErrorFlag(false);
		result.setErrorMessage(null);
		return result;
	}

	@Override
	public void switchState(T next_state) {
		if (next_state == null) {
			throw new Error("Null state detected");
		}
		if (debug) {
			L.d(this.debug_name + ": " + this.state + " -", next_state);
		}
		this.state = next_state;
	}

	@Override
	public T currentState() {
		return this.state;
	}

	@Override
	public void setDebugName(String string) {
		this.debug_name = string;
	}

	boolean debug = false;

	@Override
	public void setDebugFlag(boolean b) {
		debug = b;
	}

	@Override
	public String toString() {
		return "<" + state + ">";
	}

	boolean throw_error = true;

	@Override
	public void setThrowErrorOnUnexpectedState(boolean throw_error) {
		this.throw_error = throw_error;
	}

	@Override
	public EvaluationResult doesNotExpectState(T unexpected_state) {
		if (this.state.equals(unexpected_state)) {
			String message = "Unexpected state=" + this.state;
			if (throw_error) {
				throw new Error(message);
			} else {
				result.setErrorFlag(true);
				result.setErrorMessage(message);
				return result;
			}
		}
		result.setErrorFlag(false);
		result.setErrorMessage(null);
		return result;
	}

}
