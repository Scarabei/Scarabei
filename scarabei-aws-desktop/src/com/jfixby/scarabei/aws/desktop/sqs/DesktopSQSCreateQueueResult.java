
package com.jfixby.scarabei.aws.desktop.sqs;

import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.jfixby.scarabei.aws.api.sqs.SQSCreateQueueParams;
import com.jfixby.scarabei.aws.api.sqs.SQSCreateQueueResult;

public class DesktopSQSCreateQueueResult implements SQSCreateQueueResult {

	private final CreateQueueResult aws_createQueueResult;
	private final String url;

	public DesktopSQSCreateQueueResult (final SQSCreateQueueParams createQueueRequestParams, final AmazonSQSClient awsSQSClient) {
		final String name = createQueueRequestParams.getName();
		final CreateQueueRequest createQueueRequest = new CreateQueueRequest(name);

		this.aws_createQueueResult = awsSQSClient.createQueue(createQueueRequest);

		this.url = this.aws_createQueueResult.getQueueUrl();
	}

	@Override
	public String getQueueURL () {
		return this.url;
	}

}
