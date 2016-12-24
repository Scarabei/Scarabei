
package com.jfixby.scarabei.red.util;

import com.jfixby.scarabei.api.util.EvaluationResult;

class RedStateSwitcherEvaluationResult implements EvaluationResult {

	private boolean error_flag;
	private String error_message;

	public void setErrorMessage (final String error_message) {
		this.error_message = error_message;
	}

	public void setErrorFlag (final boolean error_flag) {
		this.error_flag = error_flag;
	}

	@Override
	public boolean isOK () {
		return !error_flag;
	}

	@Override
	public String getErrorMessage () {
		return error_message;
	}

}
