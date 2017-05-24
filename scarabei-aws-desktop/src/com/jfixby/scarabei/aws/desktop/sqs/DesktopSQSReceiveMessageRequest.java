
package com.jfixby.scarabei.aws.desktop.sqs;

import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.jfixby.scarabei.aws.api.sqs.SQSReceiveMessageParams;

public class DesktopSQSReceiveMessageRequest implements com.jfixby.scarabei.aws.api.sqs.SQSReceiveMessageRequest {

	final ReceiveMessageRequest aws_request;

	public DesktopSQSReceiveMessageRequest (final SQSReceiveMessageParams params) {
		final String queueURL = params.getQueueURL();
		this.aws_request = new ReceiveMessageRequest(queueURL);

	}

}
