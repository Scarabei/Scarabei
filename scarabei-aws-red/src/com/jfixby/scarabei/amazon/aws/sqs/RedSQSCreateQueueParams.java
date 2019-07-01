
package com.jfixby.scarabei.amazon.aws.sqs;

import com.jfixby.scarabei.aws.api.sqs.SQSCreateQueueParams;

public class RedSQSCreateQueueParams implements SQSCreateQueueParams {

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
