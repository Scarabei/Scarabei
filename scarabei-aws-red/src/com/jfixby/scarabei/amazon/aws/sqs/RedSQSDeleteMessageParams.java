
package com.jfixby.scarabei.amazon.aws.sqs;

import com.jfixby.scarabei.aws.api.sqs.SQSDeleteMessageParams;

public class RedSQSDeleteMessageParams implements SQSDeleteMessageParams {

	private String queueURL;
	private String messageReceiptHandle;

	@Override
	public void setQueueURL (final String queueURL) {
		this.queueURL = queueURL;
	}

	@Override
	public void setMessageReceiptHandle (final String messageReceiptHandle) {
		this.messageReceiptHandle = messageReceiptHandle;
	}

	@Override
	public String getQueueURL () {
		return this.queueURL;
	}

	@Override
	public String getMessageReceiptHandle () {
		return this.messageReceiptHandle;
	}

}
