
package com.jfixby.scarabei.aws.desktop.sqs;

import com.jfixby.scarabei.aws.api.sqs.SQSCreateQueueParams;

public class DesktopSQSCreateQueueParams implements SQSCreateQueueParams {

	private String name;

	@Override
	public void setName (final String string) {
		this.name = string;
	}

	@Override
	public String getName () {
		return this.name;
	}

}
