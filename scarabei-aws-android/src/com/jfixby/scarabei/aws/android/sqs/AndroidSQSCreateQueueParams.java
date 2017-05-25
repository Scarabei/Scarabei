
package com.jfixby.scarabei.aws.android.sqs;

import com.jfixby.scarabei.aws.api.sqs.SQSCreateQueueParams;

public class AndroidSQSCreateQueueParams implements SQSCreateQueueParams {

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
