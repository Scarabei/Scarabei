
package com.jfixby.scarabei.amazon.aws.sqs;

import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.jfixby.scarabei.aws.api.sqs.SQSReceiveMessageParams;

public class RedSQSReceiveMessageRequest implements com.jfixby.scarabei.aws.api.sqs.SQSReceiveMessageRequest {

	final ReceiveMessageRequest aws_request;

	public RedSQSReceiveMessageRequest (final SQSReceiveMessageParams params) {
		final String queueURL = params.getQueueURL();
		this.aws_request = new ReceiveMessageRequest(queueURL);

	}

}
