package com.jfixby.red.util;

import com.jfixby.cmns.api.collections.EvaluationResult;

public class RedStateSwitcherEvaluationResult implements EvaluationResult {

	private boolean error_flag;
	private String error_message;

	public void setErrorMessage(String error_message) {
		this.error_message = error_message;
	}

	public void setErrorFlag(boolean error_flag) {
		this.error_flag = error_flag;
	}

	@Override
	public boolean isOK() {
		return !error_flag;
	}

	@Override
	public String getErrorMessage() {
		return error_message;
	}

}
