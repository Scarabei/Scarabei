
package com.jfixby.scarabei.amazon.aws.sqs;

import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.jfixby.scarabei.aws.api.sqs.SQSReceiveMessageRequest;
import com.jfixby.scarabei.aws.api.sqs.SQSReceiveMessageRequestParams;

public class RedSQSReceiveMessageRequest implements SQSReceiveMessageRequest {

	final ReceiveMessageRequest aws_request;

	public RedSQSReceiveMessageRequest (final SQSReceiveMessageRequestParams params) {
		final String queueURL = params.getQueueURL();
		this.aws_request = new ReceiveMessageRequest(queueURL);

	}

}
