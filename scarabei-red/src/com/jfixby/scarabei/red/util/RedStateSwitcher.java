
package com.jfixby.scarabei.red.util;

import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.util.EvaluationResult;
import com.jfixby.scarabei.api.util.StateSwitcher;

public class RedStateSwitcher<T> implements StateSwitcher<T> {
	T state;
	private String debug_name;

	public RedStateSwitcher (final T default_state) {
		this.debug_name = "StateSwitcher<?>";
		this.switchState(default_state);
	}

	final RedStateSwitcherEvaluationResult result = new RedStateSwitcherEvaluationResult();

	@Override
	public EvaluationResult expectState (final T expected_state) {
		if (!this.state.equals(expected_state)) {
			final String message = "Wrong state=" + this.state + ", expected: " + expected_state;
			if (this.throw_error) {
				Err.reportError(this.debug_name + ": " + message);
			} else {
				this.result.setErrorFlag(true);
				this.result.setErrorMessage(message);
				return this.result;
			}
		}
		this.result.setErrorFlag(false);
		this.result.setErrorMessage(null);
		return this.result;
	}

	@Override
	public void switchState (final T next_state) {
		if (next_state == null) {
			Err.reportError(this.debug_name + ": " + "Null state detected");
		}
		if (this.debug) {
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
		this.debug = b;
	}

	@Override
	public String toString () {
		return "<" + this.state + ">";
	}

	boolean throw_error = true;

	@Override
	public void setThrowErrorOnUnexpectedState (final boolean throw_error) {
		this.throw_error = throw_error;
	}

	@Override
	public EvaluationResult doesNotExpectState (final T unexpected_state) {
		if (this.state.equals(unexpected_state)) {
			final String message = "Unexpected state=" + this.state;
			if (this.throw_error) {
				Err.reportError(this.debug_name + ": " + message);
			} else {
				this.result.setErrorFlag(true);
				this.result.setErrorMessage(message);
				return this.result;
			}
		}
		this.result.setErrorFlag(false);
		this.result.setErrorMessage(null);
		return this.result;
	}

	@Override
	public boolean stateIs (final T state) {
		return this.currentState() == state;
	}

}
