
package com.jfixby.scarabei.aws.android.sqs;

import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.jfixby.scarabei.aws.api.sqs.SQSReceiveMessageParams;

public class AndroidSQSReceiveMessageRequest implements com.jfixby.scarabei.aws.api.sqs.SQSReceiveMessageRequest {

	final ReceiveMessageRequest aws_request;

	public AndroidSQSReceiveMessageRequest (final SQSReceiveMessageParams params) {
		final String queueURL = params.getQueueURL();
		this.aws_request = new ReceiveMessageRequest(queueURL);

	}

}
